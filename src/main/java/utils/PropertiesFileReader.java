package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author huangdili
 * 读取配置文件参数
 */
public class PropertiesFileReader {
    //单例模式
    private static PropertiesFileReader propertiesFileReader = null;
    static Properties properties = null;
    static InputStream in = null;
    //私有化构造器
    private PropertiesFileReader(){

    }
    //懒加载
    public static synchronized PropertiesFileReader getInstance(){
        if(propertiesFileReader == null){
            propertiesFileReader = new PropertiesFileReader();
        }
        return propertiesFileReader;
    }

    /**
     * 通过配置项路径获取配置项列表
     * @param name 需要读取的配置文件路径
     * @return
     */
    public static Properties readProperties(String name){
        properties = new Properties();
        //名称不为空时，读取配置项
        if(null != name){
            in = properties.getClass().getResourceAsStream("/" + name);
            try {
                properties.load(new InputStreamReader(in, "utf-8"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return properties;
    }

    /**
     * 根据关键字获取指定配置项的值
     * @param key 配置项关键字
     * @return 返回String类型的配置项的值
     */

    public static String getProperty(String key){
        String value = null;
        if(null != key && !"".equalsIgnoreCase(key)){
            value = (String)properties.getProperty(key);
        }
        return value;
    }

    /**
     * 根据关键字获取指定配置项的值
     * @param key 配置项关键字
     * @return 返回int类型的配置项的值
     */
    public static int getPropertyInt(String key) {
        String value = null;
        if (null != key && !"".equalsIgnoreCase(key)) {
            value = (String) properties.get(key);
        }
        return Integer.valueOf(value);
    }

    public static String getQuery(String query) {
        String value = null;
        if (null != query && !"".equalsIgnoreCase(query)) {
            value = (String) properties.get(query);
        }
        return value;
    }



}
