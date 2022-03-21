import com.datarangers.sdk.RangersClient;
import org.junit.Before;
import org.junit.Test;

public class DataProfileClientTest {

    private String ak = "xxx";
    private String sk = "xxx";
    private RangersClient rangersClient = null;
    private String url = "xxx";

    @Before
    public void beforeInit(){
        rangersClient = new RangersClient(ak, sk, url);
    }

    @Test
    public void testQuery() throws Exception {
        String path = "/openapi/v1/111/items/shirt/aaaa";
        String method = "GET";

        String resp = rangersClient.dataProfile(path, method, null, null, "");
        System.out.println(resp);
    }

    @Test
    public void testPost() throws Exception {
        String path = "/openapi/v1/1/items/shirt";
        String method = "POST";
        String body = "{\n" +
                "  \"description\": \"description\",\n" +
                "  \"properties\": [\n" +
                "    {\n" +
                "      \"name\": \"price\",\n" +
                "      \"type\": \"FLOAT\",\n" +
                "      \"status\": 1,\n" +
                "      \"description\": \"价格\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"color\",\n" +
                "      \"type\": \"STRING\",\n" +
                "      \"status\": 1,\n" +
                "      \"description\": \"颜色\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String resp = rangersClient.dataProfile(path, method, null, null, body);
        System.out.println(resp);
    }

    @Test
    public void testPut() throws Exception {
        String path = "/openapi/v1/1/items/shirt/aaaa/attributes";
        String method = "PUT";
        String body = "{\n" +
                "  \"description\": \"额为的描述信息\",\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"name\": \"price\",\n" +
                "      \"value\": 9,\n" +
                "      \"operation\": \"SET\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"color\",\n" +
                "      \"operation\": \"UNSET\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"region\",\n" +
                "      \"operation\": \"APPEND\",\n" +
                "      \"value\": [\n" +
                "        \"nanjing\",\n" +
                "        \"shanghai\",\n" +
                "        \"beijing\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String resp = rangersClient.dataProfile(path, method, null, null, body);
        System.out.println(resp);
    }

    @Test
    public void testPatch() throws Exception {
        String path = "/openapi/v1/1/items/shirt/aaaa/attributes/color";
        String method = "PATCH";
        String body = "{\n" +
                "    \"operation\":\"SET\",\n" +
                "    \"vlaue\": 9\n" +
                "}";
        String resp = rangersClient.dataProfile(path, method, null, null, body);
        System.out.println(resp);
    }
}
