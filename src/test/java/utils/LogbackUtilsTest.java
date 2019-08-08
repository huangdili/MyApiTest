package utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class LogbackUtilsTest {

    @Test(dataProvider = "LoggerFile")
    public void logbackUtilsTest(String inputString, String expectedString){
        assertTrue(true);
    }

    @DataProvider(name = "LoggerFile")
    public Object[][] LoggerFile() throws IOException {

        Object[][] parameters = CsvUtil.csvReader("src/test/TestDatas/LogbackUtilTest.CSV");

        return parameters;
    }




}