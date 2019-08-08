package utils;

import okhttp3.HttpUrl;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class HttpRequestUrlAppendUtil {

      public static String getHttpurl(String filePath, String appendUrl){
            String httpUrl = null;
            Properties properties = PropertiesFileReader.readProperties(filePath);
            String serverUrl = properties.getProperty("server.url");
            httpUrl= serverUrl + appendUrl;
            LogbackUtil.info(httpUrl);
            return httpUrl;
      }

      public static String appendUrl(String url, String...appendUrl){
            StringBuilder stringBuilder = new StringBuilder(url);
            for (String s :appendUrl
                 ) {
               url = stringBuilder.append(appendUrl).toString();
            }
            return url;
      }

      public static HttpUrl appendGetUrl(String url, Map<String,Object> bizParams){


            HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

            Set<Map.Entry<String,Object>> set = bizParams.entrySet();

            for (Map.Entry<String,Object> entry : set
                 ) {
                  urlBuilder.setQueryParameter(entry.getKey(),entry.getValue().toString());
            }

            return urlBuilder.build();
      }
}
