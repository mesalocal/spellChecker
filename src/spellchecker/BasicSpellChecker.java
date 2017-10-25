package spellchecker;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.apache.commons.io.*;

public class BasicSpellChecker implements SpellChecker {
	BasicDictionary impDic = new BasicDictionary();
	String sentence;
	Scanner reader;

	@Override
	public void importDictionary(String filename) throws Exception {
		impDic.importFile(filename);

	}

	@Override
	public void loadDictionary(String filename) throws Exception {
		impDic.load(filename);

	}

	@Override
	public void saveDictionary(String filename) throws Exception {
		impDic.save(filename);

	}

	@Override
	public void loadDocument(String filename) throws Exception {
		File file = new File(filename);
		reader = new Scanner(file);
		sentence = FileUtils.readFileToString(file);

	}

	@Override
	public void saveDocument(String filename) throws Exception {
		File file = new File(filename);
		FileUtils.writeStringToFile(file, sentence);
	}

	@Override
	public String getText() {

		return sentence;
	}

	/**
	 * Starts/continues a spell check of the text. Use the regular expression
	 * below to match words (it's not great, but it's simple and works OK for
	 * basic text).
	 * 
	 * <pre>
	 * \b[\w']+\b
	 * </pre>
	 * 
	 * The method returns when the first unknown word is located or when the end
	 * of the document is reached (whichever comes first). The index of the
	 * character after the unknown word is retained for use as the starting
	 * index for subsequent calls to spellCheck where continueFromPrevious is
	 * true.
	 * 
	 * @param continueFromPrevious
	 *            If false, a new spell check is started from the first
	 *            character of the document. If true, the spell check continues
	 *            from it's current location.
	 * @return If no unknown word is found this method returns null. Otherwise,
	 *         it returns a String array organized as follows:
	 * 
	 *         <pre>
	 *         [0] = Unknown word 
	 *         [1] = Index of start of unknown word.  The index is the position within the entire document.
	 *         [2] = Preceeding word in the dictionary .  If the unknown word is before all words in the dictionary, this element should be "".
	 *         [3] = Succeeding word in the dictionary.  If the unknown word is after all words in the dictionary, this element should be "".
	 *              e.g. 
	 *         [0] = "spelm"
	 *         [1] = "224"
	 *         [2] = "spells" 
	 *         [3] = "spelt"
	 *         </pre>
	 */
	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		String word;
		String[] testing;
		// System.out.println("continueFromPrevious ------------> " +
		// continueFromPrevious);
		if (continueFromPrevious) {
			// System.out.println("continueFromPrevious ------------> " +
			// continueFromPrevious);
			word = reader.next().trim();
			return spellCheckCode(word);
		}
		reader = new Scanner(sentence);
		word = reader.next();
		testing = spellCheckCode(word);
		// System.out.println(Arrays.toString(testing));
		return testing;

	}

	public String[] spellCheckCode(String word) {
		String[] returnOutput = new String[4];

		word = word.replaceAll("\\p{Punct}", "");

		// Build a regular expression pattern
		String expr = "\\b[\\w']+\\b";
		Pattern p = Pattern.compile(expr);

		// Get a matcher to process our sentence
		Matcher m = p.matcher(sentence);

		// Find all of the matches for expression in sentence

		// System.out.println("word = " + word + " impDic.find(word) = " +
		// Arrays.toString(impDic.find(word)));
		while (m.find() && (impDic.find(word) == null)) {
			// System.out.println("word = " + word);
			// System.out.println("word = " + word);
			if (reader.hasNext())
				word = reader.next().replaceAll("\\p{Punct}", "");
		}

		if (impDic.find(word) != null) {
			// System.out.println("----------------------impDic.find != null");
			returnOutput[0] = word;
			returnOutput[1] = Integer.toString(sentence.indexOf(word));
			returnOutput[2] = impDic.find(word)[0];
			returnOutput[3] = impDic.find(word)[1];
			// System.out.println(Arrays.toString(returnOutput));
			return returnOutput;
		} else
			return null;
	}

	@Override
	public void addWordToDictionary(String word) {
		impDic.add(word);

	}

	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		StringBuilder sb = new StringBuilder(sentence);
		sentence = sb.replace(startIndex, endIndex, replacementText).toString();

	}

}
