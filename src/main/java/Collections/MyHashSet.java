package Collections;

import Collections.Interfaces.CarSet;

import java.util.Iterator;

public class MyHashSet<T> implements CarSet<T> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Object[] entries = new Object[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public boolean add(T item) {
        if (size >= entries.length * LOAD_FACTOR) {
            shuffleArray();
        }

        boolean isAdded = add(item, entries);
        if (isAdded) {
            size++;
        }
        return isAdded;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(T item) {
        int position = getElementPosition(entries.length, item);
        if (entries[position] == null) {
            return false;
        }

        Entry secondLast = (Entry) entries[position];
        Entry last = secondLast.next;

        while (last != null) {
            if (secondLast.value.equals(item)) {
                entries[position] = last;
                size--;
                return true;
            }

            if (last.value.equals(item)) {
                secondLast.next = last.next;
                size--;
                return true;
            }
            secondLast = last;
            last = last.next;

        }

        return false;
    }

    @Override
    public void clear() {
        entries = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void shuffleArray() {
        Object[] doubledEntries = new Object[entries.length * 2];
        for (Object entry : entries) {
            Entry element = (Entry) entry;
            while (element != null) {
                T item = element.value;
                add(item, doubledEntries);
                element = element.next;
            }
        }

        entries = doubledEntries;
    }

    private boolean add(T item, Object[] destination) {
        int position = getElementPosition(destination.length, item);

        Entry entry = new Entry(item, null);

        if (destination[position] == null) {
            destination[position] = entry;
        } else {
            Entry existentElement = (Entry) destination[position];

            if (existentElement.value.equals(item)) {
                return false;
            }

            while (existentElement.next != null) {
                if (existentElement.value.equals(item)) {
                    return false;
                }
                existentElement = existentElement.next;
            }
            existentElement.next = entry;
        }
        return true;
    }

    @Override
    public boolean contains(T item) {
        int position = getElementPosition(entries.length, item);
        Entry existentElement = (Entry) entries[position];

        while (existentElement != null) {
            if (existentElement.value.equals(item)) {
                return true;
            }
            existentElement = existentElement.next;
        }

        return false;
    }

    private int getElementPosition(int hashTableCapacity, T element) {
        return Math.abs(element.hashCode()) % hashTableCapacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int alreadyListed = 0;
            private int position = 0;
            private Entry current = null;

            @Override
            public boolean hasNext() {
                return alreadyListed < size;
            }

            @Override
            public T next() {
                for (int i = position; i < entries.length; i++) {
                    if (entries[i] != null) {
                        position = i;
                        break;
                    }
                }

                if (current == null) {
                    current = (Entry) entries[position];
                    alreadyListed++;
                    return current.value;
                }

                T value = current.value;
                current = current.next;

                if (current == null) {
                    position++;
                }

                alreadyListed++;

                return value;
            }
        };
    }

    private class Entry {
        private T value;
        private Entry next;

        public Entry(T value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
