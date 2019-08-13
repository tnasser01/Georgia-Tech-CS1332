package Algorithms.PatternMatching;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pattern Matching student tests.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class TnasserPatternMatchingTests {
    private List<Integer> answer = new ArrayList<Integer>();
    private List<Integer> answer2 = new ArrayList<Integer>();
    private String pattern;
    private String text;
    private String pattern2;
    private String text2;

    private CharacterComparator comparator;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        pattern = "aaaaab";
        text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        answer.add(30);

        pattern2 = "ab";
        text2 = "aab";
        answer2.add(1);

        comparator = new CharacterComparator();
    }


    @Test(timeout = TIMEOUT)
    public void testSmallAlphabet1() {
        assertEquals(answer, PatternMatching.bruteForce(pattern, text,
                comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);

    }

    @Test(timeout = TIMEOUT)
    public void testSmallAlphabet2() {
        assertEquals(answer2, PatternMatching.bruteForce(pattern2, text2,
                comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        System.out.println(comparator.getCount());
    }

    @Test(timeout = TIMEOUT)
    public void testSmallAlphabet3KMP() {
        assertEquals(answer2, PatternMatching.kmp(pattern2, text2,
                comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        System.out.println(comparator.getCount());
    }


}
