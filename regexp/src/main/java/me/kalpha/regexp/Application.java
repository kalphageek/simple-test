package me.kalpha.regexp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        String org = "select a.aa, b.bb from tab1 a inner join tab2 on (a.aa = tab2.aa) where a.cc in (select cc from tab3 where 1=1);\n" +
                "select a.aa, b.bb from tab4 a, (select aa from tab5) b where a.aa = b.aa and   a.cc in (select cc from tab6 where 1=1);\n" +
                "select a.aa, b.bb from tab7 a, tab8 b where 1=1 and a.aa = b.aa  a.cc >  10;\n" +
                "SELECT Count(*) AS col1 FROM (SELECT DISTINCT Country FROM Tab9);\n" +
                "select (select aa from tab10 b where b.aa = a.aa) from tab11 a;\n" +
                "SELECT DISTINCT     titles.title FROM     tab12  AS titles\n" +
                "   INNER JOIN     exp_relationships13   AS rel      ON titles.entry_id = rel.rel_child_id\n" +
                "   INNER JOIN     tab14    AS channel  ON rel.rel_id = channel.field_id_202 WHERE     channel.entry_id = 19971;\n" +
                "select * from tab15 union select * from tab16 group by aa;\n" +
                "select aa from tab17 union  all select aa from tab18;\n" +
                "select aa from tab19 minus select aa from tab20;\n" +
                "select aa from owner.tab21 intersect select aa from tab22;\n" +
                "with vtab23 as (select * from owner.tab24) select col from vtab23;";

        final String regex = "(?:from|join).*?(?:\\n|\\binner|\\bouter\\b|\\bself\\b|\\bcross\\b|\\bon\\b|\\bunion +(?:all)?|\\bminus\\b|\\bintersect\\b|[();]|\\bwhere\\b|\\border\\b|\\bgroup\\b)";
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
        //"with"가 나오는 경우 동일한 테이블명은 모두 제외
       stream.forEach(System.out::println);
    }
}
