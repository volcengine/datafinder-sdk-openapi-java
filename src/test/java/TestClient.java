import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.datarangers.sdk.DSL;
import com.datarangers.sdk.RangersClient;
import com.datarangers.sdk.requests.Requests;
import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class TestClient {
    private String ak = "xxx";
    private String sk = "xxx";
    private RangersClient rangersClient = null;

    public void analysisRequest(DSL dsl) throws Exception {
        // 开放api统一使用 /openapi 前缀，headers、params 默认传null，若传空Map则code会返回 1403 错误
        String result = rangersClient.dataFinder("/openapi/v1/analysis", "post", null, null, JSON.toJSONString(dsl));
        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        Assert.assertEquals("result not ok", (int) resultObject.getInteger("code"), 200);
        Assert.assertEquals("message not SUCCESS", resultObject.getString("message"), "success");
        for (Object obj : resultObject.getJSONArray("data")) {
            JSONObject obj_ = (JSONObject) obj;
            Assert.assertEquals("message error", "SUCCESS", obj_.getString("result_status"));
        }
    }

    public void dataRracerRequest(DSL dsl) throws Exception {
        String result = rangersClient.dataTracer("/openapi/v1/161842/query/table", "post", null, null, JSON.toJSONString(dsl));
        System.out.println(result);
    }

    @Before
    public void init() {
        if (rangersClient == null) {
            rangersClient = new RangersClient(ak, sk);
            // 若私有化部署，则需要传产品私有化访问域名url
//            rangersClient = new RangersClient(ak, sk,"产品私有化访问域名");
        }
    }

    @Test
    public void testFunnel() throws Exception {
        analysisRequest(TestCommon.getFunnelDSL());
    }

    @Test
    public void testLifeCycle() throws Exception {
        analysisRequest(TestCommon.getLifeCycleDSL());
    }

    @Test
    public void testPathFinder() throws Exception {
        analysisRequest(TestCommon.getPathFinderDSL());
    }

    @Test
    public void testRetention() throws Exception {
        analysisRequest(TestCommon.getRetentionDSL());
    }

    @Test
    public void testWeb() throws Exception {
        analysisRequest(TestCommon.getWebDSL());
    }

    @Test
    public void testTopK() throws Exception {
        analysisRequest(TestCommon.getTopKDSL());
    }

    @Test
    public void testTracerTable() throws Exception {
        dataRracerRequest(TestCommon.getTracerTableDSL());
    }

    @Test
    public void testRangersOpenAPI() throws Exception {
        OkHttpClient okHttpClient = createHttpClient();
        Requests.init(okHttpClient);
        String result = rangersClient.dataRangers("/openapi/v1/xxx/date/2020-02-20/2020-02-23/downloads", "get");
        System.out.println(result);
    }


    private OkHttpClient createHttpClient() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            sslContext.init((KeyManager[]) null, new TrustManager[]{tm}, (SecureRandom) null);
            SSLSocketFactory  sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, tm)
                    .build();
            return okHttpClient;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
            throw new RuntimeException("init ssl error", e);
        }
    }
}
