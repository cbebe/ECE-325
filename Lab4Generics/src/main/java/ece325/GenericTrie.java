package ece325;

/**
 * Lab 4: Generics <br />
 * Charles Ancheta, 1581672
 * 
 * The {@code GenericTrie} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Trie">
 * https://en.wikipedia.org/wiki/Trie </a>
 */
public class GenericTrie<K extends CharSequence, V> {

    /**
     * Root node of the Prefix Tree
     */
    private TrieNode<V> root = new TrieNode<V>();

    /**
     * Insert a new element to the Prefix Tree
     * 
     * @param word  {@code K} word to be inserted in the Prefix Tree
     * @param value {@code V} value to be inserted with the word
     */
    public void insert(K word, V value) {
        char c = word.charAt(0);

        if (root.child.get(c - 'a') == null)
            root.child.set(c - 'a', new TrieNode<V>());

        TrieNode<V> next = root.child.get(c - 'a');

        for (int i = 1; i < word.length(); ++i) {
            c = word.charAt(i);
            if (next.child.get(c - 'a') == null)
                next.child.set(c - 'a', new TrieNode<V>());

            next = next.child.get(c - 'a');
        }

        if (!next.isEndOfWord) {
            next.value = value;
            next.isEndOfWord = true;
        } else {
            System.out.printf("Error inserting word: %s already exists in the Trie\n", word);
        }

    }

    /**
     * Retrieve the TrieNode from a given word/prefix
     * 
     * @param word {@code K} input word to traverse the tree
     * @return {@code TrieNode<V>} node that the word ends on, returns null if word
     *         is not in tree
     */
    public TrieNode<V> retrieveNode(K word) {
        char c = word.charAt(0);

        if (root.child.get(c - 'a') == null)
            return null;

        TrieNode<V> next = root.child.get(c - 'a');

        for (int i = 1; i < word.length(); ++i) {
            c = word.charAt(i);
            if (next.child.get(c - 'a') == null)
                return null;

            next = next.child.get(c - 'a');
        }

        return next;
    }

    /**
     * Returns the value associated with the word is in the Prefix Tree
     * 
     * @param word {@code K} word to be searched in the prefix tree
     * @return {@code V} the value associated with the word
     */
    public V search(K word) {
        TrieNode<V> wordNode = retrieveNode(word);
        if (wordNode == null || !wordNode.isEndOfWord)
            return null;

        return wordNode.value;
    }

    /**
     * Returns if there is any word in the Prefix Tree that starts with the given
     * prefix.
     * 
     * @param prefix {@code K} the prefix of the word
     * @return {@code boolean} true if found, false if not
     */
    public boolean startWith(K prefix) {
        return retrieveNode(prefix) != null;
    }

    /**
     * Removes an element from the Prefix Tree, returning its associated value
     * 
     * @param word {@code K} word to be removed
     * @return {@code V} value of the removed word
     */
    public V remove(K word) {
        TrieNode<V> node = retrieveNode(word);
        // word is not in trie
        if (node == null)
            return null;

        V value = node.value;

        node.isEndOfWord = false;
        node.value = null;

        return value;
    }

}
