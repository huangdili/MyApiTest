package utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

/**
 * @author huangdili
 *根据CsvTemplate自动生成测试用例CSV文件
 */

public class MakeTestcaseCSVUtil {
    private static String csvPath;
    private static String templateFile;
    private static String filePath = "makeTestcase.properties";
    private Configuration configuration;

    @BeforeClass
    public void init() {
        Properties properties = PropertiesFileReader.readProperties(filePath);
        csvPath = properties.getProperty("pinhengApiTestcaseCsv");
        templateFile = properties.getProperty("pinhengApiCsvTemplate");
        configuration = new Configuration();
    }

    @Test(dataProvider = "parameters")
    public void makeTestcaseCSVUtil(String csvDir,String interface_name,String param,String isRun) throws IOException {
        //如果isRun为Y，自动生成用例
        if("Y".toUpperCase().equals(isRun)){
            Map<String,String> map = new HashMap<String,String>();
            StringBuffer classNameBuffer = new StringBuffer();
            String input_param = "";
            if("" != param||param.length()>0){
                String[] paramArray = param.split("&");
                //用于用例中，入参声明
                StringBuffer stringBuffer = new StringBuffer(input_param);

                if(paramArray.length>=1){
                    for (String string:paramArray
                         ) {
                        stringBuffer.append(string).append(",");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length()-1);
                    input_param = stringBuffer.toString();
                    LogbackUtil.info(input_param);
                }
            }
            map.put("input_param",input_param);

            //配置文件后缀名
            String sourceFileName = interface_name + ".CSV";
            //配置生成路径
            String sourceSavePath = csvDir + "/";
            //设置模板
            Template sourceTemplate = configuration.getTemplate(templateFile);
            //调用函数生成用例
            BuildFileUtils.build(map,sourceSavePath,sourceFileName,sourceTemplate);

        }else {//isRun为N，不执行自动生成
            assertTrue(false,"isRUN为N，未运行");
        }
    }

    @DataProvider(name = "parameters")
    public Object[][] parameters(){
        return CsvUtil.csvReader(csvPath);
    }

}
