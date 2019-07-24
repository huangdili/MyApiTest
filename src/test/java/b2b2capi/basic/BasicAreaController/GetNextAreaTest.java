package b2b2capi.basic.BasicAreaController;
import net.sf.json.JSONArray;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetNextAreaTest {
    @BeforeClass
    public void init(){


    }

    @Test(dataProvider = "getNextAreaCsv")
    public void getNextAreaTest(String areaId,String expectedCode) throws IOException, FrameworkException {

        String url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties","app/area/getNextArea");

        Map<String, Object> map = new HashMap<>();
        //构造请求参数
        map.put("areaId",areaId);
        //调用接口
        Response response = RequestFactory.postRequest_Json(url,map);
        //校验response.code
        Assert.assertEquals(Integer.valueOf(expectedCode),Integer.valueOf(response.code()));
        //校验returnCode
        PinhengResponseBody pinhengResponseBody = new PinhengResponseBody(response);
        int returnCode = pinhengResponseBody.getReturnCode();
        Assert.assertEquals(1,returnCode);

        Assert.assertTrue(AssertUtil.isDataExist(JSONArray.fromObject(pinhengResponseBody.getResultData()),"areaId","1"));

    }

    @DataProvider(name = "getNextAreaCsv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("src/test/testdatas/b2b2capi/basic/BasicAreaController/getNextArea.CSV");
        return parameters;
    }

}
