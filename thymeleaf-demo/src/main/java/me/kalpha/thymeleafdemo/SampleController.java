package me.kalpha.thymeleafdemo;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.Writer;

@Controller
public class SampleController {
    @Autowired
    TemplateEngine templateEngine;

    @SneakyThrows
    @GetMapping("/hello")
    public String hello(Model model, @RequestParam("name") String name) {
        model.addAttribute("name", name);
        String filePath = "hello-" + name + ".html";
//        String filePath = System.getProperty("user.home") + File.separator + "hello-thymeleaf.html";
        Context context = new Context();
        context.setVariable("name", name);
        @Cleanup Writer writer = new FileWriter(filePath);
        writer.write(templateEngine.process("hello.html", context));

        return "hello";
    }
}
