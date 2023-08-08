package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

@Controller
@RequestMapping(path = {"/"})
@RequiredArgsConstructor
@Log4j2
public class HomeController {

    private final DateUtil dateUtil;
    @GetMapping
    public String home() {
        return "index";
    }
    @GetMapping(path = "/login")
    public String login() {
        return "logar_registrar";
    }

    @GetMapping(path = "/login/registrar")
    public String registrar() {
        return "";
    }

    @GetMapping(path = "/layout")
    public String layout() {
        return "fragments/_layout";
    }


//    public void process(
//            final IWebExchange webExchange,
//            final ITemplateEngine templateEngine,
//            final Writer writer)
//            throws Exception {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
//        Calendar cal = Calendar.getInstance();
//
//        WebContext webContext = new WebContext(webExchange, webExchange.getLocale());
//        webContext.setVariable("today", dateFormat.format(cal.getTime()));
//
//        templateEngine.process("fragments/_menuTop", webContext, writer);
//
//    }

}//class
