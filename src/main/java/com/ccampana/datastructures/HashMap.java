package com.ccampana.datastructures;

import java.util.NoSuchElementException;

/**
 * <p>
 * The com.ccampana.datastructures.HashMap is a data structure that allows adding and removing elements from an underlining
 * array list using a translator function called a hash function that provides an easy map between
 * elements and their respective position (buckets).
 * </p>
 * Currently, this implementation resolves conflicts using linear probing;
 * for each position (bucket), there is another array list with elements, and the element
 * conflicted is added to that array list.
 * <p>
 * The hash function is not dealt with here. Instead, it is provided an interface {@link HashCode}
 * containing a single method with the same name. There is a necessity to implement this interface
 * since it is a parameter for the default constructor. This is advantageous since it removes
 * a critical component of the com.ccampana.datastructures.HashMap from its implementation, allowing for greater flexibility
 * to rewrite many hash functions and not have an ineffective default implementation.
 * <p>
 * The maximum load within the hash map is 0.75 unless defined differently through the constructor.
 * <p>
 * There are two constructors. The default constructor initializes the variables, whereas the other
 * allows for a custom maximum load factor.
 * <p>
 * Each pair of keys and values is held by an inner class responsible for managing the value's
 * retrieval and update.
 * <p>
 * <ul>
 * Supported operations:
 * <li>{@link HashMap#put(K key, V value)} - adds a key-value pair to the hash map, using linear
 * chaining for conflict resolution. </li>
 * <li>{@link HashMap#remove(K key)} - removes a key-value pair from the hash map.</li>
 * <li>{@link HashMap#keys()} - returns a list of all keys within the hash map.</li>
 * <li>{@link HashMap#values()} - returns a list of all values within the hash map.</li>
 * <li>{@link HashMap#get(K key)} - using a key as a parameter,
 * returns its corresponding value.</li>
 * <li>{@link HashMap#size()} - Returns the size of the hash map.</li>
 * <li>{@link HashMap#updateLoadFactor()} - it updates the load factor based on the size and the
 * length of the buckets.</li>
 * <li>{@link HashMap#getIndex(K key)} - gets the index in the underlining array
 * list based on the key.</li>
 * <li>{@link HashMap#checkGrowth()} - Checks if it is necessary to increase the size of the hash
 * map if the load factor is greater than the maximum load.</li>
 * <li>{@link HashMap#grow()} - increases the size of the hash map. It is only called by the
 * checkGrowth method.
 * .</li>
 * </ul>
 * <p>
 * This project is licensed under Creative Commons Attribution 4.0 International License.
 * </p>
 * <p>
 * Created by <b>Caike Salles Campana - csallesc@ucsd.edu</b>
 * </p>
 * @see ArrayList
 * @see HashCode
 * @param <K> The key type.
 * @param <V> The value type.
 * @author Caike Salles Campana
 * @version 0.1
 */
public class HashMap<K, V> {
    /**
     * The default maximum load of 0.75.
     */
    final double DEFAULT_MAX_LOAD = 0.75;
    /**
     * Underlining array list to handle the hash map.
     */
    ArrayList<ArrayList<Entry>> buckets;
    /**
     * The hash function interface. Must be implemented before utilizing any operation, it is
     * required by the default constructor.
     */
    HashCode<K> hashFunction;

    /**
     * Private field that defines the maximum load of the hash map.
     */
    private double maximumLoad;
    /**
     * The current load factor of the hashmap.
     */
    private double loadFactor;
    /**
     * The size of the hashmap.
     */
    private int size;

    /**
     * This constructor initializes the hashCode and all the variables.
     * @param hashCode a class that implements the interface HashCode.
     */
    public HashMap(HashCode<K> hashCode) {
        buckets = new ArrayList<>();
        hashFunction = hashCode;
        loadFactor = 0;
        maximumLoad = DEFAULT_MAX_LOAD;
        size = 0;

    }

    /**
     * This constructor allows for a custom maximum load.
     * @param hashCode a class that implements the interface HashCode.
     * @param maximumLoad a double variable that defines the maximum load.
     */
    public HashMap(HashCode<K> hashCode, double maximumLoad) {
        this(hashCode);
        this.maximumLoad = maximumLoad;
    }

