package Collections;

import Collections.Interfaces.CarMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashMap<K, V> implements CarMap<K, V> {
    private static final double LOAD_FACTOR = 0.75;
    private static final int INITIAL_CAPACITY = 16;
    private Object[] entries = new Object[INITIAL_CAPACITY];

    private int size = 0;

    private void shuffleArray() {
        Object[] doubledEntries = new Object[entries.length * 2];

        for (Object object : entries) {
            Entry entry = (Entry) object;
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
    public void put(K key, V value) {
        if (size >= entries.length * LOAD_FACTOR) {
            shuffleArray();
        }

        boolean succeed = put(entries, key, value);
        if (succeed) {
            size++;
        }
    }

    private boolean put(Object[] destination, K carOwner, V car) {
        int position = getElementPosition(destination.length, carOwner);
        Entry entry = new Entry(carOwner, car, null);

        if (destination[position] == null) {
            destination[position] = entry;
        } else {
            Entry existentElement = (Entry) destination[position];

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
    public V get(K carOwner) {
        int position = getElementPosition(entries.length, carOwner);
        Entry existentElement = (Entry) entries[position];

        while (existentElement != null) {
            if (existentElement.key.equals(carOwner)) {
                return existentElement.value;
            }
            existentElement = existentElement.next;
        }

        return null;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<>();

        for (Object object : entries) {
            Entry entry = (Entry) object;
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
    public List<V> values() {
        ArrayList<V> values = new ArrayList<>();

        for (Object object : entries) {
            Entry entry = (Entry) object;
            while (entry != null) {
                values.add(entry.value);
                entry = entry.next;
            }
        }

        return values;
    }

    @Override
    public boolean remove(K carOwner) {
        int position = getElementPosition(entries.length, carOwner);
        Entry element = (Entry) entries[position];
        if (element == null) {
            return false;
        } else {
            if (element.key.equals(carOwner)) {
                entries[position] = element.next;
                size--;
                return true;
            }

            Entry secondLast = (Entry) entries[position];
            Entry last = secondLast.next;

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
        entries = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private int getElementPosition(int hashTableCapacity, K key) {
        return Math.abs(key.hashCode() % hashTableCapacity);
    }

    private class Entry {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
