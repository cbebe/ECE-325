import java.util.regex.Pattern;

/**
 * ECE 325 - Fall 2020 <br/>
 * Assignment 2: Java regular expressions <br/>
 * Test cookies using regular expressions
 * <p>
 * 
 * @author Charles Ancheta 1581672
 */
public class TestCookies {

    // save the variable so we don't have to build the regex every time
    static String cookieRegex;

    /**
     * Create the regex string from the provided rules
     */
    static void buildRegexString() {
        var ctlChars = "\\x00-\\x1F\\x7F";

        var header = "Set-Cookie:";
        // at least 1 of any char except ctl chars and separators
        var name = String.format("[^%s()<>@,;:\\\\\"\\/\\[\\]?=\\{} ]+", ctlChars);

        // any number of chars except ctl, space, double quote, comma, and backslash
        var valPat = String.format("[^%s\\x20\",;\\\\]*", ctlChars);
        // with brackets and without brackets
        var value = String.format("(?:\"%s\"|%s)", valPat, valPat);

        var days = "(?:Mon|Tue|Wed|Thu|Fri|Sat|Sun)";
        var months = "(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
        // HH:MM:SS time format
        var time = "\\d{2}:\\d{2}:\\d{2} GMT";
        var expiresAt = String.format("Expires=%s, \\d{2} %s \\d{4} %s", days, months, time);

        var maxAge = "Max-Age=[1-9]\\d*"; // minimum is 1
        var domain = "Domain=(?:\\.?[a-zA-z](?:[a-zA-Z0-9-]*[a-zA-Z0-9])*)*";
        var secureHttp = "Secure|HttpOnly";
        var path = String.format("Path=[^%s;]", ctlChars);

        var options =
                String.format("(?:%s|%s|%s|%s|%s)", expiresAt, maxAge, domain, secureHttp, path);

        cookieRegex = String.format("^%s %s=%s(?:; %s)*$", header, name, value, options);
    }

    /**
     * Verify a cookie and return the verification result
     * 
     * @param cookie The cookie string
     * @return True for a legal cookie; false for an illegal one
     */
    public static boolean verifyCookie(String cookie) {
        boolean legal = false;
        Pattern cookiePattern = Pattern.compile(cookieRegex);
        legal = cookiePattern.matcher(cookie).matches();

        return legal;
    }

    /**
     * Main entry
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String[] cookies = {
                // Legal cookies:
                "Set-Cookie: ns1=\"alss/0.foobar^\"", // 01 name=value
                "Set-Cookie: ns1=", // 02 empty value
                "Set-Cookie: ns1=\"alss/0.foobar^\"; Expires=Tue, 18 Nov 2008 16:35:39 GMT", // 03
                                                                                             // Expires=time_stamp
                "Set-Cookie: ns1=; Domain=", // 04 empty domain
                "Set-Cookie: ns1=; Domain=.srv.a.com-0", // 05 Domain=host_name
                "Set-Cookie: lu=Rg3v; Expires=Tue, 18 Nov 2008 16:35:39 GMT; Path=/; Domain=.example.com; HttpOnly", // 06
                // Illegal cookies:
                "Set-Cookie:", // 07 empty cookie-pair
                "Set-Cookie: sd", // 08 illegal cookie-pair: no "="
                "Set-Cookie: =alss/0.foobar^", // 09 illegal cookie-pair: empty name
                "Set-Cookie: ns@1=alss/0.foobar^", // 10 illegal cookie-pair: illegal name
                "Set-Cookie: ns1=alss/0.foobar^;", // 11 trailing ";"
                "Set-Cookie: ns1=; Expires=Tue 18 Nov 2008 16:35:39 GMT", // 12 illegal Expires
                                                                          // value
                "Set-Cookie: ns1=alss/0.foobar^; Max-Age=01", // 13 illegal Max-Age: starting 0
                "Set-Cookie: ns1=alss/0.foobar^; Domain=.0com", // 14 illegal Domain: starting 0
                "Set-Cookie: ns1=alss/0.foobar^; Domain=.com-", // 15 illegal Domain: trailing
                                                                // non-letter-digit
                "Set-Cookie: ns1=alss/0.foobar^; Path=", // 16 illegal Path: empty
                "Set-Cookie: ns1=alss/0.foobar^; httponly", // 17 lower case
        };

        buildRegexString();
        for (int i = 0; i < cookies.length; i++)
            System.out.println(String.format("Cookie %2d: %s", i + 1,
                    verifyCookie(cookies[i]) ? "Legal" : "Illegal"));
    }

}
