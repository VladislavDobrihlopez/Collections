package Collections;

import Collections.Entities.Car;
import Collections.Interfaces.CarSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CarHashSetReal implements CarSet {
    private final Map<Car, Object> hashMap = new HashMap<>();
    private final Object any = new Object();

    @Override
    public boolean add(Car car) {
        if (hashMap.containsKey(car)) {
            return false;
        }
        hashMap.put(car, any);
        return true;
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public boolean remove(Car car) {
        return hashMap.remove(car) != null;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public boolean contains(Car car) {
        return hashMap.containsKey(car);
    }

    @Override
    public Iterator<Car> iterator() {
        return hashMap.keySet().iterator();
    }
}
