package oy.tol.tra;

public class LinkedListImplementation<E> {

    private class Node<T> {

        Node(T element) {
            this.element = element;
            next = null;
        }

        T element;
        Node<T> next;

    @Override
    public String toString() {
        return element.toString();
        }
    }

    private Node<E> head = null;
    private int size = 0;

    public void add (E element) throws NullPointerException, LinkedListAllocationException {
        if (element.equals(null)) throw new IllegalArgumentException("Cant add list");
        if (null == head) {
            head = new Node<E>(element);
            size++;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            try {
                current.next = new Node<E>(element);
            } catch (Exception e) {
                throw new LinkedListAllocationException("Failed allocated new list elements.");
            }
            size++;
        }
    }

    public void add(int index, E element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
        if (element.equals(null)) throw new IllegalArgumentException("Can't add null");
        if (index > size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        if (index == 0) {
            Node<E> newNode;
            try {
                newNode = new Node<E>(element);
                newNode.next = head;
                head = newNode;
                size++;
            } catch (Exception e) {
                throw new LinkedListAllocationException("Failed to allocated");
            }
        } else {
            int counting = 0;
            Node<E> current = head;
            Node<E> previous = null;
            while (counting < index) {
                previous = current;
                current = current.next;
                counting++;
            }
            Node<E> newNode;
            try {
                newNode = new Node<>(element);
                previous.next = newNode;
                newNode.next = current;
                size++;
            } catch (Exception e) {
                throw new LinkedListAllocationException("Failed to allocated");
            }
        }
    }

    public boolean remove(E element) throws NullPointerException{

        if (element.equals(null)) new IllegalArgumentException("Won't remove elememt that equal");
        if (size == 0) {
            return false;
        }
        Node<E> current = head;
        Node<E> previous = null;
        int counting = 0;
        while (counting < size) {
            if (current.element.equals(element)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
            counting++;
        }
        return false;
    }

    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index is out of bounds");

        Node<E> removed = null;
        if (index == 0) {
            removed = head;
            head = head.next;
            size--;
            return removed.element;
        }

        int counting = 1;
        Node<E> current = head.next;
        Node<E> previous = head;
        while (current != null) {
            if (counting == index) {
                removed = current;
                previous.next = current.next;
                size--;
                break;
            }
            counting++;
            previous = current;
            current = current.next;
        }
        return removed.element;
    }

    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size || size == 0) throw new IndexOutOfBoundsException("Index out of bounds");
        int counting = 0;
        Node<E> anotherNode = head;
        while (counting < index) {
            anotherNode = anotherNode.next;
            counting++;
        }
        return anotherNode.element;
    }

    public int indexOf(E element) throws NullPointerException {
        if (element.equals(null)) throw new IllegalArgumentException("Can't search for null");
        if (size == 0) {
            return -1;
        }
        Node<E> current = head;
        int counting = 0;

        while (counting < size) {
            if (current.element.equals(element)) {
                return counting;
            }
            current = current.next;
            counting++;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void reverse() {
        if (size == 0 || size == 1) {
            return;
        }
        if (size == 2) {
            Node<E> current = head;
            current = head.next;
            current.next = head;
            head = current;
            return;
        }
        Node<E> current = head;
        Node<E> next = null;
        Node<E> previous = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (head != null) {
            Node<E> addNode = head;
            int counting = 0;
            while ( counting < size) {
                builder.append(addNode.element);
                if (counting < size - 1) {
                    builder.append(", ");
                }
                addNode = addNode.next;
                counting++;
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
