package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@Controller
@RequestMapping(path = "v1/app/cpd/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @GetMapping(path = "/list")
    public String listAllPageable(Model model, @AuthenticationPrincipal Funcionario userPrincipal) {
        String keyword = "";
        return mostrarBuscaPaginada(1, "nome", "asc", keyword, model, userPrincipal);
    }

    @GetMapping(path = "/form")
    public ModelAndView mostrarPaginaDeCadastro(Funcionario funcionario) {
        ModelAndView modelAndView = new ModelAndView("funcionario/cadastro");
        modelAndView.addObject("funcionario", funcionario);
        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(@Valid Funcionario funcionario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return mostrarPaginaDeCadastro(funcionario);//Retorna o funcionario para a page de cadrastro, preservando os dados do objeto/Funcionario
            //que tentou realizar a ação.
        }else {
            funcionarioService.save(funcionario);//Se não tiver erro, irá salvar o Funcionário
        }
        return mostrarPaginaDeCadastro(new Funcionario());//É retornar a page de mostrarPaginaDeCadastro com um novo funcionário vazio;
    }


    @GetMapping(path = "/editar/{id}")
    public ModelAndView findByIdAndUpdate(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findByIdOrThrowBadRequestException(id);
        return mostrarPaginaDeCadastro(funcionario);
    }

    @GetMapping(path = "/delete/{id}")
    public ModelAndView deleteById(@PathVariable Long id) {
        funcionarioService.delete(id);
        return new ModelAndView("redirect:/v1/app/cpd/funcionarios/list");
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Funcionario funcionario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return mostrarPaginaDeCadastro(funcionario);
        }

        funcionarioService.update(funcionario);
        return new ModelAndView("redirect:/v1/app/cpd/funcionarios/list");
    }

    @GetMapping(path = "/page/{pageNow}")
    public String mostrarBuscaPaginada(@PathVariable(value = "pageNow") int pageNow,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam("keyword") String keyword,
                                Model model,  @AuthenticationPrincipal Funcionario userPrincipal) {
        int pageSize = 5;

        Page<Funcionario> page = funcionarioService.findAllPaginated(pageNow, pageSize, sortField, sortDir, keyword);
        List<Funcionario> funcionarios = page.getContent();

        model.addAttribute("currentPage", pageNow);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("idFuncionarioLogado", userPrincipal.getId());

        return "funcionario/listar_funcionario";
    }


}//class
