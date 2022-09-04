package Collections;

import Collections.Entities.Car;
import Collections.Interfaces.CarSet;

import java.util.Iterator;

public class CarHashSet implements CarSet {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] entries = new Entry[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public boolean add(Car car) {
        if (size >= entries.length * LOAD_FACTOR) {
            shuffleArray();
        }

        boolean isAdded = add(car, entries);
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
    public boolean remove(Car car) {
        int position = getElementPosition(entries.length, car);
        if (entries[position] == null) {
            return false;
        }

        Entry secondLast = entries[position];
        Entry last = secondLast.next;

        while (last != null) {
            if (secondLast.value.equals(car)) {
                entries[position] = last;
                size--;
                return true;
            }

            if (last.value.equals(car)) {
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
        entries = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private void shuffleArray() {
        Entry[] doubledEntries = new Entry[entries.length * 2];
        for (Entry entry : entries) {
            Entry element = entry;
            while (element != null) {
                Car car = element.value;
                add(car, doubledEntries);
                element = element.next;
            }
        }

        entries = doubledEntries;
    }

    private boolean add(Car car, Entry[] destination) {
        int position = getElementPosition(destination.length, car);

        Entry entry = new Entry(car, null);

        if (destination[position] == null) {
            destination[position] = entry;
        } else {
            Entry existentElement = destination[position];

            if (existentElement.value.equals(car)) {
                return false;
            }

            while (existentElement.next != null) {
                if (existentElement.value.equals(car)) {
                    return false;
                }
                existentElement = existentElement.next;
            }
            existentElement.next = entry;
        }
        return true;
    }

    @Override
    public boolean contains(Car car) {
        int position = getElementPosition(entries.length, car);
        Entry existentElement = entries[position];

        while (existentElement != null) {
            if (existentElement.value.equals(car)) {
                return true;
            }
            existentElement = existentElement.next;
        }

        return false;
    }

    private int getElementPosition(int hashTableCapacity, Car element) {
        return Math.abs(element.hashCode()) % hashTableCapacity;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            private int alreadyListed = 0;
            private int position = 0;
            private Entry current = null;

            @Override
            public boolean hasNext() {
                return alreadyListed < size;
            }

            @Override
            public Car next() {
                for (int i = position; i < entries.length; i++) {
                    if (entries[i] != null) {
                        position = i;
                        break;
                    }
                }

                if (current == null) {
                    current = entries[position];
                    alreadyListed++;
                    return current.value;
                }

                Car value = current.value;
                current = current.next;

                if (current == null) {
                    position++;
                }

                alreadyListed++;

                return value;
            }
        };
    }

    private static class Entry {
        private Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
