package utils;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.security.auth.login.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.testng.Assert.assertTrue;
import static utils.LogbackUtil.LOGGER;

/**
 * @author huangdili
 *根据pinhengMakeTestcase.csv&pinhengTestcaseTemplate.ftl自动生成测试用例
 */

public class MakeInterfaceTestCaseUtil {
    private static String csvPath;
    private static String templateFile;
    private static String filePath = "makeTestcase.properties";
    private Configuration configuration;

    @BeforeClass
    public void init() {
        Properties properties = PropertiesFileReader.readProperties(filePath);
        csvPath = properties.getProperty("pinhengApiCsvPath");
        templateFile = properties.getProperty("pinhengTestcaseTemplate");

    }

    @Test(dataProvider = "parameters")
    public void makeInterfaceTestCaseUtil(String testcaseDir,String csvDir,String interface_name,String interface_description,
                                          String interfaceUrl,String param,String isRun){
        //如果isRun为Y，自动生成用例
        if("Y".toUpperCase().equals(isRun)){
            Map<String,String> map = new HashMap<String,String>();
            String packageDir = testcaseDir.replaceAll("/",".");
            String input_param = "";
            /**
             * 存在多种情况
             * param为空或null
             * param仅有一个参数
             * param有多个参数
             */
            if("" != param||param.length()>0){
                String[] paramArray = param.split("&");
                StringBuffer stringBuffer = new StringBuffer(input_param);
                if(paramArray.length>1){
                    for (String string:paramArray
                         ) {
                        stringBuffer.append("String").append(string).append(",");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length()-1);
                    input_param = stringBuffer.toString();
                }
            }
            map.put("interface_name",interface_name);
            map.put("input_param",input_param);
            map.put("testcaseDir", testcaseDir);
            map.put("interfaceUrl", interfaceUrl);
            map.put("packageDir", packageDir);
            map.put("csvDir", csvDir);

            String sourceFileName = interface_name + ".java";
            String sourceSavePath = testcaseDir + "/";
            Template template = configuration.getParameters()






        }else {//isRun为N，不执行自动生成
            assertTrue(false,"isRUN为N，未运行");
        }




    }

    @DataProvider(name = "parameters")
    public Object[][] parameters(){
        return CsvUtil.csvReader(csvPath);
    }

}
