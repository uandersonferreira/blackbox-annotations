package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.dto.FuncionarioPutRequestBody;
import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(path = "v1/app/cpd/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @GetMapping(path = "/list")
    public String listAllPageable(ModelMap modelMap,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "8") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Funcionario> funcionarios = funcionarioService.listAllPageable(pageRequest);
        modelMap.addAttribute("funcionarios", funcionarios);
        modelMap.addAttribute("paginas", new int[funcionarios.getTotalPages()]);

        modelMap.addAttribute("paginaAtual", size);
        return "funcionario/pagination";
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


    @GetMapping(path = "/find-by/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        return new ResponseEntity<>(funcionarioService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{id}")
    public ModelAndView deleteById(@PathVariable Long id) {
        funcionarioService.delete(id);
        return new ModelAndView("redirect:/v1/app/cpd/funcionarios/list");
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> update(@RequestBody FuncionarioPutRequestBody funcionarioPutRequestBody) {
        funcionarioService.update(funcionarioPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}//class
