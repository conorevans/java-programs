/**
 * @author Conor Evans
 * @version 1.0
 * 
 *          This class implements the Knuth-Morris-Pratt (KMP) algorithm for
 *          searching through text for a string pattern using a Deterministic
 *          Finite Automaton (DFA).
 * 
 */
public class KMPSearch {

	private static final int ALPHABET_SIZE = 256;
	private static int[][] dfa;

	/**
	 * The method creates a dfa for a pattern pat.
	 */
	public static void dfa(String pat) {
		dfa = new int[ALPHABET_SIZE][pat.length()];
		dfa[pat.charAt(0)][0] = 1;
		for (int i = 0, j = 1; j < pat.length(); j++) {
			for (int k = 0; k < ALPHABET_SIZE; k++)
				dfa[k][j] = dfa[k][i]; // copy mismatch cases

			dfa[pat.charAt(j)][j] = j + 1; // set match case
			i = dfa[pat.charAt(j)][i]; // update restart state
		}
	}

	/**
	 * The method checks whether a pattern pat occurs at least once in String
	 * txt.
	 * 
	 * @return boolean
	 */
	public static boolean contains(String txt, String pat) {
		if (txt.isEmpty() || pat.isEmpty())
			return false;
		else if (pat.length() > txt.length())
			return false;
		else
			return (KMPSearch.searchFirst(txt, pat) != -1);
	}

	/**
	 * The method returns the index of the first occurrence of a pattern pat in
	 * String txt. It should return -1 if the pat is not present
	 * 
	 * @return int
	 */
	public static int searchFirst(String txt, String pat) {
		if (txt.isEmpty() || pat.isEmpty())
			return -1;
		else if (pat.length() > txt.length())
			// if pattern length is greater than text length, return -1
			return -1;
		else {
			dfa(pat);
			int i, j;
			for (i = 0, j = 0; i < txt.length() && j < pat.length(); i++)
				j = dfa[txt.charAt(i)][j];

			if (j == pat.length())
				return i - pat.length();
			else
				return -1;
		}
	}

	/**
	 * The method returns the number of non-overlapping occurences of a pattern
	 * pat in String txt.
	 * 
	 * @return int
	 */
	public static int searchAll(String txt, String pat) {
		int numOccurrences = 0;

		if (txt.isEmpty() || pat.isEmpty())
			return 0;
		else if (pat.length() > txt.length())
			// if length of pattern is greater than length of whole string
			// return -1
			return 0;
		else if (KMPSearch.searchFirst(txt, pat) == -1)
			return 0;
		else {
			dfa(pat);
			int i = 0, j = 0;
			while (i < txt.length()) {
				for (j = 0; i < txt.length() && j < pat.length(); i++)
					j = dfa[txt.charAt(i)][j];

				if (j == pat.length())
					numOccurrences++;

			}
		}

		return numOccurrences;
	}
}