package Collections.Interfaces;

import Collections.CarMyList;
import Collections.Entities.Car;
import Collections.Interfaces.CarList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarListTest {

    private CarList carList;

    @Before
    public void setUp() throws Exception {
        carList = new CarMyList();

        for (int i = 0; i < 100; i++) {
            carList.add(new Car("brand " + i, i));
        }
    }

    @Test
    public void whenAdded100ElementsThenSizeMustBe100() {
        assertEquals(100, carList.size());
    }

    @Test
    public void whenElementRemovedByIndexThenSizeMustBeDecreased() {
        assertTrue(carList.removeAt(5));
        assertTrue(carList.removeAt(55));

        assertEquals(98, carList.size());
    }

    @Test
    public void whenElementRemovedByInstanceThenSizeMustBeDecreased() {
        Car carForDeletion = new Car("bmv", 3);
        carList.add(carForDeletion);
        assertEquals(101, carList.size());

        carList.remove(carForDeletion);
        assertEquals(100, carList.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse() {
        assertFalse(carList.remove(new Car("eqw", 7)));

        assertEquals(100, carList.size());
    }

    @Test
    public void whenListClearedThenItsSizeMustBeZero() {
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsThenThrowException() {
        carList.get(100);
    }

    @Test
    public void methodGetWorksCorrectly() {
        Car car = carList.get(9);

        assertEquals("brand 9", car.getBrand());
        assertEquals(9, car.getNumber());
    }

    @Test
    public void addIntoMiddleWorksCorrectly() {
        Car car = new Car("Tesla", 1);
        carList.add(car, 50);
        Car gotCar = carList.get(50);

        assertTrue(car.getBrand().equals(gotCar.getBrand()));
    }

    @Test
    public void addIntoLastWorksCorrectly() {
        Car car = new Car("Tesla", 1);
        carList.add(car, 99);
        Car gotCar = carList.get(99);

        assertTrue(car.getBrand().equals(gotCar.getBrand()));
    }

    @Test
    public void addIntoFirstWorksCorrectly() {
        Car car = new Car("Tesla", 1);
        carList.add(car, 0);
        Car gotCar = carList.get(0);

        assertTrue(car.getBrand().equals(gotCar.getBrand()));
    }
}