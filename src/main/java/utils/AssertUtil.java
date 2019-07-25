package utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import static utils.LogbackUtil.LOGGER;

/**
 * @author huangdili
 */

public class AssertUtil {

    /**
     * 判断Json数组中是否存在某个关键字，并比对它的值，存在返回true,不存在返回false
     * @param data
     * @param key
     * @param value
     * @return
     * @throws FrameworkException
     */

    public static boolean isDataExist(JSONArray data, String key, String value) throws FrameworkException {
        if(data.size()>0) {
            for (int i = 0; i < data.size(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                String actualValue = jsonObject.getString(key);

                if(actualValue.equalsIgnoreCase(value)){
                    return true;
                }
            }
            return false;
        }else {
            throw new FrameworkException("data为空");
        }
    }

}
