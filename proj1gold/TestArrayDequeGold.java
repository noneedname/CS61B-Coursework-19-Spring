import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> studentArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionArray = new ArrayDequeSolution<>();
        int numTests = 100;
        String message = "";

        //Test addLast and get
        for (int i = 0; i < numTests; i++) {
            int numberBetweenZeroAndOne = StdRandom.uniform(100);
            studentArray.addLast(numberBetweenZeroAndOne);
            solutionArray.addLast(numberBetweenZeroAndOne);
            message += "addLast(" + numberBetweenZeroAndOne + ")\n";
            assertEquals(message, solutionArray.get(i), studentArray.get(i));
        }

        //Test addFirst and get
        for (int i = 0; i < numTests; i++) {
            int numberBetweenZeroAndOne = StdRandom.uniform(100);
            studentArray.addFirst(numberBetweenZeroAndOne);
            solutionArray.addFirst(numberBetweenZeroAndOne);
            message += "addFirst(" + numberBetweenZeroAndOne + ")\n";
            assertEquals(message, solutionArray.get(0), studentArray.get(0));
        }

        //Test removeLast
        for (int i = 0; i < numTests; i++) {
            Integer act = studentArray.removeLast();
            Integer exp = solutionArray.removeLast();
            message += "removeLast()\n";
            assertEquals(message, exp, act);
        }

        //Test removeFirst
        for (int i = 0; i < numTests; i++) {
            Integer act = studentArray.removeFirst();
            Integer exp = solutionArray.removeFirst();
            message += "removeFirst()\n";
            assertEquals(message, exp, act);
        }
    }
}
