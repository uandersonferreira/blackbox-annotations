package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = {"/"})
@RequiredArgsConstructor
public class HomeController {
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
