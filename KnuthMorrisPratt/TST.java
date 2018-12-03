
/**
 * @author Conor Evans
 * @version 1.0
 * 
 *       This class represents an symbol table of key-value
 *  	 pairs, with string keys and generic values.
 * 
 */

import java.util.LinkedList;

public class TST<Value> {

	/**
	 * A Node in the trie containing a Value val and a pointer to its children
	 */
	private static class TrieNode<Value> {
		private char c; // character
		private TrieNode<Value> left, mid, right; // left, middle, and right
													// subtries
		private Value val; // value associated with string
	}

	/** a pointer to the start (root) of the trie */
	private TrieNode<Value> root = new TrieNode<Value>();
	/** size of TST */
	private int size;

	/**
	 * @return int : the number of words in the trie
	 */
	public int size() {
		return size;
	}

	/**
	 * @return boolean : true if the word is in the trie, false otherwise
	 */
	public boolean contains(String key) {
		if (get(key) != null)
			return true;
		else
			return false;
	}

	/**
	 * @return Value : the value stored in a node with a given key, returns null
	 *         if word is not in trie
	 */
	public Value get(String key) {
		if (key == null || key.length() == 0)
			return null;
		TrieNode<Value> node = get(root, key, 0);
		if (node == null)
			return null;
		else
			return (Value) node.val;
	}

	/**
	 * @return TrieNode<Value> : The trie Node with the relevant key at the
	 *         right index
	 */
	private TrieNode<Value> get(TrieNode<Value> node, String key, int i) {
		if (node == null)
			return null;
		char c = key.charAt(i);
		if (c < node.c)
			return get(node.left, key, i);
		else if (c > node.c)
			return get(node.right, key, i);
		else if (i < key.length() - 1)
			return get(node.mid, key, i + 1);
		else
			return node;
	}

	/**
	 * stores the Value val in the node with the given key
	 */
	public void put(String key, Value val) {
		if (key == null || key.isEmpty()) {
			System.out.println("invalid key");
			return;
		}
		if (!contains(key))
			size++;
		root = put(root, key, val, 0);
	}

	/**
	 * @return TrieNode<Value> : stores the Value val in the node with the given
	 *         key at the given index
	 */
	private TrieNode<Value> put(TrieNode<Value> node, String key, Value val, int i) {
		char c = key.charAt(i);
		if (node == null) {
			node = new TrieNode<Value>();
			node.c = c;
		}
		if (c < node.c)
			// if c is less than current nodes char, put to left
			node.left = put(node.left, key, val, i);
		else if (c > node.c)
			// else if c is greater than current nodes char, put to right
			node.right = put(node.right, key, val, i);
		else if (i < key.length() - 1)
			// else put mid
			node.mid = put(node.mid, key, val, i + 1);
		else
			node.val = val;
		return node;
	}

	/**
	 * return LinkedList<String> : the linked list containing all the keys
	 * present in the trie that start with the prefix passes as a parameter,
	 * sorted in alphabetical order
	 */
	public LinkedList<String> keysWithPrefix(String prefix) {
		LinkedList<String> list = new LinkedList<String>();
		if (prefix == null || prefix.isEmpty()) {
			return list;
		}
		TrieNode<Value> node = get(root, prefix, 0);
		if (node == null)
			return list;
		if (node.val != null)
			list.addLast(prefix);
		collect(node.mid, prefix, list);
		return list;
	}

	/** all keys in subtrie rooted at x with given prefix */

	private void collect(TrieNode<Value> x, String prefix, LinkedList<String> list) {
		if (x == null)
			return;

		collect(x.left, prefix, list);
		if (x.val != null)
			list.addLast(prefix.toString() + x.c);
		collect(x.mid, prefix + x.c, list);
		collect(x.right, prefix, list);
	}
}