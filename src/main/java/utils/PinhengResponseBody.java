package utils;

import net.sf.json.JSONObject;
import okhttp3.Response;

import java.io.IOException;

public class PinhengResponseBody {

    private int returnCode;
    private String message;
    private Object resultData;
    private String time;


    public int getReturnCode(Response response) throws IOException, FrameworkException {
        this.returnCode = (int)getResultByKey(response,"returnCode");
        return returnCode;
    }

    public String getMessage(Response response) throws IOException, FrameworkException {
        this.message = (String)getResultByKey(response,"message");
        return message;
    }

    public Object getResultData(Response response) throws IOException, FrameworkException {
        this.resultData = getResultByKey(response,"resultData");
        return resultData;
    }

    public String getTime(Response response) throws IOException, FrameworkException {
        this.time = (String)getResultByKey(response,"time");
        return time;
    }


    public Object getResultByKey(Response response, String key) throws IOException, FrameworkException {
        if (null != response) {
            String json = response.body().string();
            JSONObject jsonObject = JSONObject.fromObject(json);
            Object value = jsonObject.get(key);
            return value;
        }else{
            throw new FrameworkException("response为空");
        }
    }
}
