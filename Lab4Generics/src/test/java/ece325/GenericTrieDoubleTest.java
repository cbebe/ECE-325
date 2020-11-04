package ece325;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for GenericTrie
 */
public class GenericTrieDoubleTest {

    static String[] words = { "ece", "lab", "java", "jar", "car", "cat", "care", "laboratory", "ebook" };
    static Double[] values = { 10.5, 2.0, 3.33, 0.35, -4.0, 42.001, 6.0, -1.02, 45.9 };

    static GenericTrie<String, Double> trie = new GenericTrie<String, Double>();

    @BeforeClass
    public static void initTrie() {
        for (int i = 0; i < words.length; i++)
            trie.insert(words[i], values[i]);
    }

    @Test
    public void searchLab() {
        Double res = trie.search("lab");
        assertNotNull("'lab' should be in trie", res);
        assertEquals(2.0, res, 0);
    }

    @Test
    public void searchJava() {
        Double res = trie.search("java");
        assertNotNull("'java' should be in trie", res);
        assertEquals(3.33, res, 0);
    }

    @Test
    public void startsWithFalse() {
        assertFalse(trie.startWith("eced"));
    }

    @Test
    public void startsWithTrue() {
        assertTrue(trie.startWith("ca"));
    }

    @Test
    public void searchBook() {
        Double res = trie.search("book");
        assertNull("'book' is no in trie", res);
    }

    @Test
    public void removeInTrie() {
        Double res = trie.remove("jar");
        assertNull("'jar' should not be in trie", trie.search("jar"));
        assertEquals(0.35, res, 0);
    }

    @Test
    public void removeNotInTrie() {
        Double res = trie.remove("capitan");
        assertNull("'capitan' is not in trie", res);
    }

}
