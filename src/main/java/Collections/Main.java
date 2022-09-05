package Collections;

import Collections.Entities.Car;
import Collections.Interfaces.CarCollection;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        CarCollection cars = new CarMyList();
        cars.add(new Car("car", 1));
        cars.add(new Car("car2", 2));
        cars.add(new Car("car3", 3));

        Iterator<Car> iterator = cars.iterator();

        while (iterator.hasNext()) {
            Car currentCar = iterator.next();
            System.out.println(currentCar);
        }
    }
}
