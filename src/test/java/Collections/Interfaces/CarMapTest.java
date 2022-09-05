package Collections.Interfaces;

import Collections.CarHashMap;
import Collections.Entities.Car;
import Collections.Entities.CarOwner;
import Collections.Interfaces.CarMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarMapTest {
    private static final int INITIAL_NUMBER_OF_ITEMS = 100;

    private CarMap carMap = new CarHashMap();

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < INITIAL_NUMBER_OF_ITEMS; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
    }

    @Test
    public void testGet() {
        CarOwner carOwner = new CarOwner(57, "Name57", "LastName57");
        assertEquals(57, carMap.get(carOwner).getNumber());
    }

    @Test
    public void testRemovingAtBounds() {
        CarOwner firstOwner = new CarOwner(0, "Name0", "LastName0");
        CarOwner lastOwner = new CarOwner(99, "Name99", "LastName99");

        carMap.remove(firstOwner);
        carMap.remove(lastOwner);

        assertEquals(INITIAL_NUMBER_OF_ITEMS - 2, carMap.size());

        assertNull(carMap.get(firstOwner));
        assertNull(carMap.get(lastOwner));
    }

    @Test
    public void whenElementsAddedThenSizeMustBeIncreased() {
        assertEquals(INITIAL_NUMBER_OF_ITEMS, carMap.size());
    }

    @Test
    public void whenPut5000ElementsWithOnly50DifferentKeysThenSizeMustBeIncreasedBy50() {
        carMap.clear();
        for (int i = 0; i < 5000; i++) {
            int index = i % 50;
            CarOwner carOwner = new CarOwner(index, "Somebody" + index, "Somebody" + index);
            Car car = new Car("SomeBrand" + index, index);
            carMap.put(carOwner, car);
        }

        assertEquals(50, carMap.size());
    }

    @Test
    public void whenElementWithAlreadyExistentKeyAddedThenValueMustBeOverridden() {
        CarOwner carOwner = new CarOwner(57, "Name" + 57, "LastName" + 57);
        carMap.put(carOwner, new Car("Toyota", 111));
        Car car = carMap.get(carOwner);

        assertEquals("Toyota", car.getBrand());
    }

    @Test
    public void whenTryingToRemoveElementWithNonExistentKeyThenMustReturnFalse() {
        CarOwner carOwner = new CarOwner(INITIAL_NUMBER_OF_ITEMS + 1, "somebody", "somebody");
        assertFalse(carMap.remove(carOwner));
    }

    @Test
    public void whenElementsAreRemovedThenSizeMustBeDecreased() {
        CarOwner carOwner1 = new CarOwner(5, "Name" + 5, "LastName" + 5);
        CarOwner carOwner2 = new CarOwner(7, "Name" + 7, "LastName" + 7);
        CarOwner carOwner3 = new CarOwner(50, "Name" + 50, "LastName" + 50);
        assertTrue(carMap.remove(carOwner1));
        assertFalse(carMap.remove(carOwner1));
        assertTrue(carMap.remove(carOwner2));
        assertTrue(carMap.remove(carOwner3));

        assertEquals(INITIAL_NUMBER_OF_ITEMS - 3, carMap.size());
    }

    @Test
    public void testKeySet() {
        assertEquals(carMap.size(), carMap.keySet().size());
    }

    @Test
    public void testValues() {
        assertEquals(carMap.size(), carMap.values().size());
    }

    @Test
    public void whenCollectionIsClearedThenItsSizeMustBeZero() {
        carMap.clear();
        assertEquals(0, carMap.size());
    }
}