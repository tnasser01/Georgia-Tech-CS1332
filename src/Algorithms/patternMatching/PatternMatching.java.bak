package Algorithms.PatternMatching;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * pattern matching algorithms - brute force, kmp, and boyer moore
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @GTID 903397126
 * @version 1
 */
public class PatternMatching {

    /**
     * Brute force pattern matching algorithm to find all matches.
     *
     * You should check each substring of the text from left to right,
     * stopping early if you find a mismatch.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> bruteForce(CharSequence pattern,
          CharSequence text, CharacterComparator comparator) {

        if (pattern == null || pattern.length() == 0 || text == null
                || comparator == null) {
            throw new IllegalArgumentException("Pattern, text, and comparator"
                    + " passed in must not be null.");
        }
        List<Integer> matches = new ArrayList<Integer>();
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int k = 0;
            while (k < m && comparator.compare(text.charAt(i + k),
                    pattern.charAt(k)) == 0) {
                k++;
            }
            if (k == m) {
                matches.add(i);
            }
        }
        return matches;
    }


    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {

        if (pattern == null || pattern.length() == 0 || text == null
                || comparator == null) {
            throw new IllegalArgumentException("Pattern, text, and comparator"
                    + " passed in must not be null.");
        }

        int n = text.length();
        int m = pattern.length();

        if (m > n) {
            return new ArrayList<Integer>();
        }

        int[] failureTable = buildFailureTable(pattern, comparator);
        List<Integer> matches = new ArrayList<Integer>();

        int i = 0;
        int k = 0;


        while (i <= n - m) {
            while (k < m && comparator.compare(text.charAt(i + k), pattern.charAt(k)) == 0) {
                k++;
            }
            //if no match was found at the first char
            if (k == 0) {
                i++;
            //else (match was found for some subset of the pattern)
            } else {
                //if entire pattern matched
                if (k == m) {
                    matches.add(i);
                }
                //mismatch found mid-pattern so lookup next pos in failure tbl
                int overlap = failureTable[k - 1];
                //shift text pointer
                i += k - overlap;
                //reset pattern pointer
                k = overlap;

            }

        }
        return matches;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern or comparator is null
     * @param pattern a {@code CharSequence} you're building a failure table for
     * @param comparator you MUST use this for checking character equality
     * @return integer array holding your failure table
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {

        if (pattern == null || comparator == null) {
            throw new IllegalArgumentException("Pattern and comparator passed"
                    + " in must not be null to build failure table.");
        }

        if (pattern.length() == 0) {
            return new int[0];
        }

        int[] failureTable = new int[pattern.length()];

        int i = 0;
        int j = 1;

        failureTable[0] = 0;
        while (j < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                failureTable[j] = i + 1;
                i++;
                j++;
            } else if (i > 0) {
                i = failureTable[i - 1];
            } else {
                failureTable[j] = 0;
                j++;
            }
        }

        return failureTable;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for the pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                       CharSequence text, CharacterComparator comparator) {

        if (pattern == null || pattern.length() == 0 || text == null
                || comparator == null) {
            throw new IllegalArgumentException("Pattern, text, and comparator"
                    + " passed in must not be null.");
        }

        int n = text.length();
        int m = pattern.length();
        if (m > n) {
            return new ArrayList<Integer>();
        }
        List<Integer> matches = new ArrayList<Integer>();

        Map<Character, Integer> last = buildLastTable(pattern);
        int i = m - 1;
        int k = m - 1;

        while (i < n) {
            if (comparator.compare(text.charAt(i), pattern.charAt(k)) == 0) {
                if (k == 0) {
                    matches.add(i);
                    i += m;
                    k = m - 1;
                } else {
                    i--;
                    k--;
                }

            } else {
                Integer lastOcc = last.get(text.charAt(i));
                if (lastOcc == null) {
                    i += m - Math.min(k, 1 + (-1));
                } else {
                    i += m - Math.min(k, 1 + lastOcc);
                }
                k = m - 1;

            }


        }
        return matches;
    }


    /**
         * Builds last occurrence table that will be used to run the Boyer Moore
         * algorithm.
         *
         * Note that each char x will have an entry at table.get(x).
         * Each entry should be the last index of x where x is a particular
         * character in your pattern.
         * If x is not in the pattern, then the table will not contain the key
         * x, and you will have to check for that in your Boyer Moore
         * implementation.
         *
         * Ex. octocat
         *
         * table.get(o) = 3
         * table.get(c) = 4
         * table.get(t) = 6
         * table.get(a) = 5
         * table.get(everything else) = null, which you will interpret in
         * Boyer-Moore as -1
         *
         * If the pattern is empty, return an empty map.
         *
         * @throws IllegalArgumentException if the pattern is null
         * @param pattern a {@code CharSequence} you are building last table for
         * @return a Map with keys of all of the characters in the pattern
         * mapping to their last occurrence in the pattern
         */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern to build last "
                    + "occurence table cannot be null.");
        }

        if (pattern.toString().equals("")) {
            return new HashMap<Character, Integer>();
        }

        int m = pattern.length();

        //make the last occurance table
        Map<Character, Integer> last = new HashMap<Character, Integer>();

        for (int k = 0; k < m; k++) {
            last.put(pattern.charAt(k), k);
        }
        //last.put('*', -1);
        return last;
    }

}
