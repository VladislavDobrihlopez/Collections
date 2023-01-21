package Collections.Interfaces;

import java.util.List;
import java.util.Set;

public interface CarMap<K, V> {
    public void put(K key, V value);

    public V get(K key);

    public Set<K> keySet();

    public List<V> values();

    public boolean remove(K key);

    public int size();

    public void clear();
}
