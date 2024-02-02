package eu.darkcode.utils.log;

public interface Logger {

    Logger log(String msg, LogLevel level, Throwable...throwable);

    boolean isDebug();
    Logger setDebug(boolean state);

    default Logger info(String msg, Throwable...throwable){
        return log(msg, LogLevel.INFO, throwable);
    }

    default Logger warn(String msg, Throwable...throwable){
        return log(msg, LogLevel.WARN, throwable);
    }

    default Logger error(String msg, Throwable...throwable){
        return log(msg, LogLevel.ERROR, throwable);
    }

    default Logger debug(String msg, Throwable...throwable){
        return log(msg, LogLevel.DEBUG, throwable);
    }
}