    /**
     * Adds a key-value pair to the com.ccampana.datastructures.HashMap.
     * @param key the key used for indexing.
     * @param value value to be associated with the key.
     */
    public void put(K key, V value) {
        checkGrowth();
        int position = getIndex(key);
        if (buckets.get(position) == null) {
            ArrayList<Entry> entries = new ArrayList<>();
            entries.add(new Entry(key, value));
            buckets.add(position, entries);
            ++size;
            updateLoadFactor();
            return;
        }
        ArrayList<Entry> entries = buckets.get(position);
        int previousPosition = -1;
        for (int i = 0; i < entries.length(); i++) {
            if (entries.get(i) != null && entries.get(i).key.equals(key)) {
                previousPosition = i;
            }
        }
        if (previousPosition != -1) {
            buckets.get(position).get(previousPosition).setValue(value);
        } else {
            buckets.get(position).add(new Entry(key, value));
            ++size;


        }

        updateLoadFactor();

    }

    /**
     * Removes a value based on a given key.
     * @param key of the object to be removed from the hash map.
     */
    public void remove(K key) {
        ArrayList<Entry> entries = buckets.get(getIndex(key));
        if (entries.length() == 1) {
            buckets.remove(buckets.get(getIndex(key)));
            --size;
        } else {
            for (int i = 0; i < entries.length(); i++) {
                if (entries.get(i) != null && entries.get(i).getKey().equals(key)) {
                    entries.remove(entries.get(i));
                    --size;
                }
            }
        }
        updateLoadFactor();
    }

    /**
     * Returns all the hash map keys in a list.
     * @return a list containing all the keys.
     */
    public List<K> keys() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < buckets.length(); i++) {
            for (int j = 0; buckets.get(i) != null && j < buckets.get(i).length(); j++) {
                if (buckets.get(i).get(j) != null) keys.add(buckets.get(i).get(j).getKey());
            }
        }

        return keys;
    }

    /**
     * Returns all the hash map values in a list.
     * @return a list containing all the values.
     */
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < buckets.length(); i++) {
            for (int j = 0; buckets.get(i) != null && j < buckets.get(i).length(); j++) {
                if (buckets.get(i).get(j) != null) values.add(buckets.get(i).get(j).getValue());
            }
        }

        return values;
    }

    /**
     * Using a key value, it returns a value associated with it. In case it does not exist,
     * it throws a NoSuchElement exception.
     * @param key to be searched.
     * @return a value associated with the given key.
     * @see NoSuchElementException
     */
    public V get(K key) {
        ArrayList<Entry> entries = buckets.get(getIndex(key));
        if (entries.length() == 1) return entries.get(0).getValue();
        for (int i = 0; i < entries.length(); i++) {
            if (entries.get(i) != null && entries.get(i).getKey().equals(key)) {
                return entries.get(i).getValue();
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * The size of the hash map.
     * @return the value of the private variable size.
     */
    public int size() {
        return size;
    }

    /**
     * Updates the load factor by dividing the size by the length of each bucket.
     */
    private void updateLoadFactor() {
        loadFactor = size / (double) buckets.length();
    }

    /**
     * Given a key, it returns the index of the element in the array list. It uses the hash
     * function mod bucket length to find the bucket that the key pertains.
     * @param key to be searched.
     * @return a integer with the value.
     */
    private int getIndex(K key) {
        return hashFunction.hashCode(key) % buckets.length();
    }

    /**
     * If the load factor is greater than the maximum load, it calls the
     * method{@link HashMap#grow()}.
     */
    private void checkGrowth() {
        if (loadFactor > maximumLoad) {
            grow();
        }
    }

    /**
     * Grows the hash map, updating all the key positions given the new size of the underlining
     * array list.
     */
    private void grow() {
        ArrayList<ArrayList<Entry>> oldKeys = buckets;
        buckets.grow();
        for (int i = 0; i < oldKeys.length(); i++) {
            if (oldKeys.get(i) != null) {
                ArrayList<Entry> entries = oldKeys.get(i);
                for (int j = 0; j < entries.length(); j++) {
                    put(entries.get(j).getKey(), entries.get(j).getValue());
                }
            }
        }

    }

    /**
     * Inner class used to manage each key-value pair within the hash table.
     * Uses encapsulation to protect the key, while allowing for the value to be updated.
     */
    class Entry {
        K key;
        V value;

        /**
         * The constructor provides an assignment to both the key and value.
         * @param key  used to place the element within the array list.
         * @param value given and attached to the key through this object.
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * A getter for the key.
         * @return the value of type K of the key.
         */
        public K getKey() {
            return key;
        }

        /**
         * A getter for the value.
         * @return the value corresponding to the key.
         */
        public V getValue() {
            return value;
        }

        /**
         * A setter for the value variable.
         * @param value given and attached to the key.
         */
        void setValue(V value) {
            this.value = value;
        }
    }
}
