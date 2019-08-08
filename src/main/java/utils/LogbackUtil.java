package utils;

import meta.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangdili
 * 日志处理文件
 */

public class LogbackUtil {

    private LogbackUtil(){}

    public static void trace(Class<?> classInfo,LogType logType, String message){
        Logger logger = getLogger(logType);
        logger.info(classInfo.getSimpleName() + " - " + message);

    }
    public static void info(Class<?> classInfo,LogType logType, String message){
        Logger logger = getLogger(logType);
        logger.info(classInfo.getSimpleName() + " - " + message);
    }

    public static void info(String message){
        Logger logger = LoggerFactory.getLogger(LogbackUtil.class);
        logger.info(message);
    }


    public static void warn(Class<?> classInfo,LogType logType, String message){
        Logger logger = getLogger(logType);
        logger.warn(classInfo.getSimpleName() + " - " + message);
    }

    public static void debug(Class<?> classInfo,LogType logType, String message){
        Logger logger = getLogger(logType);
        logger.debug(classInfo.getSimpleName() + " - " + message);
    }

    public static void error(Class<?> classInfo,LogType logType, String message){
        Logger logger = getLogger(logType);
        logger.error(classInfo.getSimpleName() + " - " + message);
    }


    public static Logger getLogger(LogType logType){
        Logger logger = null;
        switch(logType){
            case BIZ:
                logger = LoggerFactory.getLogger("Biz");
                break;
            case REQUEST:
                logger = LoggerFactory.getLogger("Request");
                break;
            case ASSERT:
                logger = LoggerFactory.getLogger("Assert");
        }
        return logger;
    }

}
