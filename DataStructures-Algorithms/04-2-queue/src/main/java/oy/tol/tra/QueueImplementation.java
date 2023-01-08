package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

    //member variable
    private Object[] itemArray;
    private int head;
    private int tail;
    private int count;
    private int capacity;

    private static final int DEFAULT_CAPACITY = 10;

    public QueueImplementation() throws QueueAllocationException{
        this(DEFAULT_CAPACITY);
    }

    public QueueImplementation(int capacity) throws QueueAllocationException {
        this.capacity = capacity;
        itemArray = new Object[capacity];
        this.count = 0;
        this.tail = 0;
        this.head = 0;
    }


    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        capacity = DEFAULT_CAPACITY;
        head = 0;
        tail = 0;
        count = 0;
        itemArray = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (count == 0) {
            throw new QueueIsEmptyException("Cannot dequeue from empty queue");
        }

        E element = (E) itemArray[head];
        itemArray[head] = null;
        count--;
        head++;

        if (head >= capacity()) {
            head = 0;
        }
    return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Cannot element from empty queue");
        }
        E element = (E) itemArray[head];
        return element;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if(element == null){
            throw new NullPointerException("Cannot enqueue null");
            }
        if (count >= capacity){
            reallocateArray();
        }
        if (tail >= capacity){
            tail = 0;
        }
    itemArray[tail++] = element;
    count++;
}


    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        if (count > 0) {
            int index = head;
            int counter = count;

            while (counter > 0){
                builder.append(itemArray[index++]);
                counter--;

                if (counter > 0){
                builder.append(", ");
            }
                if (index >= capacity){
                index = 0;
            }
        }
    }
        builder.append("]");
        return builder.toString();
    }

    private void reallocateArray(){

    int newCapacity = capacity * 2;
    Object [] newArray = new Object [newCapacity];

    try{

        newArray = new Object [newCapacity];

    }catch (Exception e) {
        throw new QueueAllocationException("Reallocation for queue array failed.");
    }

    int index = head;
    int counter = count;
    int newItemArrayIndex = 0;

    while (counter > 0){
        newArray[newItemArrayIndex++] = itemArray[index++];
        counter--;
        if (index >= capacity){
        index = 0;
        }
    }

    head = 0;
    tail = count;
    capacity = newCapacity;
    itemArray = newArray;
    }
}

