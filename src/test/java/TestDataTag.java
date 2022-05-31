import com.datarangers.sdk.RangersClient;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

/**
 * @Author zhangpeng.spin@bytedance.com
 * @Date 2022/5/31
 */
public class TestDataTag {
    private static String ak = System.getenv("ak");
    private static String sk = System.getenv("sk");
    private static String url = System.getenv("url");
    private static RangersClient bc = new RangersClient(ak, sk, url);

    public static void testUpload() throws Exception {
        String method = "POST";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag/file/upload";
        HashMap<String, String> headers = null;
        HashMap<String, String> params = null;
        File file = new File("user_tag.csv");  // 换成指定文件的路径

        String resp = bc.uploadFile(method, serviceUrl, headers, params, file);
        System.out.println(resp);
    }

    public static void testCreate() throws Exception {
        String method = "POST";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag";
        HashMap<String, String> headers = null;
        HashMap<String, String> params = null;
        String body = "{\n" +
                "    \"name\": \"tag_test_tag_java\",\n" +
                "    \"show_name\": \"测试标签_java\",\n" +
                "    \"value_type\": \"string\",\n" +
                "    \"description\": \"\",\n" +
                "    \"create_type\": \"upload\",\n" +
                "    \"refresh_rule\": \"manual\",\n" +
                "    \"tag_rule\": {\n" +
                "        \"file\": {\n" +
                "            \"file_key\": \"tag_upload_uuid/164314/20220531/cca802ed4f10486aa765e8a3f36f6449.json\",\n" +
                "            \"detail\": {\n" +
                "                \"name\": \"user_tag.csv\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        String resp = bc.request(method, serviceUrl, headers, params, body);
        System.out.println(resp);
    }

    public static void testQuery() throws Exception {
        String method = "GET";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag/tag_test_tag_java/result";
        String resp = bc.request(method, serviceUrl, null, null, null);
        System.out.println(resp);
    }

    public static void testQueryHistory() throws Exception {
        String method = "POST";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag/tag_test_tag_java/result/history";
        String body = "{\n" +
                "    \"granularity\":\"day\",\n" +
                "    \"type\":\"past_range\",\n" +
                "    \"spans\":[\n" +
                "        {\n" +
                "            \"type\":\"past\",\n" +
                "            \"past\":{\n" +
                "                \"amount\":7,\n" +
                "                \"unit\":\"day\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\":\"past\",\n" +
                "            \"past\":{\n" +
                "                \"amount\":1,\n" +
                "                \"unit\":\"day\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"timezone\":\"Asia/Shanghai\",\n" +
                "    \"week_start\":1\n" +
                "}";
        String resp = bc.request(method, serviceUrl, null, null, body);
        System.out.println(resp);
    }

    public static void testExportTag() throws Exception {
        String method = "POST";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag/tag_test_tag_java/download";
        String body = "{\n" +
                "    \"type\": \"user\",\n" +
                "    \"condition\": {\n" +
                "        \"property_operation\": \"is_not_null\",\n" +
                "        \"snapshot\": {\n" +
                "            \"type\": \"day\",\n" +
                "            \"day\": \"2022-05-31\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"period\": {\n" +
                "        \"timezone\": \"Asia/Shanghai\"\n" +
                "    }\n" +
                "}";
        String resp = bc.request(method, serviceUrl, null, null, body);
        System.out.println(resp);
    }

    public static void testQueryTagInfo() throws Exception {
        String method = "GET";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag/tag_test_tag_java";
        String resp = bc.request(method, serviceUrl, null, null, null);
        System.out.println(resp);
    }

    public static void testQueryTags() throws Exception {
        String method = "GET";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag";
        String resp = bc.request(method, serviceUrl, null, null, null);
        System.out.println(resp);
    }

    public static void testCalTag() throws Exception {
        String method = "POST";
        String serviceUrl = "/datatag/openapi/v1/app/164314/tag/tag_test_tag_java/calculation";
        String resp = bc.request(method, serviceUrl, null, null, null);
        System.out.println(resp);
    }

    public static void main(String[] args) throws Exception {
        //testUpload();
//        testCreate();
        testQuery();
        testQueryHistory();
        testExportTag();
        testQueryTagInfo();
        testQueryTags();
//        testCalTag();

    }

}
