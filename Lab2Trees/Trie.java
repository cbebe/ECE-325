import java.util.regex.Pattern;

/**
 * Lab 2: Debugging with an IDE and Prefix Tree)
 * 
 * @author Charles Ancheta, 1581672
 * 
 */

class Trie {

	static final int ALPHABET_SIZE = 26;

	/**
	 * Node subclass for trie
	 */
	static class TrieNode {

		TrieNode[] child = new TrieNode[ALPHABET_SIZE];
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				child[i] = null;
		}

	}

	/**
	 * Root node of the Prefix Tree
	 */
	static TrieNode root;

	/**
	 * Check if the word can be in the tree
	 * 
	 * @param word {@code String} word to be checked
	 * @return {@code boolean} true if word is valid, false if not
	 */
	public static boolean checkString(String word) {
		return Pattern.compile("[a-z]+").matcher(word).matches();

	}

	/**
	 * Insert word into the Trie
	 * 
	 * @param word {@code String} word to be inserted
	 */
	public static void insert(String word) {
		// this prefix tree is case insensitive
		String wordLower = word.toLowerCase();
		if (!checkString(wordLower)) {
			System.out.printf("\"%s\" is not a valid Trie key\n", word);
			return;
		}

		char c = wordLower.charAt(0);

		if (root.child[c - 'a'] == null)
			root.child[c - 'a'] = new TrieNode();


		TrieNode next = root.child[c - 'a'];

		for (int i = 1; i < wordLower.length(); ++i) {
			c = wordLower.charAt(i);
			if (next.child[c - 'a'] == null)
				next.child[c - 'a'] = new TrieNode();

			next = next.child[c - 'a'];
		}

		if (!next.isEndOfWord) {
			next.isEndOfWord = true;
		} else {
			System.out.printf("Error inserting word: %s already exists in the Trie\n", word);
		}
	}

	/**
	 * Retrieve the TrieNode from a given word/prefix
	 * 
	 * @param word {@code String} input word to traverse the tree
	 * @return {@code TrieNode} node that the word ends on, returns null if word is not in tree
	 */
	public static TrieNode retrieveNode(String word) {
		// this prefix tree is case insensitive
		String wordLower = word.toLowerCase();
		char c = wordLower.charAt(0);

		if (!checkString(wordLower) || root.child[c - 'a'] == null)
			return null;

		TrieNode next = root.child[c - 'a'];

		for (int i = 1; i < wordLower.length(); ++i) {
			c = wordLower.charAt(i);
			if (next.child[c - 'a'] == null)
				return null;

			next = next.child[c - 'a'];
		}

		return next;
	}

	/**
	 * Search for a word in the tree
	 * 
	 * @param word {@code String} word to search for
	 * @return {@code boolean} true if found, false if not
	 */
	public static boolean search(String word) {
		TrieNode wordNode = retrieveNode(word);
		if (wordNode == null)
			return false;

		return wordNode.isEndOfWord;
	}

	/**
	 * Check if the prefix exists in the tree
	 * 
	 * @param prefix {@code String} prefix to search for
	 * @return {@code boolean} true if prefix is found in tree, false if not
	 */
	public static boolean startWith(String prefix) {
		return retrieveNode(prefix) != null;
	}

	public static void main(String args[]) {

		String[] words = {"ece", "lab", "jaVA", "jar", "car", "cat", "care", "laboratory", "ebook"};

		root = new TrieNode();

		// Construct trie
		for (String w : words)
			insert(w);

		insert("ece"); // ece already inserted

		String testFmt = "%s --- is %sin the prefix tree\n";
		System.out.println("\n---------TEST search METHOD---------\n");
		// Search for different keys
		String[] searches = {"lab", "java", "book", "labor", "la"};
		for (String s : searches)
			System.out.printf(testFmt, s, search(s) ? "" : "NOT ");

		System.out.println("\n---------TEST startWith METHOD---------\n");
		// Search for different prefixes
		String[] startWiths = {"eced", "CA", "ja", "laB"};
		for (String s : startWiths)
			System.out.printf(testFmt, s, startWith(s) ? "" : "NOT ");
	}
}


