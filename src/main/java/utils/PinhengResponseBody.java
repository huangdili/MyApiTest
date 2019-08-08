package utils;

import meta.LogType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import okhttp3.Response;

import java.io.IOException;

public class PinhengResponseBody {

    private int returnCode;
    private String message;
    private Object resultData;
    private String time;
    private int resultCount;

    public PinhengResponseBody(Response response) throws FrameworkException, IOException {
        if (null != response) {
            String json = response.body().string();
            LogbackUtil.trace(PinhengResponseBody.class,LogType.BIZ,json);
            LogbackUtil.info("返回的响应消息为" + json);
            JSONObject jsonObject = JSONObject.fromObject(json);

            //设置returnCode
            int actualReturnCode = (int)jsonObject.get("returnCode");
            setReturnCode(actualReturnCode);

            //设置message
            String actualMessage = jsonObject.get("message").toString();
            setMessage(actualMessage);

            //设置resultData
            Object actualResultData = jsonObject.get("resultData");
            setResultData(actualResultData);

            //设置time
            String actualTime = jsonObject.get("time").toString();
            setTime(actualTime);

            //设置resultCount
            if (actualResultData.equals("null")) {
                setResultCount(0);
            } else {
                int actualResultCount = JSONArray.fromObject(actualResultData).size();
                setResultCount(actualResultCount);
            }

        }else{
            throw new FrameworkException("response为空");
        }
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(int actualReturnCode) {
        this.returnCode = actualReturnCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String actualMessage) {
        this.message = actualMessage;
    }

    public Object getResultData() {
        return this.resultData;
    }

    public void setResultData(Object actualResultData) {
        this.resultData = actualResultData;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String actualTime) {
       this.time = actualTime;
    }

    public int getResultCount() {
        return this.resultCount;
    }

    public void setResultCount(Integer actualResultCount) {
       this.resultCount = actualResultCount;
    }
}
