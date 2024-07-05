package com.datarangers.sdk.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DslSign {
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    public static String sha256Hmac(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return hash;
    }

    private static String canonicalMethod(String method) {
        return "HTTPMethod:" + method;
    }

    private static String canonicalUrl(String url) {
        return "CanonicalURI:" + url;
    }

    private static String formatKeyValue(String key, String value) {
        return key + "=" + value;
    }

    private static String canonicalParam(Map<String, String> params) {
        String res = "CanonicalQueryString:";
        if (params == null || params.isEmpty()) {
            return res;
        }
        for (String key : params.keySet()) {
            res += formatKeyValue(key, params.get(key)) + "&";
        }
        return res.substring(0, res.length() - 1);
    }

    private static String canonicalBody(String body) {
        String res = "CanonicalBody:";
        if (body == null) {
            return res;
        } else {
            return res + body;
        }
    }

    private static String canonicalRequest(String method, String url, Map<String, String> params, String body) {
        String cm = canonicalMethod(method);
        String cu = canonicalUrl(url);
        String cp = canonicalParam(params);
        String cb = canonicalBody(body);
        return cm + "\n" + cu + "\n" + cp + "\n" + cb;
    }

    private static String doSign(String ak, String sk, int expiration, String text) {
        String signKeyInfo = "ak-v1/" + ak + "/" + (int) (System.currentTimeMillis() / 1000) + "/" + expiration;
        String signKey = sha256Hmac(signKeyInfo, sk);
        String signResult = sha256Hmac(text, signKey);
        return signKeyInfo + "/" + signResult;
    }

    public static String sign(String ak, String sk, int expiration, String method, String url, Map<String, String> params, String body) {
        String text = canonicalRequest(method, url, params, body);
        return doSign(ak, sk, expiration, text);
    }

    public static String sign(String ak, String sk, int expiration, String method, String url) {
        return sign(ak, sk, expiration, method, url, null, null);
    }
}
