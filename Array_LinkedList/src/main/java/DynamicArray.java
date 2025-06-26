import java.util.Arrays;

public class DynamicArray<T> {
    private static final int DEFAULT_CAPACITY = 4;
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public DynamicArray() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void append(T value){
        if(size == data.length) resize(data.length*2);
        data[size++] = value;
    }

    public T get(int index){
        rangeCheck(index);
        return data[index];
    }

    public void set(int index, T value){
        rangeCheck(index);
        data[index] = value;
    }

    @SuppressWarnings("unchecked")
    public void resize(int newCapacity) {
        if(newCapacity < size) throw new IllegalArgumentException("새 용량이 현재 용량보다 작음");
        data = Arrays.copyOf(data, newCapacity);
    }

    public int size() {return size;}
    public int capacity() {return data.length;}

    private void rangeCheck(int index){
        if(index<0 || index >=size) throw new IndexOutOfBoundsException(index + "out of range");
    }

    public void printArray() {
        System.out.print("DynamicArray: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
