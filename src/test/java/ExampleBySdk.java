import com.datarangers.sdk.RangersClient;

import java.util.HashMap;

/**
 * @Author zhangpeng.spin@bytedance.com
 * @Date 2020-06-15
 */
public class ExampleBySdk {


    public static void getDataExportUrl() throws Exception {
        String ak = "xxx";
        String sk = "xxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        String res = rangersClient.dataRangers("/openapi/v1/xxx/date/2020-05-03/2020-05-09/downloads", "get");
        System.out.println(res);
    }

    public static void testFinderAll() throws Exception {
        String ak = "xxx";
        String sk = "xxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        String res = rangersClient.dataFinder("/openapi/v1/xxx/dashboards/all", "get");
        System.out.println(res);
    }

    public static void testFinderDashboard() throws Exception {
        String ak = "xxx";
        String sk = "xxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        String res = rangersClient.dataFinder("/openapi/v1/xxx/dashboards/xxx", "get");
        System.out.println(res);
    }

    public static void testFinderDashboardReports() throws Exception {
        String ak = "xxx";
        String sk = "xxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        String res = rangersClient.dataFinder("/openapi/v1/xxx/dashboards/xxx/reports", "get");
        System.out.println(res);
    }

    public static void testFinderDashboardReport(String id) throws Exception {
        String ak = "xxx";
        String sk = "xxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        HashMap<String, String> headers = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("count", "10");
        String res = rangersClient.dataFinder("/openapi/v1/xxx/reports/" + id, "get", headers, params, null);
        System.out.println(res);
    }

    public static void testFinderCohorts() throws Exception {
        String ak = "xxx";
        String sk = "xxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        String res = rangersClient.dataFinder("/openapi/v1/xxx/cohorts", "get");
        System.out.println(res);
    }

    public static void testFinderCohort() throws Exception {
        String ak = "xxx";
        String sk = "xxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        String res = rangersClient.dataFinder("/openapi/v1/xxxx/cohorts/xxx", "get");
        System.out.println(res);
    }

    public static void testFinderCohortsSample() throws Exception {
        String ak = "xxxx";
        String sk = "xxxx";
        RangersClient rangersClient = new RangersClient(ak, sk);
        HashMap<String, String> headers = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("count", "10");
        String res = rangersClient.dataFinder("/openapi/v1/xxx/cohorts/xxx/sample", "get", headers, params, null);
        System.out.println(res);
    }

    public static void main(String[] args) {
        try {
            getDataExportUrl();
            testFinderAll();
            testFinderDashboard();
            testFinderDashboardReports();
            testFinderDashboardReport("xxx");
            testFinderCohorts();
            testFinderCohort();
            testFinderCohortsSample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
