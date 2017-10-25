package spellchecker;

public class BinaryTree {
	BinaryTreeNode root;
	BinaryTreeNode current;
	BinaryTreeNode pre;
	BinaryTreeNode post;
	int countss = 0;
	String[] message = { "", "" };

	public BinaryTree(String word) {
		root = new BinaryTreeNode(word);
	}

	public BinaryTree() {

	}

	public void insert(String word) {

		root = insertRecursive(root, word.trim());
		// put count here
	}

	public BinaryTreeNode insertRecursive(BinaryTreeNode root, String word) {
		if (root == null) {
			root = new BinaryTreeNode(word);
			return root;
		}
		// left
		if (root.value.compareToIgnoreCase(word) > 0)
			root.left = insertRecursive(root.left, word);

		// right
		if (root.value.compareToIgnoreCase(word) < 0)
			root.right = insertRecursive(root.right, word);

		return root;
	}

	public void preorder(BinaryTreeNode root) {
		if (root == null)
			return;
		System.out.println(root.value);
		preorder(root.left);
		preorder(root.right);

	}

	public void inOrder(BinaryTreeNode root) {
		if (root == null)
			return;

		inOrder(root.left);
		System.out.println(root.value);
		inOrder(root.right);

	}

	public String[] findWord(String word) {
		// System.out.println("Enters findWord");
		message = new String[] { "", "" };
		return findWordRecursive(root, word);
	}

	public String[] findWordRecursive(BinaryTreeNode root, String word) {
		// System.out.println("Enters findWordRecursive");
		current = root;

		// System.out.println("word = " + word + "\ncurrent.value = " +
		// current.value);

		if (current == null) {
			// System.out.println("current == null");

			// System.out.println(Arrays.toString(message));
			return message;
		}

		// left
		else if (current.value.compareToIgnoreCase(word) > 0) {
			// && (current.left!=null) dosent work either
			post = current;
			message[1] = post.value;
			// System.out.println("traverse left and post = " + post.value);
			message = findWordRecursive(current.left, word);

			// if (current.left != null)
			// message = findWordRecursive(current.left, word);
			// else {
			// post.value = "";
			// message = findWordRecursive(post, word);
			// }
		}

		// right
		else if (current.value.compareToIgnoreCase(word) < 0) {
			// && (current.right!=null)
			pre = current;
			message[0] = pre.value;
			// System.out.println("traverse right and pre = " + pre.value);
			message = findWordRecursive(current.right, word);
		}

		// found value
		else if (current.value.compareToIgnoreCase(word) == 0) {
			return null;
		}
		// System.out.println("returning message");
		return message;

		// if (root == null)
		// return null;
		//
		// if (root.value.compareTo(word) != 0 && message[0] == null) {
		// pointer = root;
		// findWord(root.left, word);
		// }
		// message[0] = pointer.value;
		//
		// if (root.value.compareTo(word) != 0 && message[1] == null) {
		// findWord(root.right, word);
		// }
		// message[1] = root.value;
		//

		// String Above = null, Bellow= null;
		// String[] message = new String[2];
		//
		// if (root == null)
		// return null;
		//
		// // left
		// if (root.value.compareTo(word) > 0) {
		// Above = root.value;
		// findWord(root.left, word);
		// } else if (root.value.compareTo(word) == 0) {
		// message[0] = Above;
		// message[0] = "";
		// return message;
		// }
		//
		// // right
		// if (root.value.compareTo(word) < 0) {
		// findWord(root.right, word);
		// } else if (root.value.compareTo(word) == 0) {
		// message[0] = "";
		// message[0] = "";
		// return message;
		// }

		/**
		 * 
		 * @param word
		 * @return If the word is <b>found</b> this method returns <b>null</b>.
		 *         Otherwise, it returns a String array organized as follows:
		 * 
		 *         <pre>
		 *         [0] = Preceeding word in the dictionary 
		 *         [1] = Succeeding word in the dictionary 
		 *         
		 *              e.g. if the unknown word was "spelm", the result might be:
		 *              
		 *         [0] = "spells" 
		 *         [1] = "spelt"
		 *         
		 *         If there is no preceeding or succeeding word in the dictionary, set the element to "".
		 *         </pre>
		 */
	}

}
