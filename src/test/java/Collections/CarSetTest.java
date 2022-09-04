package Collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarSetTest {
    private static final int INITIAL_NUMBER_OF_ITEMS = 100;
    private CarSet carSet;

    @Before
    public void setUp() throws Exception {
        carSet = new CarHashSet();

        for (int i = 0; i < INITIAL_NUMBER_OF_ITEMS; i++) {
            carSet.add(new Car("brand " + i, i));
        }
    }

    @Test
    public void whenAddSeveralSimilarInstancesThenOnlyOneAdded() {
        assertTrue(carSet.add(new Car("SomeCar", 123)));
        assertFalse(carSet.add(new Car("SomeCar", 123)));
        assertFalse(carSet.add(new Car("SomeCar", 123)));
        assertEquals(INITIAL_NUMBER_OF_ITEMS + 1, carSet.size());
    }

    @Test
    public void whenElementRemovedByInstanceThenSizeMustBeDecreased() {
        Car car = new Car("brand 50", 50);
        assertTrue(carSet.remove(car));
        assertEquals(INITIAL_NUMBER_OF_ITEMS - 1, carSet.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse() {
        assertFalse(carSet.remove(new Car("nonExistentBrand", 7)));
        assertEquals(INITIAL_NUMBER_OF_ITEMS, carSet.size());
    }

    @Test
    public void when100ElementsAddedThenSizeMustBe150() {
        assertEquals(100, carSet.size());
    }

    @Test
    public void whenSetClearedThenSizeMustBe0() {
        carSet.clear();
        assertEquals(0, carSet.size());
    }
}