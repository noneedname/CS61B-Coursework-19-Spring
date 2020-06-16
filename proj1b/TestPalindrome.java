import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();


    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("aaaaaaa"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("b"));
        assertTrue(palindrome.isPalindrome("0"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("people"));
        assertTrue(palindrome.isPalindrome("puppup"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertTrue(palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testisPalindromeVersionTwo() {
        assertTrue(palindrome.isPalindrome("flake", new OffByOne()));
        assertFalse(palindrome.isPalindrome("aaa", new OffByOne()));
        assertTrue(palindrome.isPalindrome("aab", new OffByOne()));
        assertTrue(palindrome.isPalindrome("a", new OffByOne()));
        assertFalse(palindrome.isPalindrome("cat", new OffByOne()));
    }
}
