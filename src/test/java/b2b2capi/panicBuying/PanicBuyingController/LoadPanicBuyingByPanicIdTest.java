package b2b2capi.panicBuying.PanicBuyingController;

import net.sf.json.JSONArray;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoadPanicBuyingByPanicIdTest {

    @Test(dataProvider = "loadPanicBuyingByPanicIdCsv")
    public void loadPanicBuyingByPanicIdTest(String panicId,String expectedCode,String resultCount) throws IOException, FrameworkException {

        String url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties","app/panicBuying/loadPanicBuyingByPanicId");

        Map<String, Object> map = new HashMap<>();
        //构造请求参数
        map.put("panicId",panicId);
        //调用接口
        Response response = RequestFactory.getRequest(url,map);
        //校验response.code
        Assert.assertEquals(Integer.valueOf(response.code()),Integer.valueOf(expectedCode));
        //校验returnCode
        PinhengResponseBody pinhengResponseBody = new PinhengResponseBody(response);
        int returnCode = pinhengResponseBody.getReturnCode();
        Assert.assertEquals(0,returnCode);

 //       Assert.assertTrue(AssertUtil.isDataExist(JSONArray.fromObject(pinhengResponseBody.getResultData()),"areaId","1"));

    }

    @DataProvider(name = "loadPanicBuyingByPanicIdCsv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("src/test/testdatas/b2b2capi/panicBuying/PanicBuyingController/loadPanicBuyingByPanicId.CSV");
        return parameters;
    }
}
