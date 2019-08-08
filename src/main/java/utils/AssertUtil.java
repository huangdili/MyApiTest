package utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import okhttp3.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;


/**
 * @author huangdili
 */

public class AssertUtil {

    /**
     * 比较returnCode、message、还有resultCount,resultData不计较，原因比较形式不一样
     * @param pinhengResponseBody
     * @param expectedReturnCode
     * @param expectedMessage
     * @param expectedResultCount
     * @throws IOException
     * @throws FrameworkException
     */

    public static void assertPinhengResponse(PinhengResponseBody pinhengResponseBody,String expectedReturnCode, String expectedMessage,String expectedResultCount) throws IOException, FrameworkException {

            AssertUtil.integerAssertEquals(pinhengResponseBody.getReturnCode(), expectedReturnCode);
            Assert.assertEquals(pinhengResponseBody.getMessage(), expectedMessage);
            AssertUtil.integerAssertEquals(pinhengResponseBody.getResultCount(), expectedResultCount);

    }


    /**
     * 判断Json数组中是否存在某个关键字，并比对它的值，存在返回true,不存在返回false
     * @param data
     * @param key
     * @param value
     * @return
     * @throws FrameworkException
     */

    public static boolean isDataExist(JSONArray data, String key, String value) throws FrameworkException {
        if(data.size()>0) {
            for (int i = 0; i < data.size(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                String actualValue = jsonObject.getString(key);

                if(actualValue.equalsIgnoreCase(value)){
                    return true;
                }
            }
            return false;
        }else {
            throw new FrameworkException("data为空");
        }
    }

    public static  void integerAssertEquals(Object actulaObject,String expectedObject){
        Assert.assertEquals(Integer.valueOf(actulaObject.toString()),Integer.valueOf(expectedObject));
    }

    public static boolean isListEqual(List list, List listToCompare){
        if(list == listToCompare){
            return true;
        }
        if (list == null && listToCompare == null){
            return true;
        }

        if (list == null || listToCompare == null)
            return false;
        if (list.size() != listToCompare.size()){
            return false;
        }

        for (Object o : list) {
            if (!listToCompare.contains(o)){
                return false;
            }

        }
        for (Object o : listToCompare) {
            if(!list.contains(o)){
                return false;
            }
        }
        return true;
    }

}
