import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Test {

    public static boolean checkBrackets(String exp) {
        var brackets = new Stack<Character>();
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (c == '(') {
                brackets.push(c);
            } else if (c == ')') {
                if (brackets.empty())
                    return false;

                brackets.pop();
            }
        }

        return brackets.empty();
    }

    public static void main(String args[]) {

        String[] i = { "1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;",
                "1 + (2 * 3", "1 + 3) + (3 * 4) + (((3)))", "(((()(1))))" };

        for (String s : i) {
            System.out.println(checkBrackets(s));
        }
    }
}
