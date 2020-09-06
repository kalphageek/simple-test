package me.kalpha.regexp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        final String regex = "from(?<from>).+?(?:join|\\n|where|[()])";
        final String string = "select a.aa, b.bb\n" +
                "from tab1 a inner join tab5 on (a.aa = tab5.aa)\n" +
                "where a.cc in (select cc from tab3 where 1=1)\n" +
                "\n" +
                "select a.aa, b.bb\n" +
                "from tab1 a, (select aa from tab2) b\n" +
                "where a.aa = b.aa\n" +
                "  a.cc in (select cc from tab3 where 1=1)\n" +
                "\n" +
                "select a.aa, b.bb\n" +
                "from tab1 a, tabl2 b\n" +
                "where 1=1 and a.aa = b.aa\n" +
                "  a.cc >  10";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);

        Stream stream = matcher.results().
                map(t -> t.group()).
                map(s -> s.split(",")).
                flatMap(Arrays::stream).
                map(s -> s.replaceAll("(from|\\(|\\))", "")).
                map(s -> s.trim()).
                filter(s -> s.length() > 0).
                map(s -> Arrays.asList(s.split(" +")).get(0));
        stream.forEach(System.out::println);
    }
}
