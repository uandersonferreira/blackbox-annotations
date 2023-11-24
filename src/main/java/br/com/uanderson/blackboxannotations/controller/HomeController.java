package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import br.com.uanderson.blackboxannotations.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = {"/"})
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final FuncionarioService funcionarioService;

    private final DateUtil dateUtil;

    @GetMapping
    @RequestMapping(path = {"/", "home"})
    public String home() {
        return "index";
    }

    @GetMapping(path = "layout")
    public String layout() {
        return "fragments/_layout";
    }

    @GetMapping(path = "/login")
    public ModelAndView mostrarPaginaDeCadastro(Funcionario funcionario) {
        ModelAndView modelAndView = new ModelAndView("logar_registrar");
        modelAndView.addObject("funcionario", funcionario);
        return modelAndView;
    }

    @PostMapping(path = "/funcionario/login-registrar/save")
    public ModelAndView save(@Valid Funcionario funcionario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return mostrarPaginaDeCadastro(funcionario);//Retorna o funcionario para a page de cadrastro, preservando os dados do objeto/Funcionario
            //que tentou realizar a ação.
        }
        funcionarioService.save(funcionario);//Se não tiver erro, irá salvar o Funcionário
        return mostrarPaginaDeCadastro(new Funcionario());//É retornar a page de mostrarPaginaDeCadastro com um novo funcionário vazio;
    }


}//class
