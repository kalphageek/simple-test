package me.kalpha.regexp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        String org = "select a.aa, b.bb\n" +
                "from owner.tab1 a inner join owner.tab2 on (a.aa = tab2.aa)\n" +
                "where a.cc in (select cc from tab3 where 1=1);\n" +
                "select a.aa, b.bb\n" +
                "from owner.tab4 a, (select aa from owner.tab5) b\n" +
                "where a.aa = b.aa\n" +
                "  a.cc in (select cc from owner.tab6 where 1=1);\n" +
                "select a.aa, b.bb\n" +
                "from owner.tab7 a, owner.tabl8 b\n" +
                "where 1=1 and a.aa = b.aa\n" +
                "  a.cc >  10;\n" +
                "SELECT Count(*) AS DistinctCountries\n" +
                "FROM (SELECT DISTINCT Country FROM owner.Tab9);\n" +
                "select (select aa from owner.tab10 b where b.aa = a.aa)\n" +
                "from owner.tab11 a;\n" +
                "SELECT DISTINCT\n" +
                "     titles.title\n" +
                "FROM     owner.tab_12  AS titles\n" +
                "INNER JOIN     exp_relationships_13   AS rel\n" +
                "       ON titles.entry_id = rel.rel_child_id\n" +
                "INNER JOIN     owner.tab_14    AS channel\n" +
                "       ON rel.rel_id = channel.field_id_202\n" +
                "WHERE     channel.entry_id = 19971;\n" +
                "select * from owner.tab15 order by aa;\n" +
                "select * from owner.tab16 group by aa;\n" +
                "select aa from owner.tab17 union all select aa from owner.tab18;\n" +
                "select aa from tab19 minus select aa from tab20;\n" +
                "select aa from owner.tab21 intersect select aa from owner.tab22;\n" +
                "with aa as (select * from owner.tab23)";

        final String regex = "(?:from|join).*?(?:\\n|\\binner|\\bouter\\b|\\bself\\b|\\bcross\\b|\\bon\\b|\\bunion\\b|\\bminus\\b|\\bintersect\\b|[();]|\\bwhere\\b|\\border\\b|\\bgroup\\b)";
        final String sql = org.replace("\n"," ");
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(sql);

        Stream stream = matcher.results().
                map(t -> t.group()).//full match
                map(s -> s.split(",")).// 테이블 기준으로 줄 분리
                flatMap(Arrays::stream).// 1줄에 테이블 1개만
                map(s -> s.replaceAll("([fF][rR][oO][mM]|[jJ][oO][iI][nN]|\\(|\\)|;)", "")). //테이블명 앞쪽 문자 삭제
                map(s -> s.trim()).
                filter(s -> s.length() > 0).
                map(s -> Arrays.asList(s.split(" +")).get(0));// 테이블명만 추출
       stream.forEach(System.out::println);
    }
}
