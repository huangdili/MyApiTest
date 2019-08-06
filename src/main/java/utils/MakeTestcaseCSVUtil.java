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
        configuration = new Configuration();
    }

    @Test(dataProvider = "parameters")
    public void makeInterfaceTestCaseUtil(String testcaseDir,String csvDir,String interface_name,String interface_description,
                                          String interfaceUrl,String param,String isRun) throws IOException {
        //如果isRun为Y，自动生成用例
        if("Y".toUpperCase().equals(isRun)){
            Map<String,String> map = new HashMap<String,String>();
            String packageDir = testcaseDir.replaceAll("/",".");
            packageDir=packageDir.replaceAll("src.test.java.","");
            StringBuffer classNameBuffer = new StringBuffer();
            String className = classNameBuffer.append(Character.toUpperCase(interface_name.charAt(0)))
                    .append(interface_name.substring(1)).append("Test").toString();
            LOGGER.info(className);
            String input_param = "";
            String map_builder = "";
            /**
             * 存在多种情况
             * param为空或null
             * param仅有一个参数
             * param有多个参数
             */
            if("" != param||param.length()>0){
                String[] paramArray = param.split("&");
                //用于用例中，入参声明
                StringBuffer stringBuffer = new StringBuffer(input_param);
                //用于用例中map赋值
                StringBuffer mapStringBuffer = new StringBuffer(map_builder);

                if(paramArray.length>=1){
                    for (String string:paramArray
                         ) {
                        stringBuffer.append("String").append(" ").append(string).append(",");
                        //前端留出多余空格，为对齐格式
                        mapStringBuffer.append("            ").append("map.put(\"").append(string).append("\"").append(",")
                                .append(string).append(");").append("\n");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length()-1);
                    input_param = stringBuffer.toString();
                    map_builder = mapStringBuffer.toString();
                    LOGGER.info(input_param);
                    LOGGER.info(map_builder);
                }
            }
            map.put("interface_name",interface_name);
            map.put("input_param",input_param);
            map.put("testcaseDir", testcaseDir);
            map.put("interfaceUrl", interfaceUrl);
            map.put("packageDir", packageDir);
            map.put("csvDir", csvDir);
            map.put("map_builder",map_builder);
            map.put("className",className);
            //配置文件后缀名
            String sourceFileName = className + ".java";
            //配置生成路径
            String sourceSavePath = testcaseDir + "/";
            //设置模板
            Template sourceTemplate = configuration.getTemplate(templateFile);
            //调用函数生成用例
            MakeInterfaceTestCaseUtil.build(map,sourceSavePath,sourceFileName,sourceTemplate);

        }else {//isRun为N，不执行自动生成
            assertTrue(false,"isRUN为N，未运行");
        }
    }

    public static void build(Map<String,String> map,String sourceSavePath,String sourceFileName,Template sourceTempalte) throws IOException {
        String filename = sourceSavePath + sourceFileName;
        LOGGER.info(filename);
        File directory = new File(sourceSavePath);
        if(!directory.exists()){
            directory.mkdir();
        }
        File file = new File(sourceSavePath);
        file.createNewFile();
        try{
            Writer writer = new OutputStreamWriter(new FileOutputStream(filename));
            sourceTempalte.process(map,writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @DataProvider(name = "parameters")
    public Object[][] parameters(){
        return CsvUtil.csvReader(csvPath);
    }

}
