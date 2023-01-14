package oy.tol.tra;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private Pair<K, V>[] array = null;
    private int capacity;
    private int count;
    private int collisionCounter;
    private int maxProbingCount;
    private int reallocateCount;

    private static int DEFAULT_CAPACITY = 20;
    private static double LOAD_FACTOR = 0.65;


    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        maxProbingCount = 0;
        collisionCounter = 0;
        count = 0;
        this.capacity = capacity;
        reallocateCount = 0;
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        count = 0;
        capacity = DEFAULT_CAPACITY;
        maxProbingCount = 0;
        collisionCounter = 0;
        reallocateCount = 0;
        try {
            ensureCapacity(DEFAULT_CAPACITY);
        } catch (Exception e) {
            throw new OutOfMemoryError("Ran out of memory when allocating enough memory for hashtable");
        }
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity > 0) {
            array = (Pair<K, V>[]) new Pair[capacity];
            this.capacity = capacity;
            try {
                array = (Pair<K, V>[]) new Pair[capacity];
            } catch (Exception e) {
                throw new OutOfMemoryError("Ran out of memory when allocating memory for hashtable");
            }
        } else {
            try {
                reallocate(capacity);
            } catch (Exception e) {
                throw new OutOfMemoryError("Ran out of the memory");
            }
        }
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */

    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(
            "Hash table fill factor is " + LOAD_FACTOR +
            "\nCapacity: " + capacity +
            "\nCount: " + count +
            "\nReallocated count: " + reallocateCount +
            "\nHash table had " + this.collisionCounter + " collision when filling the hash table." +
            "\nHash table had to probe " + this.maxProbingCount + " times." +
            "\nCurrent fill rate is: " + ((double) count / (double) capacity) * 100.0);
        return builder.toString();
   }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) throw new IllegalArgumentException("One of the parameters was null");
        int index = 0;
        int hashModifier = 0;
        int currentProbingCount = 0;
        boolean added = false;

        if ((double) this.count / (double) capacity >= LOAD_FACTOR) {
            reallocate((int)(capacity *(1.0/LOAD_FACTOR)));
        }
        do {
            index = indexFor (key, hashModifier);
            if (array[index] == null) {
                array[index] = new Pair<>(key, value);
                this.count++;
                added = true;
            }
            else if (!array[index].getKey().equals(key)) {
                hashModifier++;
                collisionCounter++;
                currentProbingCount++;
            }
        } while (!added);
        maxProbingCount = Math.max(maxProbingCount, currentProbingCount);
        return added;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        int hashModifier = 0;

        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int index = indexFor (key, hashModifier);
        if (this.array[index] != null && this.array[index].getKey().equals(key)) {
            return this.array[index].getValue();
        } else {
            int i = (index + 1) % capacity;
            while (this.array[i] != null && !this.array[i].getKey().equals(key)) {
                i = (i + 1) % this.capacity;
            }
            if (array[i] != null) {
                return array[i].getValue();
            } else {
                return null;
            }
        }
    }

    @Override
    @java.lang.SuppressWarnings({"unchecked"})
    public Pair<K,V> [] toSortedArray() {
        Pair<K, V>[] toReturn = (Pair<K, V>[]) new Pair[count];
        int aindex = 0;
        for (int index = 0; index < capacity; index++) {
            if (array[index] != null) {
                toReturn[aindex++] = new Pair<K,V>(array[index].getKey(), array[index].getValue());
            }
        }
        Algorithms.fastSort(toReturn);
        return toReturn;
}

    @Override
    public void compress() throws OutOfMemoryError {
        KeyValueHashTable<K, V> newTable = new KeyValueHashTable<>(capacity);
        for (Pair<K, V> pair : array) {
            if (pair != null) {
                newTable.add(pair.getKey(), pair.getValue());
            }
        }
        array = newTable.array;
        capacity = newTable.capacity;
        count = newTable.count;
        collisionCounter = newTable.collisionCounter;
    }

    @SuppressWarnings("unchecked")
    public void reallocate (int newCapacity) {
        reallocateCount++;

        Pair<K, V>[] originalArray = (Pair<K, V>[]) new Pair[this.capacity];

        originalArray = this.array;
        int originalCapasity = capacity;

        this.capacity = newCapacity;
        this.array = (Pair<K, V>[]) new Pair[this.capacity];
        this.count = 0;

        for (int index = 0; index < originalCapasity; index++) {
            if (originalArray[index] != null) {
                add(originalArray[index].getKey(), originalArray[index].getValue());
            }
        }
    }

    public int indexFor (K key, int hashModifier) {
        return ((key.hashCode() + hashModifier) & 0x7fffffff) % capacity;
    }

}
