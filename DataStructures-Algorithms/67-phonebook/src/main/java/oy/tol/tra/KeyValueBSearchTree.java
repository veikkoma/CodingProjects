package oy.tol.tra;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyValueBSearchTree<K extends Comparable<K>,V> implements Dictionary<K, V> {

    private TreeNode<K, V> root;
    private int count;
    private int depth;
    private int maxCollisionLen;


    public KeyValueBSearchTree() {
        root = null;
        count = 0;
        depth = 0;
        maxCollisionLen = 0;
    }

    @Override
    public Type getType() {
       return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your hash function
     * is good or bad (too much collisions against data size).
     */
    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(
            "Count treenodes: " + count +
            "\nTree has max depth of " + depth + "." +
            "\nLongest collision chain in tree node is " + maxCollisionLen);
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        boolean cr = false;
        if (key == null || value == null) throw new IllegalArgumentException("Key and value must not be null");
            try {
                if (root == null) {
                    root = new TreeNode<K, V>(key, value);
                    depth = 1;
                    maxCollisionLen = 0;
                    count++;
                    cr = true;
                } else {
                    TreeNode.depthAdd = 1;
                    maxCollisionLen = 0;
                    int added = root.insert(key, value, key.hashCode());
                    if (added > 0) {
                        depth = Math.max(TreeNode.depthAdd, depth);
                        count++;
                        cr = true;
                    }
                maxCollisionLen = Math.max(maxCollisionLen, TreeNode.collisionChainCounter);
                }
            } catch (Exception e) {
                throw new OutOfMemoryError();
            }
            return cr;
        }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key must not be null");
        if (root == null) {
            return null;
        }
        return root.find(key, key.hashCode());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<K,V> [] toSortedArray() {
        if (root == null) {
            return null;
        }
        Pair<K,V>[] toArray = (Pair<K,V>[])new Pair[count];
        AtomicInteger add = new AtomicInteger(0);
        root.toSortedArray(toArray, add);
        Algorithms.fastSort(toArray);
        return toArray;
    }

    // These not needed !!
    @Override
    public void compress() throws OutOfMemoryError {
        // This is not needed!!
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // This is not needed!!
    }

}
