package Collections.Interfaces;

import Collections.Entities.Car;

public interface CarQueue extends CarCollection {
    public boolean add(Car car);

    public Car peek();

    public Car poll();
}
