import java.io.Serializable;

public class Column implements Serializable {
    private final int[] list;
    private final int capacity;
    private int size;

    public Column(int capacity) {
        this.size = 0;
        this.list = new int[capacity];
        this.capacity = capacity;
        for (int i = 0; i < capacity; i++) {
            this.list[i] = 0;
        }
    }

    public boolean push(int number) {
        if (this.size == this.capacity) {
            return false;
        }

        this.list[this.size] = number;
        this.size ++;
        return true;
    }

    public int[] getList() {
        return this.list.clone();
    }

    public int capacity() {
        return this.capacity;
    }
}
