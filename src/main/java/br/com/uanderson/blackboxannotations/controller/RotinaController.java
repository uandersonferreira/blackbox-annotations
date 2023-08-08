package br.com.uanderson.blackboxannotations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/rotina")
public class RotinaController {

    @GetMapping
    public String listar(){
        return "rotinas/rotina_diario";
    }

}
