package Collections;

import Collections.Entities.Car;
import Collections.Interfaces.CarList;

import java.util.Arrays;
import java.util.Iterator;

public class CarMyList implements CarList {
    private static final int INITIAL_CAPACITY = 10;
    private Car[] cars = new Car[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public Car get(int index) {
        checkIfIndexValid(index);

        return cars[index];
    }

    @Override
    public boolean add(Car car) {
        tryToIncreaseCapacity();

        cars[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        tryToIncreaseCapacity();

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(cars, index, cars, index + 1, size - index);
//        for (int i = size; i > index; i--) {
//            cars[i] = cars[i - 1];
//        }

        cars[index] = car;
        size++;
        return true;
    }

    @Override
    public boolean remove(Car car) {
        for (int i = 0; i < size; i++) {
            if (cars[i].equals(car)) {
                return removeAt(i);
            }
        }

        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIfIndexValid(index);

        System.arraycopy(cars, index + 1, cars, index, size - index - 1);
//        for (int i = index; i < size - 1; i++) {
//            cars[i] = cars[i + 1];
//        }

        size--;

        return true;
    }

    @Override
    public boolean contains(Car car) {
        for (int i = 0; i < size; i++) {
            if (cars[i].equals(car)) {
                return true;
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
        cars = new Car[INITIAL_CAPACITY];
        size = 0;
    }

    private void checkIfIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void tryToIncreaseCapacity() {
        if (size == cars.length) {
            cars = Arrays.copyOf(cars, cars.length * 2);
        }
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Car next() {
                return cars[index++];
            }
        };
    }
}
