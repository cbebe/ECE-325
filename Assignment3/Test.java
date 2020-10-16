import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Test {
    public static void main(String args[]) {
        int pos = 0;
        String text = "1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;";
        Pattern p = Pattern.compile("(\\([^()]*\\)){1}+");
        Matcher m = p.matcher(text);
        m.find();
        pos = m.start();
        String g = m.group();
        System.out.format("%s found at %d\n", g, pos); // this regex matcher sucks apparently

        int start = 0;
        pos = 0;
        text = "1 + (2 + 3) + ((4 +5) + 6)";
        p = Pattern.compile("\\(");
        m = p.matcher(text);
        m.find();
        pos = m.start();
        g = m.group();
        System.out.format("%s found at %d\n", g, pos); // this regex matcher sucks apparently
        System.out.println(text.substring(start, pos));

    }
}
