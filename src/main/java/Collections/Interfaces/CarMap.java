package Collections.Interfaces;

import Collections.Entities.Car;
import Collections.Entities.CarOwner;

import java.util.List;
import java.util.Set;

public interface CarMap {
    public void put(CarOwner carOwner, Car car);

    public Car get(CarOwner carOwner);

    public Set<CarOwner> keySet();

    public List<Car> values();

    public boolean remove(CarOwner carOwner);

    public int size();

    public void clear();
}
