package b2b2capi.basic.BasicAreaController;

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



public class GetNextAreaByParamsTest {

    @Test(dataProvider = "getNextAreaByParamsCsv")
    public void getNextAreaTestByParamsTest(String name,String fullName, String areaCode, String parentId, String levelCode,String expectedCode,String expectedCount) throws IOException, FrameworkException {

        String url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties","app/area/getNextAreaByParams");

        Map<String, Object> map = new HashMap<>();
        //构造请求参数
        if(!name.equals("null")){
            map.put("name",name);
        }

        if(!fullName.equals("null")){
            map.put("fullName",fullName);
        }

        if(!areaCode.equals("null")){
            map.put("areaCode",areaCode);
        }

        map.put("parentId",parentId);
        map.put("levelCode",levelCode);


        //调用接口
        Response response = RequestFactory.postRequest_Form(url,map);
        //校验response.code
        Assert.assertEquals(Integer.valueOf(response.code()),Integer.valueOf(expectedCode));
        //校验returnCode
        PinhengResponseBody pinhengResponseBody = new PinhengResponseBody(response);

        int returnCode = pinhengResponseBody.getReturnCode();
        Assert.assertEquals(1,returnCode);

        Assert.assertTrue(AssertUtil.isDataExist(JSONArray.fromObject(pinhengResponseBody.getResultData()),"parentId",parentId));

        Assert.assertEquals(pinhengResponseBody.getResultCount(),Integer.parseInt(expectedCount));

    }

    @DataProvider(name = "getNextAreaByParamsCsv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("src/test/testdatas/b2b2capi/basic/BasicAreaController/getNextAreaByParams.CSV");
        return parameters;
    }
}
