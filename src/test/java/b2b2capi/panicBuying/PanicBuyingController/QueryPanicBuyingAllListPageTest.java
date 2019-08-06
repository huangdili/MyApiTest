package b2b2capi.panicBuying.PanicBuyingController;

import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class QueryPanicBuyingAllListPageTest{
    String url;
    @BeforeTest
    public void init(){
        url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties", "/app/panicBuying/queryPanicBuyingAllListPage");
    }

    @Test(dataProvider = "queryPanicBuyingAllListPageCsv")
    public void queryPanicBuyingAllListPageTest(String title,String currentPage,String expectedCode,String expectedReturnCode,
                                               String expectedMessage,String expectedResultData,String expectedResultCount,String isRun) throws IOException, FrameworkException {

        if(isRun.toUpperCase().equals("Y")) {

            Map<String, Object> map = new HashMap<>();
            //构造请求参数
            map.put("currentPage",currentPage);

            //调用接口
            Response response = RequestFactory.getRequest(url, map);
            //调用接口
            PinhengResponseBody pinhengResponseBody = new PinhengResponseBody(response);
            AssertUtil.integerAssertEquals(response.code(), expectedCode);
            AssertUtil.assertPinhengResponse(pinhengResponseBody, expectedReturnCode, expectedMessage, expectedResultCount);
            //resultData
            Assert.assertEquals(pinhengResponseBody.getResultData().toString(), expectedResultData);
        }else {
            assertTrue(false,title+"isRUN为N，未运行");
        }

    }

    @DataProvider(name = "queryPanicBuyingAllListPageCsv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("src/test/testdatas/b2b2capi/panicBuying/PanicBuyingController/queryPanicBuyingAllListPage.CSV");
        return parameters;
    }
}