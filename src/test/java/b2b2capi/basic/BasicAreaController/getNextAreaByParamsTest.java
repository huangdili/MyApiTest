package b2b2capi.basic.BasicAreaController;

import net.sf.json.JSONArray;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class getNextAreaByParamsTest {

    @Test(dataProvider = "getNextAreaByParamsCsv")
    public void getNextAreaTest(String name,String fullName, String areaCode, String parentId, String levelCode,String expectedCode) throws IOException, FrameworkException {

        String url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties","app/area/getNextAreaByParams");

        Map<String, Object> map = new HashMap<>();
        //构造请求参数
        if(null != name && !"".equals(name)){
            map.put("name",name);
        }

        if(null != fullName && !"".equals(fullName)){
            map.put("fullName",fullName);
        }

        if(null != areaCode && !"".equals(areaCode)){
            map.put("areaCode",areaCode);
        }

        map.put("parentId",parentId);
        map.put("levelCode",levelCode);

        //调用接口
        Response response = RequestFactory.postRequest_Json(url,map);
        //校验response.code
        Assert.assertEquals(Integer.valueOf(expectedCode),Integer.valueOf(response.code()));
        //校验returnCode
        PinhengResponseBody pinhengResponseBody = new PinhengResponseBody(response);
        int returnCode = pinhengResponseBody.getReturnCode();
        Assert.assertEquals(1,returnCode);

        Assert.assertTrue(AssertUtil.isDataExist(JSONArray.fromObject(pinhengResponseBody.getResultData()),"parentId",parentId));

    }

    @DataProvider(name = "getNextAreaByParamsCsv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("src/test/testdatas/b2b2capi/basic/BasicAreaController/getNextAreaByParams.CSV");
        return parameters;
    }
}
