package utils;

import net.sf.json.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class RequestFactory {

    public static  Response postRequest_Json(String url, Map<String,Object> bizParams) throws FrameworkException, IOException {

        //检查入参
        checkParameters(url,bizParams);

        //传入的map参数转换json
        String parameters;
        JSONObject jsonObject = JSONObject.fromObject(bizParams);
        parameters = jsonObject.toString();
        //设置消息体格式
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //设置消息体
        RequestBody requestBody = RequestBody.create(JSON,parameters);

        Response response = executePostRequest(url,requestBody);

        return response;

    }

    public static  Response postRequest_Form(String url, Map<String,Object> bizParams) throws FrameworkException, IOException {

        checkParameters(url,bizParams);

        FormBody.Builder builder = new FormBody.Builder();

        for (String key : bizParams.keySet()) {
            //追加表单信息
            builder.add(key, (String) bizParams.get(key));
        }

        RequestBody formBody=builder.build();

        Response response = executePostRequest(url,formBody);

        return response;
    }


    public static  Response getRequest(String url, Map<String,Object> bizParams) throws FrameworkException, IOException {

        checkParameters(url,bizParams);

        HttpUrl httpUrl = HttpRequestUrlAppendUtil.appendGetUrl(url,bizParams);

        LogbackUtil.info(httpUrl.toString());

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();

        return response;

    }

    private static void checkParameters(String url, Map<String,Object> bizParams) throws FrameworkException {

        if(bizParams == null){
            throw new FrameworkException("传入参数为空");
        }

        if(url == null|| "".equalsIgnoreCase(url)){
            throw new FrameworkException("传入url为空");
        }

    }

    private static Response executePostRequest(String url,RequestBody requestBody) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        LogbackUtil.info(request.toString());
        //调用参数
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();

        return response;

    }





}
