package utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import okhttp3.Response;

import java.io.IOException;

import static utils.LogbackUtil.LOGGER;

public class PinhengResponseBody {

    private static int returnCode;
    private static String message;
    private static Object resultData;
    private static String time;

    public PinhengResponseBody(Response response) throws FrameworkException, IOException {
        if (null != response) {
            String json = response.body().string();
            LOGGER.info("返回的响应消息为" + json);
            JSONObject jsonObject = JSONObject.fromObject(json);

            //设置returnCode
            int actualReturnCode = (int)jsonObject.get("returnCode");
            setReturnCode(actualReturnCode);

            //设置message
            String actualMessage = jsonObject.get("message").toString();
            setMessage(actualMessage);

            //设置resultData
            Object resultData = jsonObject.get("resultData");
            setResultData(resultData);

            //设置time
            String actualTime = jsonObject.get("time").toString();
            setMessage(actualTime);

        }else{
            throw new FrameworkException("response为空");
        }
    }

    public static int getReturnCode() {
        return returnCode;
    }

    public static void setReturnCode(int returnCode) {
        PinhengResponseBody.returnCode = returnCode;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        PinhengResponseBody.message = message;
    }

    public static Object getResultData() {
        return resultData;
    }

    public static void setResultData(Object resultData) {
        PinhengResponseBody.resultData = resultData;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        PinhengResponseBody.time = time;
    }
}
