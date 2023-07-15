package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import br.com.uanderson.blackboxannotations.util.DateUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

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
    public String layout(Model model) {
        //Para Usar: Data-Hora | Data | Time
        model.addAttribute("myDateTime", dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        model.addAttribute("myDate", dateUtil.formatLocalDate(LocalDateTime.now()));
        model.addAttribute("myTime", dateUtil.formatLocalTime(LocalDateTime.now()));
        return "fragments/_layout";
    }




}//class
