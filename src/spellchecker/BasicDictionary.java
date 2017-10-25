package spellchecker;

import java.io.*;

import org.apache.commons.io.*;

public class BasicDictionary implements Dictionary {

	// ArrayList<String> words = new ArrayList<String>();
	// BasicDictionary current = new BasicDictionary();
	BinaryTree currentWord = new BinaryTree();
	int count = 0;

	@Override
	public void importFile(String filename) throws Exception {

		File file = new File(filename);
		String tempDictionary = FileUtils.readFileToString(file);

		String[] words = tempDictionary.split("\r\n");
		// currentWord = null;
		importRecursion(words, 0, words.length - 1);

		// long seed = System.nanoTime();
		// Collections.shuffle(words, new Random(seed));

		/**
		 * Replaces the current dictionary with words imported from the
		 * specified text file. Words in the file are in lexicographical (ASCII)
		 * order, one word per line.
		 * 
		 * @param filename
		 *            Name (possibly including a path) of the file to import.
		 * @throws Exception
		 */
	}

	public void importRecursion(String[] words, int start, int end) {

		if (start > end) {
			// currentWord.insert(words[start]);

			return;
		}
		int mid = (start + end) / 2;
		currentWord.insert(words[mid]);
		count++;
		// left
		importRecursion(words, start, mid - 1);
		// right
		importRecursion(words, mid + 1, end);

	}

	@Override
	public void load(String filename) throws Exception {
		File file = new File(filename);
		String tempDictionary = FileUtils.readFileToString(file);
		String[] words = tempDictionary.split("\r\n");
		for (String word : words) {
			currentWord.insert(word);
			count++;

		}

		/**
		 * Loads the specified file from a previously saved dictionary tree
		 * file. The file format is text, with one word per line.
		 * 
		 * @param filename
		 *            Name (possibly including a path) of the file to load from.
		 * @throws Exception
		 */

	}

	@Override
	public void save(String filename) throws Exception {
		PrintStream out = new PrintStream(new FileOutputStream(filename));
		System.setOut(out);
		currentWord.preorder(currentWord.root);
		out.close();
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

		/**
		 * Saves the dictionary tree to the specified file in preorder. The file
		 * format is text, with one word per line.
		 * 
		 * @param filename
		 *            Name (possibly including a path) of the file to save to.
		 *            If the file exists, it is overwritten.
		 * @throws Exception
		 */

	}

	@Override
	public String[] find(String word) {
		return currentWord.findWord(word);
	}

	@Override
	public void add(String word) {
		currentWord.insert(word);
		// TODO Auto-generated method stub

	}

	@Override
	public BinaryTreeNode getRoot() {
		// TODO Auto-generated method stub
		return currentWord.root;
	}

	@Override
	public int getCount() {
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	}

}
