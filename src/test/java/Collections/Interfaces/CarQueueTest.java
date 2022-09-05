package Collections.Interfaces;

import Collections.CarLinkedList;
import Collections.Entities.Car;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CarQueueTest {
    private static final int INITIAL_NUMBER_OF_ITEMS = 100;
    private CarQueue queue;

    @Before
    public void setUp() {
        queue = new CarLinkedList();
        for (int i = 0; i < INITIAL_NUMBER_OF_ITEMS; i++) {
            queue.add(new Car("Brand" + i, i));
        }
    }

    @Test
    public void whenElementsAddedThenSizeMustBeIncreased() {
        assertEquals(INITIAL_NUMBER_OF_ITEMS, queue.size());
    }

    @Test
    public void whenCallPickThenSizeMustStaySameAndMustReturnVeryFirstElement() {
        Car car = new Car("Brand0", 0);
        assertEquals(car, queue.peek());
        assertEquals(car, queue.peek());
        assertEquals(INITIAL_NUMBER_OF_ITEMS, queue.size());
    }

    @Test
    public void whenCallPollThenSizeMustBeDecreasedAndMustReturnVeryFirstElement() {
        Car car = new Car("Brand0", 0);
        assertEquals(car, queue.poll());
        assertEquals(INITIAL_NUMBER_OF_ITEMS - 1, queue.size());
    }

    @Test
    public void testPollAndPeekWorkCorrectlyWhenQueueIsEmpty() {
        queue.clear();
        assertNull(queue.peek());
        assertNull(queue.poll());
    }
}