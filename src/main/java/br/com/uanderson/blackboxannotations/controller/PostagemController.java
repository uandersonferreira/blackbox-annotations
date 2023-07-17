package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Postagem;
import br.com.uanderson.blackboxannotations.repository.PostagemRepository;
import br.com.uanderson.blackboxannotations.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequestMapping(path = "/v1/app/cpd/post")
@RestController
@RequiredArgsConstructor
public class PostagemController {
    private final PostagemRepository postagemRepository;

    @GetMapping(path = "/form")
    public ModelAndView mostrarPaginaDeCadastro(){
        ModelAndView mv =  new ModelAndView("anotacoes/create_post");
        mv.addObject("postagem", new Postagem());
        return mv;
    }

    @PostMapping(path = "/cadastro",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView cadastroDeClient(@ModelAttribute Postagem postagem, @RequestParam("file") MultipartFile imagem){
        ModelAndView mv =  new ModelAndView("anotacoes/create_post");

        mv.addObject("postagem", postagem);

        try {
            if(UploadUtil.fazerUploadImagem(imagem)){
                postagem.setImagem(imagem.getOriginalFilename());
            }
            postagemRepository.save(postagem);
            System.out.println("Salvo com sucesso: " + postagem.getTitulo() + " " + postagem.getImagem());

            return mostrarPaginaDeCadastro();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }
    }



}//CLASS
