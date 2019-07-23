package utils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huagndili
 * 读取CSV中的测试数据，返回一个Object[][]的数组，一行代表一组测试数据
 */

public class CsvUtil {

        public static Object[][] csvReader(String filePath){
            //获取行数
            String line;
            List<String[]> lists = new ArrayList<String[]>();
            BufferedReader bufferedReader;

            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                bufferedReader.readLine();
                while((line=bufferedReader.readLine())!= null){
                    String[] datas = line.split(",");
                    //去掉序号
                    datas = ArrayUtils.removeElement(datas,datas[0]);
                    for (int i = 0; i < datas.length; i++) {
                        datas[i] = datas[i].replaceAll("##",",");
                    }
                    lists.add(datas);
                }
                bufferedReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Object[][] objects = new Object[lists.size()][];
            for (int i = 0; i < lists.size(); i++) {
                objects[i] = lists.get(i);
            }
            return objects;

        }

}
