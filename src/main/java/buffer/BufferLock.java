package buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLock implements Buffer {

    private Lock lock = new ReentrantLock(false);
    private Condition podeLer = lock.newCondition();
    private Condition podeEscrever = lock.newCondition();

    private Integer buffer = -1;
    private Boolean empty = true;

    @Override
    public void set(int valor) {
        lock.lock();

        try {
            while (!empty) {
                System.out.println("Produtor tenta gravar, mas o buffer está cheio");
                podeEscrever.await();
            }

            buffer = valor;
            System.out.println("Produtor grava " + buffer);
            empty = false;
            podeLer.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public int get() {
        lock.lock();

        try {

            while (empty) {
                System.out.println("Consumidor tenta consumir, mas o buffer está vazio");
                podeLer.await();
            }

            empty = true;
            System.out.println("Consumidor lê : " + buffer);
            podeEscrever.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return buffer;
    }
}
