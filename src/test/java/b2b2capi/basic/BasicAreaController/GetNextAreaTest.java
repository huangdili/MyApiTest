package b2b2capi.basic.BasicAreaController;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


public class GetNextAreaTest {
    @BeforeClass
    public void init(){


    }

    @Test(dataProvider = "getNextAreaCsv")
    public void getNextAreaTest(String areaId,String expectedCode) throws IOException, FrameworkException {

        String url = HttpRequestUrlAppendUtil.getHttpurl("pinhengconfig.properties","app/area/getNextArea");

        Map<String, Object> map = new HashMap<>();
        map.put("areaId",areaId);
        Response response = RequestFactory.postRequest_Json(url,map);
        Assert.assertEquals(Integer.valueOf(expectedCode),Integer.valueOf(response.code()));
        PinhengResponseBody pinhengResponseBody = new PinhengResponseBody();
        int returnCode = pinhengResponseBody.getReturnCode(response);
        Assert.assertEquals(1,returnCode);

    }

    @DataProvider(name = "getNextAreaCsv")
    public Object[][] areaCsv(){
        Object[][] parameters = CsvUtil.csvReader("src/test/testdatas/b2b2capi/basic/BasicAreaController/getNextAreaCsv.CSV");
        return parameters;
    }

}
