package br.com.uanderson.blackboxannotations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/"})
public class HomeController {

    @GetMapping
    public String home() {
        return "index";
    }
    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }    @GetMapping(path = "/login/registrar")
    public String registrar() {
        return "registrar";
    }




}//class
