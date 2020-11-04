package ece325;

import java.util.ArrayList;

/**
 * Lab 4: Generics <br />
 * Charles Ancheta, 1581672 <br />
 * The {@code TrieNode} class
 */
public class TrieNode<V> {
	static final int ALPHABET_SIZE = 26;
	ArrayList<TrieNode<V>> child = new ArrayList<TrieNode<V>>(0);
	boolean isEndOfWord;
	V value;

	TrieNode() {
		isEndOfWord = false;
		value = null;
		for (int i = 0; i < ALPHABET_SIZE; i++)
			child.add(i, null);
	}

	TrieNode(V val) {
		isEndOfWord = true;
		value = val;
		for (int i = 0; i < ALPHABET_SIZE; i++)
			child.add(i, null);
	}
}
