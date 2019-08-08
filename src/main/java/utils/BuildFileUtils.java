package utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;


public class BuildFileUtils {
    public static void build(Map<String,String> map, String sourceSavePath, String sourceFileName, Template sourceTempalte) throws IOException {
        String filename = sourceSavePath + sourceFileName;

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
}
