package Collections;

public class CarLinkedList implements CarList {
    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNoteById(index).value;
    }

    @Override
    public void add(Car car) {
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
    }

    @Override
    public void add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(car);
            return;
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
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;

        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return removeAt(i);
            }

            node = node.next;
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
