import eu.darkcode.utils.action.Action;
import eu.darkcode.utils.action.ActionImpl;
import eu.darkcode.utils.log.DefaultFormatLogger;
import eu.darkcode.utils.log.Logger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = new DefaultFormatLogger("Main");

        Action<?> nehehe = new ActionImpl<>(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("Nehehe");
        }).fail((thr) -> logger.error("Error while action", thr));

        nehehe.run();
        nehehe.queue();

        logger.info("Test info")
                .warn("Test warn")
                .error("Test error", new Throwable("Čau"))
                .debug("TEST")
                .setDebug(true)
                .debug("TEST #2");

        Thread.sleep(2000);
    }
}