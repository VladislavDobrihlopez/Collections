package Collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarCollectionTest {
    private static final int INITIAL_CAPACITY = 100;
    private CarCollection carCollection;

    @Before
    public void setUp() throws Exception {
        carCollection = new CarHashSet();

        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            carCollection.add(new Car("brand " + i, i));
        }
    }

    @Test
    public void whenElementsAddedThenSizeMustBeIncreased() {
        assertEquals(INITIAL_CAPACITY, carCollection.size());
    }

    @Test
    public void whenCollectionIsClearedThenItsSizeMustBeZero() {
        carCollection.clear();
        assertEquals(0, carCollection.size());
    }

    @Test
    public void whenElementsRemovedThenSizeMustBeDecreased() {
        carCollection.remove(new Car("brand 1", 1));
        carCollection.remove(new Car("brand 21", 21));
        carCollection.remove(new Car("brand 70", 70));
        assertEquals(INITIAL_CAPACITY - 3, carCollection.size());
    }

    @Test
    public void mustReturnTrueForExistentElement() {
        Car car = new Car("brand 7", 7);
        assertTrue(carCollection.contains(car));
    }

    @Test
    public void mustReturnFalseForNonExistentElement() {
        Car car = new Car("someNonExistentCar", 7);
        assertFalse(carCollection.contains(car));
    }
}