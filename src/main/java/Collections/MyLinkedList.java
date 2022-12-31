package Collections;

import Collections.Interfaces.CarList;
import Collections.Interfaces.CarQueue;

import java.util.Iterator;

public class MyLinkedList<T> implements CarList<T>, CarQueue<T> {
    private static final int ELEMENT_NOT_FOUND = -1;
    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    @Override
    public T get(int index) {
        return getNoteById(index).value;
    }

    @Override
    public boolean add(T item) {
        if (first == null) {
            Node<T> node = new Node<T>(null, null, item);
            first = node;
            last = node;
        } else {
            Node<T> beforeLast = last;
            last = new Node<T>(beforeLast, null, item);
            beforeLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public T peek() {
        return (size > 0) ? get(0) : null;
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }

        T item = get(0);
        removeAt(0);
        return item;
    }

    @Override
    public boolean add(T item, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            return add(item);
        }

        Node<T> supposedToBeNext = getNoteById(index);
        Node<T> supposedToBePrevious = supposedToBeNext.previous;

        Node<T> node = new Node<T>(supposedToBePrevious, supposedToBeNext, item);

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
    public boolean remove(T item) {
        int index = getElementIndex(item);

        if (index != ELEMENT_NOT_FOUND) {
            return removeAt(index);
        }

        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node<T> node = getNoteById(index);
        Node<T> previous = node.previous;
        Node<T> next = node.next;

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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.value;
                current = current.next;
                return item;
            }
        };
    }

    @Override
    public boolean contains(T item) {
        return getElementIndex(item) != ELEMENT_NOT_FOUND;
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

    private Node<T> getNoteById(int index) {
        checkIfIndexValid(index);

        Node<T> current = first;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private int getElementIndex(T item) {
        Node<T> current = first;

        for (int i = 0; i < size; i++) {
            if (current.value.equals(item)) {
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

    private static class Node<T> {
        private Node<T> previous;
        private Node<T> next;
        private T value;

        public Node(Node<T> previous, Node<T> next, T value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }
}
