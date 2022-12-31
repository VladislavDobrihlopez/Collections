package Collections;

import Collections.Entities.Car;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarCircularLinkedListTest {

    private static final int INITIAL_CAPACITY = 100;
    private CarCircularLinkedList<Car> circularLinkedList;

    @Before
    public void setUp() throws Exception {
        circularLinkedList = new CarCircularLinkedList<>();

        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            circularLinkedList.add(new Car("nissan", i));
        }
    }

    @Test
    public void whenClearListThemSizeMustBe0() {
        circularLinkedList.clear();
        assertEquals(0, circularLinkedList.size());
    }

    @Test
    public void whenNewElementsAddedThenSizeMustBeSummedWithInitialCapacity() {
        final int ELEMENTS_TO_BE_ADDED = 100;
        for (int i = 0; i < ELEMENTS_TO_BE_ADDED; i++) {
            circularLinkedList.add(new Car("some awesome brand", i + 50));
        }
        assertEquals(INITIAL_CAPACITY + ELEMENTS_TO_BE_ADDED, circularLinkedList.size());
    }

    @Test
    public void whenElementRemovedByInstanceThenSizeMustBeDecreased() {
        Car car = new Car("nissan", 0);
        assertTrue(circularLinkedList.remove(car));
        assertEquals(INITIAL_CAPACITY - 1, circularLinkedList.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse() {
        assertFalse(circularLinkedList.remove(new Car("someBrand", 7)));
        assertEquals(INITIAL_CAPACITY, circularLinkedList.size());
    }

    @Test
    public void testContains() {
        boolean exists = circularLinkedList.contains(new Car("nissan", 7));
        boolean exists2 = circularLinkedList.contains(new Car("not nissan", 7));
        assertTrue(exists);
        assertFalse(exists2);
    }
}