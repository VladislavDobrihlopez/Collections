package Collections;

import Collections.Entities.Car;
import Collections.Interfaces.CarList;

import java.util.Iterator;

public class CarCircularLinkedList implements CarList {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public Car get(int index) {
        return null;
    }

    @Override
    public boolean add(Car car) {
        Node newNode = new Node(car);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        return false;
    }

    @Override
    public boolean remove(Car car) {
        Node last = head;
        Node secondLast = null;

        do {
            if (last.value.equals(car)) {
                if (tail == head) {
                    tail = head = null;
                } else {
                    secondLast.next = last.next;

                    if (head == last) {
                        head = head.next;
                    }

                    if (tail == last) {
                        tail = secondLast;
                    }
                }
                size--;
                return true;
            }
            secondLast = last;
            last = last.next;
        } while (last != head);
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        return false;
    }

    @Override
    public boolean contains(Car car) {
        Node current = head;

        do {
            if (current.value.equals(car)) {
                return true;
            }
            current = current.next;
        } while (current != head);
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            private Node node = head;
            private boolean isHeadVisited = false;
            @Override
            public boolean hasNext() {
                if (node == head) {
                    if (isHeadVisited)
                    {
                        return false;
                    }
                    isHeadVisited = true;
                }
                return true;
            }

            @Override
            public Car next() {
                node = node.next;
                return node.value;
            }
        };
    }

    private static class Node {
        private final Car value;
        private Node next;

        public Node(Car value) {
            this.value = value;
        }
    }
}
