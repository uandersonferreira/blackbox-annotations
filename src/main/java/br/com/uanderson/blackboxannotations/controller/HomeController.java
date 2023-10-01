package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.text.SimpleDateFormat;
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


}//class
