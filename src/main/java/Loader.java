import buffer.BufferCircular;

import java.nio.Buffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Buffer buffer = new BufferCircular();

        executorService.execute(new Produtor(buffer));
        executorService.execute(new Consumidor(buffer));

        executorService.shutdown();
    }
}
