package ${packageDir};

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

public class ${className}{
    String url;
    @BeforeTest
    public void init(){
        url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties", "${interfaceUrl}");
    }

    @Test(dataProvider = "${interface_name}Csv")
    public void ${interface_name}Test(${input_param},String expectedCode,String expectedReturnCode,
                                               String expectedMessage,String expectedResultData,String expectedResultCount,String isRun) throws IOException, FrameworkException {

        if(isRun.toUpperCase().equals("Y")) {

            Map<String, Object> map = new HashMap<>();
            //构造请求参数
${map_builder}
            //调用接口
            Response response = RequestFactory.getRequest(url, map);
            //调用接口
            PinhengResponseBody pinhengResponseBody = new PinhengResponseBody(response);
            AssertUtil.integerAssertEquals(response.code(), expectedCode);
            AssertUtil.assertPinhengResponse(pinhengResponseBody, expectedReturnCode, expectedMessage, expectedResultCount);
            //resultData
            Assert.assertEquals(pinhengResponseBody.getResultData().toString(), expectedResultData);
        }else {
            assertTrue(false,"isRUN为N，未运行");
        }

    }

    @DataProvider(name = "${interface_name}Csv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("${csvDir}/${interface_name}.CSV");
        return parameters;
    }
}