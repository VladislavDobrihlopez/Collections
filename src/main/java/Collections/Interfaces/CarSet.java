package Collections.Interfaces;

import Collections.Entities.Car;

public interface CarSet extends CarCollection {
    public boolean add(Car car);

    public int size();

    public boolean remove(Car car);

    public boolean contains(Car car);

    public void clear();
}
