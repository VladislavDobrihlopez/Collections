package Collections;

import java.util.Iterator;

public class MyCircularLinkedList<T> implements Iterable<T> {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public boolean add(T item) {
        Node newNode = new Node(item);

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

    public boolean remove(T item) {
        Node last = head;
        Node secondLast = null;

        do {
            if (last.value.equals(item)) {
                if (tail == head) {
                    tail = head = null;
                } else {
                    if (secondLast != null) {
                        secondLast.next = last.next;
                    }

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

    public boolean contains(T item) {
        Node current = head;

        do {
            if (current.value.equals(item)) {
                return true;
            }
            current = current.next;
        } while (current != head);
        return false;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
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
            public T next() {
                node = node.next;
                return node.value;
            }
        };
    }

    private class Node {
        private final T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }
    }
}
