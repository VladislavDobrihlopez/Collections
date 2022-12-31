package Collections;

import Collections.Interfaces.CarSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyHashSetReal<K> implements CarSet<K> {
    private final Map<K, Object> hashMap = new HashMap<>();
    private final Object any = new Object();

    @Override
    public boolean add(K item) {
        if (hashMap.containsKey(item)) {
            return false;
        }
        hashMap.put(item, any);
        return true;
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public boolean remove(K item) {
        return hashMap.remove(item) != null;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public boolean contains(K item) {
        return hashMap.containsKey(item);
    }

    @Override
    public Iterator<K> iterator() {
        return hashMap.keySet().iterator();
    }
}
