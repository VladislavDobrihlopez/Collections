package Collections;

import Collections.Interfaces.CarList;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements CarList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] items = new Object[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public T get(int index) {
        checkIfIndexValid(index);

        return (T) items[index];
    }

    @Override
    public boolean add(T item) {
        tryToIncreaseCapacity();

        items[size] = item;
        size++;
        return true;
    }

    @Override
    public boolean add(T item, int index) {
        tryToIncreaseCapacity();

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
//        for (int i = size; i > index; i--) {
//            items[i] = items[i - 1];
//        }

        items[index] = item;
        size++;
        return true;
    }

    @Override
    public boolean remove(T item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return removeAt(i);
            }
        }

        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIfIndexValid(index);

        System.arraycopy(items, index + 1, items, index, size - index - 1);
//        for (int i = index; i < size - 1; i++) {
//            items[i] = items[i + 1];
//        }

        size--;

        return true;
    }

    @Override
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        items = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void checkIfIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void tryToIncreaseCapacity() {
        if (size == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) items[index++];
            }
        };
    }
}
