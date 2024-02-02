package eu.darkcode.utils.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Function;

public class FormatLogger extends AbstractLogger{

    public Function<LogLevel, String> format;

    public FormatLogger(Function<LogLevel, String> format) {
        this.format = format;
    }

    @Override
    public Logger log(String msg, LogLevel level, Throwable... throwable) {
        if(level.equals(LogLevel.DEBUG) && !isDebug()) return this;
        for (String s : msg.split("\n"))
            System.out.printf("\u001b[0m" + format.apply(level) + "\n\u001b[0m", s);
        for (Throwable thr : throwable) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            thr.printStackTrace(pw);
            error(sw.toString());
            pw.close();
        }
        return this;
    }
}
