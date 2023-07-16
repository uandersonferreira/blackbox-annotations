package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.util.DateUtil;
import lombok.RequiredArgsConstructor;
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


    public void process(
            final IWebExchange webExchange,
            final ITemplateEngine templateEngine,
            final Writer writer)
            throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();

        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ctx.setVariable("today", dateFormat.format(cal.getTime()));

        templateEngine.process("fragments/_layout", ctx, writer);

    }



//    public void process(
//            final IWebExchange webExchange,
//            final ITemplateEngine templateEngine,
//            final Writer writer) {
//
//        //Para Usar: Data-Hora | Data | Time
//        WebContext webContext = new WebContext(webExchange, webExchange.getLocale());
//        webContext.setVariable("myDateTime", dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//        webContext.setVariable("myDate", dateUtil.formatLocalDate(LocalDateTime.now()));
//        webContext.setVariable("myTime", dateUtil.formatLocalTime(LocalDateTime.now()));
//        templateEngine.process("fragments/_layout", webContext, writer);
//
//    }
}//class
