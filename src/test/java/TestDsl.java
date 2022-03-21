import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.datarangers.sdk.DSL;
import com.datarangers.sdk.requests.Requests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TestDsl {
    DSL dsl;
    String url = "xxxx";
    HashMap<String, String> headers = null;

    @Before
    public void init() throws Exception {
        if (headers == null) {
            headers = new HashMap<String, String>() {
                {
                    put("Content-Type", "application/json");
                    put("X-TOKEN", "bytefinder/1592207002/xxx/xxxx");
                    put("x-caller", "openapi");
                    put("x-user", "xxx");
                }
            };
        }
    }

    public void getResult(DSL d) {
        String data = JSON.toJSONString(d);
        System.out.println(data);
        String result = Requests.post(url, headers, null, data);
        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        Assert.assertEquals("result not ok", (int) resultObject.getInteger("code"), 200);
        Assert.assertEquals("message not SUCCESS", resultObject.getString("message"), "success");
        for (Object obj : resultObject.getJSONArray("result")) {
            JSONObject obj_ = (JSONObject) obj;
            Assert.assertEquals("message error", "SUCCESS", obj_.getString("result_status"));
        }
    }

    @Test
    public void testEvent() {
        dsl = TestCommon.getEventDSL();
        getResult(dsl);
    }

    @Test
    public void testFunnel() {
        dsl = TestCommon.getFunnelDSL();
        getResult(dsl);
    }

    @Test
    public void testLifeCycle() {
        dsl = TestCommon.getLifeCycleDSL();
        getResult(dsl);
    }

    @Test
    public void testPathFinder() {
        dsl = TestCommon.getPathFinderDSL();
        getResult(dsl);
    }

    @Test
    public void testRetention() {
        dsl = TestCommon.getRetentionDSL();
        getResult(dsl);
    }

    @Test
    public void testWeb() {
        dsl = TestCommon.getWebDSL();
        getResult(dsl);
    }

    @Test
    public void testTopK() {
        dsl = TestCommon.getTopKDSL();
        getResult(dsl);
    }

    @Test
    public void testTracerTable() {
        dsl = TestCommon.getTracerTableDSL();
        getResult(dsl);
    }
}
