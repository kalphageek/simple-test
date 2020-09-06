package me.kalpha.regexp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        String org = "select a.aa, b.bb\n" +
                "from tab1 a inner join tab2 on (a.aa = tab2.aa)\n" +
                "where a.cc in (select cc from tab3 where 1=1);\n" +
                "select a.aa, b.bb\n" +
                "from tab4 a, (select aa from tab5) b\n" +
                "where a.aa = b.aa\n" +
                "  a.cc in (select cc from tab6 where 1=1);\n" +
                "select a.aa, b.bb\n" +
                "from tab7 a, tabl8 b\n" +
                "where 1=1 and a.aa = b.aa\n" +
                "  a.cc >  10;\n" +
                "SELECT Count(*) AS DistinctCountries\n" +
                "FROM (SELECT DISTINCT Country FROM Tab9);\n" +
                "select (select aa from tab10 b where b.aa = a.aa)\n" +
                "from tab11 a;\n" +
                "SELECT DISTINCT\n" +
                "     titles.title\n" +
                "FROM     tab_12  AS titles\n" +
                "INNER JOIN     exp_relationships_13   AS rel\n" +
                "       ON titles.entry_id = rel.rel_child_id\n" +
                "INNER JOIN     tab_14    AS channel\n" +
                "       ON rel.rel_id = channel.field_id_202\n" +
                "WHERE     channel.entry_id = 19971;\n" +
                "select * from tab15 order by aa;\n" +
                "select * from tab16 group by aa;\n" +
                "selet aa from tab17 union all select aa from tab18;\n" +
                "selet aa from tab19 minus select aa from tab20;\n" +
                "selet aa from tab21 intersect select aa from tab22;";

        final String regex = "(?:from|join).*?(?:\\n|\\binner|\\bouter\\b|\\bself\\b|\\bcross\\b|\\bon\\b|\\bunion\\b|\\bminus\\b|\\bintersect\\b|[();]|\\bwhere\\b|\\border\\b|\\bgroup\\b)";
        final String sql = org.replace("\n"," ");
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(sql);

        Stream stream = matcher.results().
                map(t -> t.group()).
                map(s -> s.split(",")).
                flatMap(Arrays::stream).
                map(s -> s.replaceAll("([fF][rR][oO][mM]|[jJ][oO][iI][nN]|\\(|\\)|;)", "")).
                map(s -> s.trim()).
                filter(s -> s.length() > 0).
                map(s -> Arrays.asList(s.split(" +")).get(0));
        stream.forEach(System.out::println);
    }
}
