package b2b2capi.panicBuying.PanicBuyingController;

import net.sf.json.JSONArray;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoadPanicBuyingByPanicIdTest {

    @Test(dataProvider = "loadPanicBuyingByPanicIdCsv")
    public void loadPanicBuyingByPanicIdTest(String panicId,String expectedCode,String expectedReturnCode,String expectedMessage,String expectedResultData,String resultCount) throws IOException, FrameworkException {

        String url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties","app/panicBuying/loadPanicBuyingByPanicId");

        Map<String, Object> map = new HashMap<>();
        //构造请求参数
        map.put("panicId",panicId);
        //调用接口
        Response response = RequestFactory.getRequest(url,map);
        PinhengResponseBody pinhengResponseBody = new PinhengResponseBody(response);
        AssertUtil.integerAssertEquals(response.code(),expectedCode);
        AssertUtil.assertPinhengResponse(pinhengResponseBody,expectedReturnCode,expectedMessage,resultCount);
        //resultData
        Assert.assertEquals(pinhengResponseBody.getResultData().toString(),expectedResultData);


    }

    @DataProvider(name = "loadPanicBuyingByPanicIdCsv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("src/test/testdatas/b2b2capi/panicBuying/PanicBuyingController/loadPanicBuyingByPanicId.CSV");
        return parameters;
    }
}
