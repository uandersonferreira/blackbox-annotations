package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(path = "v1/app/cpd/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @GetMapping(path = "/list")
    public String listAllPageable(Model model) {
        String keyword = "";
        return findPaginated(1, "nome", "asc", keyword, model);
    }

    @GetMapping(path = "/cadastro")
    public ModelAndView cadastro(Funcionario funcionario) {
        ModelAndView modelAndView = new ModelAndView("funcionario/cadastro");
        modelAndView.addObject("funcionario", funcionario);
        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView save(Funcionario funcionario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return cadastro(funcionario);//Retorna o funcionario para a page de cadrastro, preservando os dados do objeto/Funcionario
            //que tentou realizar a ação.
        }

//        funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));
        funcionarioService.save(funcionario);//Se não tiver erro, irá salvar o Funcionário
        return cadastro(new Funcionario());//É retornar a page de cadastro com um novo funcionário vazio;
    }


    @GetMapping(path = "/editar/{id}")
    public ModelAndView findByIdAndUpdate(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findByIdOrThrowBadRequestException(id);
        return cadastro(funcionario);
    }

    @GetMapping(path = "/delete/{id}")
    public ModelAndView deleteById(@PathVariable Long id) {
        funcionarioService.delete(id);
        return new ModelAndView("redirect:/v1/app/cpd/funcionarios/list");
    }

    @PostMapping("/update")
    public ModelAndView update(Funcionario funcionario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return cadastro(funcionario);

        funcionarioService.update(funcionario);
        return new ModelAndView("redirect:/v1/app/cpd/funcionarios/list");
    }

    @GetMapping(path = "/page/{pageNow}")
    public String findPaginated(@PathVariable(value = "pageNow") int pageNow,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam("keyword") String keyword,
                                Model model) {
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

        return "funcionario/listar_funcionario";
    }



}//class
