package eu.darkcode.utils.action;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ActionImpl<R> extends AbstractAction<R>{
    public ActionImpl(@NotNull Runnable runnable) {
        super(runnable);
    }
    public ActionImpl(@NotNull Supplier<R> function) {
        super(function);
    }
}
