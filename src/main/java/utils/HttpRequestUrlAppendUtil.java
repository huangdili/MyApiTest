package utils;

import java.util.Properties;

import static utils.LogbackUtil.LOGGER;


public class HttpRequestUrlAppendUtil {

      public static String getHttpurl(String filePath, String appendUrl){
            String httpUrl = null;
            Properties properties = PropertiesFileReader.readProperties(filePath);
            String serverUrl = PropertiesFileReader.getProperty("server.url");
            httpUrl= serverUrl + appendUrl;
            LOGGER.info(httpUrl);
            return httpUrl;

      }
}
