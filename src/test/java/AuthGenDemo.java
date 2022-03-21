import java.util.HashMap;
import java.util.Map;

public class AuthGenDemo {

    //分配的accessKey和secretKey
    private static String accessKey = "****";
    private static String secretKey = "****";
    // 单位秒
    private static Integer expirationSeconds = 300;

    public static void main(String[] args) {
        String method = "POST";
        String uri = "/dataprofile/openapi/v1/xxx/users/xxx";
        Map<String, String> exampleQueryParams = new HashMap<>();
        exampleQueryParams.put("set_once", "true");
        String exampleQueryBodyJson = "{\"name\":\"name\",\"value\":\"zhangsan\"}";

        String authorization = AuthUtils.sign(accessKey, secretKey, expirationSeconds,
                method, uri, exampleQueryParams, exampleQueryBodyJson);
        System.out.println("authorization: " + authorization);
    }
}
