package eu.darkcode.utils.log;

public class DefaultFormatLogger extends FormatLogger{
    public DefaultFormatLogger(String tag) {
        super((level) -> switch (level) {
            case INFO -> "\u001b[36m[INFO] ["+tag+"] %s";
            case WARN -> "\u001b[33m[WARN] ["+tag+"] %s";
            case ERROR -> "\u001b[40m\u001b[31m[ERROR] ["+tag+"] %s";
            case DEBUG -> "\u001b[32m[DEBUG] ["+tag+"] %s";
        });
    }
}
