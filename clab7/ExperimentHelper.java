/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int maxDepth = (int) Math.floor(Math.log(N) / Math.log(2));
        double totalLength = 0;
        for (int i = 0; i < maxDepth; i++) {
            totalLength += Math.pow(2, i) * i;
        }
        // calculate the leaf nodes.
        totalLength += (N - Math.pow(2, maxDepth) + 1) * maxDepth;
        return (int) totalLength;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return 1.0 * optimalIPL(N) / N;
    }

    public static void insertRandomNumber(BST<Integer> bst) {
        int random = RandomGenerator.getRandomInt(10000);
        while (bst.contains(random)) {
            random = RandomGenerator.getRandomInt(10000);
        }
        bst.add(random);
    }

    public static void deleteRandomUnit(BST<Integer> bst) {
        int random = RandomGenerator.getRandomInt(10000);
        while (!bst.contains(random)) {
            random = RandomGenerator.getRandomInt(10000);
        }
        bst.deleteTakingSuccessor(random);
    }

    public static void deleteRandomUnit2(BST<Integer> bst) {
        int random = RandomGenerator.getRandomInt(10000);
        while (!bst.contains(random)) {
            random = RandomGenerator.getRandomInt(10000);
        }
        bst.deleteTakingRandom(random);
    }
}
