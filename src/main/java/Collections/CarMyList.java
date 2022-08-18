package Collections;

import java.util.Arrays;

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
    public void add(Car car) {
        tryToIncreaseCapacity();

        cars[size] = car;
        size++;
    }

    @Override
    public void add(Car car, int index) {
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
}