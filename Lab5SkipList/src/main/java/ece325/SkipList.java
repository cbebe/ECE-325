package ece325;

import java.util.ArrayList;
import java.util.Random;

/**
 * Lab 5: Java Collection Framework and Skip List <br />
 * The {@code SkipList} class
 * 
 * @param <K> {@code K} key of each skip list node
 * @param <V> {@code V} value of each skip list node
 */
public class SkipList<K extends Comparable<K>, V> {
    static Random rand = new Random();

    /**
     * The {@code Node} class for {@code SkipList}
     */
    private class Node {
        public K key;
        public V value;
        public ArrayList<Node> forwards = new ArrayList<Node>();

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            for (int i = 0; i < level; i++)
                forwards.add(null);
        }

        public String toString() {
            return String.format("%s(%s,%d)", value, key, forwards.size());
        }
    }

    /**
     * Level of the skip list. An empty skip list has a level of 0
     */
    private int level = 1;

    /**
     * Size of the skip list
     */
    private int size = 0;

    /**
     * Head of the node pointing to null with a level 0
     */
    private Node head = new Node(null, null, level);

    /**
     * List with all turning nodes
     */
    private ArrayList<Node> updates;

    private int coinFlip() {
        int level = 1;
        while (rand.nextDouble() < 0.5 && level <= level())
            ++level;

        return level;
    }

    /**
     * Insert an new element into the skip list
     * 
     * @param key   {@code K} key of the new element
     * @param value {@code V} value of the new element
     */
    public void insert(K key, V value) {
        Node node = findNode(key);
        // node already exists, update node value
        if (node != null) {
            node.value = value;
            return;
        }

        // create new node with random level
        int newLevel = coinFlip();
        node = new Node(key, value, newLevel);

        // link node to the list
        for (int i = 0; i < Math.min(level, newLevel); ++i) {
            Node update = updates.get(i);
            node.forwards.set(i, update.forwards.get(i));
            update.forwards.set(i, node);
        }

        // raise head's level, and point head's new forwards the new node
        if (newLevel > level) {
            Node oldHead = head;
            head = new Node(null, null, newLevel);
            for (int i = 0; i < level; ++i)
                head.forwards.set(i, oldHead.forwards.get(i));

            level = newLevel;
        }

        ++size;
    }

    /**
     * Remove an element by the key
     * 
     * @param key {@code K} key of the element
     * @return {@code V} value of the removed element
     */
    public V remove(K key) {
        Node node = findNode(key);

        if (node == null)
            return null;

        // unlink found Node
        for (int i = 0; i < level; ++i) {
            Node update = updates.get(i);

            if (update.forwards.get(i) != node)
                break;

            update.forwards.set(i, node.forwards.get(i));
        }

        // remove levels where forward of head is null
        while (level > 1 && head.forwards.get(level - 1) == null)
            --level;

        --size;
        return node.value;
    }

    /**
     * Find {@code Node} from {@code SkipList} and updates array of Nodes
     * 
     * @param key {@code K} key to retrieve from list
     * @return {@code Node} node with given key
     */
    private Node findNode(K key) {
        updates = new ArrayList<>();
        for (int i = 0; i < level; ++i)
            updates.add(null);

        Node current = head;

        // get update links
        for (int i = level() - 1; i >= 0; --i) {
            Node next = current.forwards.get(i);

            while (next != null && key.compareTo(next.key) > 0) {
                current = next;
                next = current.forwards.get(i);
            }

            updates.set(i, current);
        }

        current = current.forwards.get(0);

        return (current != null && current.key.equals(key)) ? current : null;
    }

    /**
     * Search for an element by the key
     * 
     * @param key {@code K} key of the element
     * @return {@code V} value of the target element
     */
    public V search(K key) {
        try {
            return findNode(key).value;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Get the level of the skip list
     * 
     * @return {@code int} level of the skip list
     */
    public int level() {
        return level;
    }

    /**
     * Get the size of the skip list
     * 
     * @return {@code int} size of the skip list
     */
    public int size() {
        return size;
    }

    /**
     * Print the skip list
     * 
     * @return {@code String} the string format of the skip list
     */
    public String toString() {
        String output = "";
        for (int i = level - 1; i >= 0; --i) {
            output += i + ": ";
            Node node = head.forwards.get(i);
            while (node != null && node.value != null) {
                output += node.value.toString() + " -> ";
                node = node.forwards.get(i);
            }
            output += "null\n";
        }

        return output;
    }

    /**
     * Main entry
     * 
     * @param args {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        SkipList<Integer, String> list = new SkipList<Integer, String>();
        int[] keys = new int[10];
        for (int i = 0; i < 10; i++) { // Insert elements
            keys[i] = (int) (Math.random() * 200);
            list.insert(keys[i], "\"" + keys[i] + "\"");
        }

        System.out.println(list);

        for (int i = 0; i < 10; i += 3) {
            int key = keys[i];
            // Search elements
            System.out.println(String.format("Find element             %3d: value=%s", key, list.search(key)));
            // Remove some elements
            System.out.println(String.format("Remove element           %3d: value=%s", key, list.remove(key)));
            // Search the removed elements
            System.out.println(String.format("Find the removed element %3d: value=%s", key, list.search(key)));
        }

        System.out.println(list);
    }
}
