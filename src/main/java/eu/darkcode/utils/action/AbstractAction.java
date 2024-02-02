package eu.darkcode.utils.action;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractAction<R> implements Action<R> {

    private @Nullable Consumer<Throwable> onFailure;
    private final @NotNull Supplier<R> function;

    public AbstractAction(@NotNull Runnable runnable) {
        this.function = () -> {
            runnable.run();
            return null;
        };
    }

    public AbstractAction(@NotNull Supplier<R> function) {
        this.function = function;
    }

    @Override
    public void queue(@Nullable Executor executor, @NotNull Consumer<R> result) {
        (executor == null ? CompletableFuture.supplyAsync(function) : CompletableFuture.supplyAsync(function, executor)).whenCompleteAsync((r, throwable) -> {
            if(throwable != null){
                if(onFailure == null) throw new RuntimeException(throwable);
                onFailure.accept(throwable);
            }else{
                result.accept(r);
            }
        });
    }

    @Override
    public R run() {
        try{
            return function.get();
        }catch(Throwable throwable){
            if(onFailure == null) throw new RuntimeException(throwable);
            onFailure.accept(throwable);
        }
        return null;
    }

    @Override
    public Action<R> fail(@Nullable Consumer<Throwable> handler) {
        onFailure = handler;
        return this;
    }

    @Override
    public <T2> Action<T2> then(@NotNull Function<R, Action<T2>> mapper) {
        return new ActionImpl<>(() -> {
            Action<T2> apply = mapper.apply(this.fail(null).run());
            if(apply == null) return null;
            return apply.run();
        });
    }

    @Override
    public <T2> Action<T2> and(@NotNull Action<T2> action) {
        return new ActionImpl<>(() ->{
            this.fail(null).run();
            return action.run();
        });
    }
}