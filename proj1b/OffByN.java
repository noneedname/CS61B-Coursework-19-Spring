public class OffByN implements CharacterComparator {

    private int numDiff;

    public OffByN(int n) {
        numDiff = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = (int) x - (int) y;
        return  Math.abs(diff) == numDiff;
    }
}
