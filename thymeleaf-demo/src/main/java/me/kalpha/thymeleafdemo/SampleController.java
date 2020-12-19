package me.kalpha.thymeleafdemo;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;

@Controller
public class SampleController {
    @Autowired
    TemplateEngine templateEngine;

    /**
     * Templete View를 이용해 파라미터로 받은 hello-[name].html를 generate하고, 그 결과를 html과 platintext로 저장한다.
     * @param model ModelAndView의 Model
     * @param name Param name
     * @return Thymeleaf에 의해 generate된 View
     */
    @SneakyThrows(IOException.class)
    @GetMapping("/hello")
    public String hello1(Model model, @RequestParam("name") String name) {
        // Templet에 전달할 변수
        model.addAttribute("name", name);

        // tql파일을 저장할 Directory 생성
        String dir = "tqls";
        File tqlDir = new File(dir);
        tqlDir.mkdirs();

        // Templete 결과 저장 Plain Text 파일
        Context context = new Context();
        context.setVariable("name", name);
        String helloHtml = templateEngine.process("hello.html", context);
        String helloText = br2nl(helloHtml);

        String tqlPath = /*System.getProperty("user.home") + */ dir + File.separator + "hello-" + name + ".tql";
        File tqlFile = new File(tqlPath);
        Writer tqlWriter = new FileWriter(tqlFile);
        tqlWriter.write(helloText);
        tqlWriter.close();

        System.out.println("helloText : " + helloText);
        System.out.println("helloHtml : " + helloHtml);

        // Templete 결과 View
        return "hello";
    }

    /**
     * html의 원래 new line 뿐 아니라 br or p tag를 new line으로 변경해준다.
     * @param html
     * @return plaintext
     */
    public static String br2nl(String html) {
        if (html == null) {
            return html;
        }
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");

        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }
}
