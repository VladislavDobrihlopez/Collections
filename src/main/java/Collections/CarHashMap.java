package Collections;

import Collections.Entities.Car;
import Collections.Entities.CarOwner;
import Collections.Interfaces.CarMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap {
    private static final double LOAD_FACTOR = 0.75;
    private static final int INITIAL_CAPACITY = 16;
    private Entry[] entries = new Entry[INITIAL_CAPACITY];

    private int size = 0;

    private void shuffleArray() {
        Entry[] doubledEntries = new Entry[entries.length * 2];

        for (Entry entry : entries) {
            if (entry != null) {
                while (entry != null) {
                    put(doubledEntries, entry.key, entry.value);
                    entry = entry.next;
                }
            }
        }
        entries = doubledEntries;
    }

    @Override
    public void put(CarOwner carOwner, Car car) {
        if (size >= entries.length * LOAD_FACTOR) {
            shuffleArray();
        }

        boolean succeed = put(entries, carOwner, car);
        if (succeed) {
            size++;
        }
    }

    private boolean put(Entry[] destination, CarOwner carOwner, Car car) {
        int position = getElementPosition(destination.length, carOwner);
        Entry entry = new Entry(carOwner, car, null);

        if (destination[position] == null) {
            destination[position] = entry;
        } else {
            Entry existentElement = destination[position];

            while (existentElement != null) {
                if (existentElement.key.equals(carOwner)) {
                    existentElement.value = car;
                    return false;
                }
                if (existentElement.next == null) {
                    existentElement.next = entry;
                    return true;
                }
                existentElement = existentElement.next;
            }
        }
        return true;
    }

    @Override
    public Car get(CarOwner carOwner) {
        int position = getElementPosition(entries.length, carOwner);
        Entry existentElement = entries[position];

        while (existentElement != null) {
            if (existentElement.key.equals(carOwner)) {
                return existentElement.value;
            }
            existentElement = existentElement.next;
        }

        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        HashSet<CarOwner> keys = new HashSet<>();

        for (Entry entry : entries) {
            if (entry != null) {
                while (entry != null) {
                    keys.add(entry.key);
                    entry = entry.next;
                }
            }
        }

        return keys;
    }

    @Override
    public List<Car> values() {
        ArrayList<Car> values = new ArrayList<>();

        for (Entry entry : entries) {
            while (entry != null) {
                values.add(entry.value);
                entry = entry.next;
            }
        }

        return values;
    }

    @Override
    public boolean remove(CarOwner carOwner) {
        int position = getElementPosition(entries.length, carOwner);
        Entry element = entries[position];
        if (element == null) {
            return false;
        } else {
            if (element.key.equals(carOwner)) {
                entries[position] = element.next;
                size--;
                return true;
            }

            Entry secondLast = entries[position];
            Entry last = entries[position].next;

            while (last != null) {
                if (last.key.equals(carOwner)) {
                    secondLast.next = last.next;
                    size--;
                    return true;
                }
                secondLast = secondLast.next;
                last = last.next;
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
        entries = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private static int getElementPosition(int hashTableCapacity, CarOwner key) {
        return Math.abs(key.hashCode() % hashTableCapacity);
    }

    private static class Entry {
        private CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
