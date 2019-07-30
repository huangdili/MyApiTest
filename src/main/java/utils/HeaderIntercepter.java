package utils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;

import static utils.LogbackUtil.LOGGER;

/**
 * @author  huangdili
 * 暂时没有使用上
 */


public class HeaderIntercepter implements Interceptor {


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        long beginTime = System.nanoTime();
        LOGGER.info(String.format("Sending request %s on %s%n%s", request.url(),chain.connection(),request.headers()));
        Response response = chain.proceed(request);
        long endTime = System.nanoTime();
        LOGGER.info(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(),(endTime-beginTime)/1e6d,response.headers()));
        return response;

    }

    public static void main(String[] args) throws IOException {
        String CONTENT_TYPE = "application/json:charset=UTF-8";
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderIntercepter())
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8088/shopjsp_b2b2c_api_war/app/panicBuying/loadPanicBuyingByPanicId?panicId=74")
                .get()
                .header("Content-Type",CONTENT_TYPE)
                .header("User-Agent","Okhttp Example")
                .build();

        Response response = client.newCall(request).execute();
        LOGGER.info(response.body().toString());
        response.body().close();

    }
}
