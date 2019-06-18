package buffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferBlockQueue implements Buffer {

    private final BlockingQueue<Integer> queue;

    public BufferBlockQueue() {
        this.queue = new ArrayBlockingQueue<>(3);
    }

    @Override
    public void set(int valor) {
        try {
            queue.put(valor);
            System.out.println("Produtor grava " + valor);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int get() {
        int valorLido = 0;
        try {
            valorLido = queue.take();
            System.out.println("Consumidor lê " + valorLido);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return valorLido;
    }
}
