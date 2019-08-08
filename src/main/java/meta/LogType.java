package meta;

public enum LogType {

    /**业务类型日志*/
    BIZ("BIZ"),
    /**request调用类型日志*/
    REQUEST("REQUEST"),
    /**断言调用类型日志*/
    ASSERT("ASSERT");

    public String getLogType() {
        return logType;
    }

    private String logType;

    private LogType(String logType){
        this.logType = logType;
    }


}
