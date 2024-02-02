package eu.darkcode.utils.action;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Action<R> {

    R run();

    default void queue(){
        queue(null);
    }

    default void queue(@Nullable Executor executor){
        queue(executor, (e) -> {});
    }

    void queue(@Nullable Executor executor, @NotNull Consumer<R> result);

    Action<R> fail(Consumer<Throwable> handler);

    <T2> Action<T2> then(Function<R, Action<T2>> mapper);

    <T2> Action<T2> and(Action<T2> action);

    default Action<Void> thenConsume(Consumer<R> consumer){
        return then(r -> {
            consumer.accept(r);
            return null;
        });
    }

}