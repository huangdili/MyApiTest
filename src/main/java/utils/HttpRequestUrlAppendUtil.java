package utils;

import java.util.Properties;



public class HttpRequestUrlAppendUtil {

      public static String getHttpurl(String filePath, String appendUrl){
            String httpUrl = null;
            Properties properties = PropertiesFileReader.readProperties(filePath);
            String serverUrl = PropertiesFileReader.getProperty("server.url");
            httpUrl= serverUrl + appendUrl;
            return httpUrl;

      }
}
