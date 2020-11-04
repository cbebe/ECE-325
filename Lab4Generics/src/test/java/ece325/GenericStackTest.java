package ece325;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class GenericStackTest {
    GenericStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new GenericStack<>();
    }

    @Test
    public void test_peek() {
        assertNull(stack.peek());
        stack.push(1);
        assertEquals(Integer.valueOf(1), stack.peek());
    }

    @Test
    public void test_sizeAndPush() {
        stack.push(1);
        assertEquals(1, stack.size());
    }

    @Test
    public void test_pop() {
        assertNull(stack.pop());
        stack.push(1);
        assertEquals(Integer.valueOf(1), stack.pop());
    }

    @Test
    public void test_isEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void test_calcPostfixExpression() {
        String[] expressions = { "4 1 +", // 1: = 5
                "2 6 -", // 2: = -4
                "3 3 *", // 3: = 9
                "1 4 /", // 4: = 0.25
                "2 3 ^", // 5: = 8
                "2 1 + 4 * 8 - 3 ^ 6 -", // 6: 58
                "south america" // 7: NaN
        }; // String[] expressions = { ... };

        double[] answers = { 5.0, -4.0, 9.0, 0.25, 8.0, 58.0, Double.NaN };

        for (int i = 0; i < expressions.length; ++i)
            assertEquals(answers[i], GenericStack.calcPostfixExpression(expressions[i]), 0.0001);
    }

}
