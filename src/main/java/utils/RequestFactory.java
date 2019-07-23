package utils;

import net.sf.json.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.LogbackUtil.LOGGER;

public class RequestFactory {

    public static Response postRequest_Json(String url, Map<String,Object> bizParams) throws FrameworkException, IOException {
        String parameters;
        if(bizParams == null){
            throw new FrameworkException("传入参数为空");
        }
        //传入的map参数转换json
        JSONObject jsonObject = JSONObject.fromObject(bizParams);
        parameters = jsonObject.toString();

        if(url == null|| "".equalsIgnoreCase(url)){
            throw new FrameworkException("传入url为空");
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON,parameters);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();

        return response;
    }

}
