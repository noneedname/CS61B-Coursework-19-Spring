
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> chars = new ArrayDeque<>();
        char[] letters = word.toCharArray();
        for (char letter : letters) {
            chars.addLast(letter);
        }
        return chars;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> chars1 = wordToDeque(word);
        Deque<Character> chars2 = wordToDeque(word);
        int size = chars1.size();
        boolean isPalindrome = true;

        while (size != 0 && isPalindrome) {
            size--;
            isPalindrome = chars1.removeFirst().equals(chars2.removeLast());
        }
        return isPalindrome;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> chars1 = wordToDeque(word);
        Deque<Character> chars2 = wordToDeque(word);
        int remainingChar = chars1.size() / 2;
        boolean isPalindrome = true;

        while (remainingChar != 0 && isPalindrome) {
            remainingChar--;
            isPalindrome = cc.equalChars(chars1.removeFirst(), chars2.removeLast());
        }
        return isPalindrome;
    }
}


