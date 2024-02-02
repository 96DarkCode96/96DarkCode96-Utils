package eu.darkcode.utils.log;

public abstract class AbstractLogger implements Logger {

    private boolean debug;

    @Override
    public boolean isDebug() {
        return this.debug;
    }

    @Override
    public Logger setDebug(boolean state) {
        this.debug = state;
        return this;
    }

}