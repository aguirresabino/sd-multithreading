package buffer;

public class BufferSimples implements Buffer{

    private Integer buffer = -1;

    @Override
    public void set(int value) {
        System.out.println("Produtor grava " + value);
        this.buffer = value;
    }

    @Override
    public int get() {
        System.out.println("Consumidor lê " + buffer.toString());
        return buffer;
    }

}
