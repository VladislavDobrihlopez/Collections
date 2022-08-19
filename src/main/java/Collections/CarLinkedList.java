package Collections;

public class CarLinkedList implements CarList {
    private static final int ELEMENT_NOT_FOUND = -1;
    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNoteById(index).value;
    }

    @Override
    public boolean add(Car car) {
        if (first == null) {
            Node node = new Node(null, null, car);
            first = node;
            last = node;
        } else {
            Node beforeLast = last;
            last = new Node(beforeLast, null, car);
            beforeLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            return add(car);
        }

        Node supposedToBeNext = getNoteById(index);
        Node supposedToBePrevious = supposedToBeNext.previous;

        Node node = new Node(supposedToBePrevious, supposedToBeNext, car);

        if (supposedToBePrevious != null) {
            supposedToBePrevious.next = node;
        } else {
            first = node;
        }

        supposedToBeNext.previous = node;
        size++;
        return true;
    }

    @Override
    public boolean remove(Car car) {
        int index = getElementIndex(car);

        if (index != ELEMENT_NOT_FOUND) {
            return removeAt(index);
        }

        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node node = getNoteById(index);
        Node previous = node.previous;
        Node next = node.next;

        if (previous != null) {
            previous.next = next;
        } else {
            first = next;
        }

        if (next != null) {
            next.previous = previous;
        } else {
            last = previous;
        }

        size--;

        return true;
    }

    @Override
    public boolean contains(Car car) {
        return getElementIndex(car) != ELEMENT_NOT_FOUND;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node getNoteById(int index) {
        checkIfIndexValid(index);

        Node current = first;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private int getElementIndex(Car car) {
        Node current = first;

        for (int i = 0; i < size; i++) {
            if (current.value.equals(car)) {
                return i;
            }
            current = current.next;
        }
        return ELEMENT_NOT_FOUND;
    }

    private void checkIfIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Node {
        private Node previous;
        private Node next;
        private Car value;

        public Node(Node previous, Node next, Car value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }
}
