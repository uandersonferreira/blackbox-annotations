package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Postagem;
import br.com.uanderson.blackboxannotations.service.PostagemService;
import br.com.uanderson.blackboxannotations.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/v1/app/cpd/postagens")
@RequiredArgsConstructor
@Log4j2
public class PostagemController {
    //    private final PostagemRepository postagemRepository;
    private final PostagemService postagemService;

    @GetMapping(path = "/form")
    public ModelAndView mostrarPaginaDeCadastro(Postagem postagem) {
        ModelAndView mv = new ModelAndView("anotacoes/create_post");
        mv.addObject("postagem", postagem);
        return mv;
    }

    @PostMapping(path = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView cadastroDeClient(@ModelAttribute Postagem postagem, @RequestParam("file") MultipartFile imagem){
        ModelAndView mv =  new ModelAndView("anotacoes/create_post");

        mv.addObject("postagem", postagem);
        UUID uuid = UUID.randomUUID();
        try {
            if (UploadUtil.fazerUploadImagem(imagem, uuid)) {
                postagem.setImagem( uuid + imagem.getOriginalFilename());
            }

            postagemService.save(postagem);
           log.info("Salvo com sucesso: '{}' ", postagem.getTitulo() + " " + postagem.getImagem());

            return mostrarPaginaDeCadastro(new Postagem());
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            log.info("Erro ao salvar '{}'",  e.getMessage());
            return mv;
        }
    }

    @GetMapping(path = "/list")
    public String listAllPageable(Model model) {
        String keyword = "";
        return mostrarBuscaPaginada(1, "titulo", "asc", keyword, model);
    }

    @GetMapping(path = "/page/{pageNow}")
    public String mostrarBuscaPaginada(@PathVariable(value = "pageNow") int pageNow,
                                       @RequestParam("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir,
                                       @RequestParam("keyword") String keyword,
                                       Model model) {
        int pageSize = 3;

        Page<Postagem> page = postagemService.findAllPaginated(pageNow, pageSize, sortField, sortDir, keyword);
        List<Postagem> postagens = page.getContent();

        model.addAttribute("currentPage", pageNow);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        model.addAttribute("postagens", postagens);

        return "anotacoes/listar_anotacoes";
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView findByIdAndUpdate(@PathVariable Long id) {
        Postagem postagem = postagemService.findByIdOrThrowBadRequestException(id);
        return mostrarPaginaDeCadastro(postagem);
    }

    @GetMapping(path = "/delete/{id}")
    public ModelAndView deleteById(@PathVariable Long id) {
        postagemService.delete(id);
        return new ModelAndView("redirect:/v1/app/cpd/postagens/list");
    }

    @PostMapping("/update")
    public ModelAndView update(Postagem postagem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return mostrarPaginaDeCadastro(postagem);

        postagemService.update(postagem);
        return new ModelAndView("redirect:/v1/app/cpd/postagens/list");
    }

}//CLASS
