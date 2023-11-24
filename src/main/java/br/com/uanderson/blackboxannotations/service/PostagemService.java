package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.model.Postagem;
import br.com.uanderson.blackboxannotations.repository.FuncionarioRepository;
import br.com.uanderson.blackboxannotations.repository.PostagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostagemService {
    private final PostagemRepository postagemRepository;

    public Page<Postagem> listAllPageable(Pageable pageable) {
        return postagemRepository.findAll(pageable);
    }

    public List<Postagem> listAllNoPageable() {
        return postagemRepository.findAll();
    }

    public Postagem findByIdOrThrowBadRequestException(long id) {
        return postagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                      String.format("Postagem, não encontrado pelo id '%d' informado", id))
                );
    }
    //salvar a data como data é mudar somente a apresentação dela para o usuário
    //encriptografar a senha
    public Postagem save(Postagem postagem){
        Postagem newPostagemm = Postagem.builder()
                .titulo(postagem.getTitulo())
                .conteudo(postagem.getConteudo())
                .imagem(postagem.getImagem())
                .dataCriacao(LocalDateTime.now())
                .funcionario(postagem.getFuncionario())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        return postagemRepository.save(newPostagemm);
    }

    public void delete(long id){
        postagemRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(Postagem postagemRequest){
        Postagem postagemSalva = findByIdOrThrowBadRequestException(postagemRequest.getId());

        Postagem newPostagemm = Postagem.builder()
                .id(postagemSalva.getId())
                .titulo(postagemRequest.getTitulo())
                .conteudo(postagemRequest.getConteudo())
                .imagem(postagemRequest.getImagem())
                .dataCriacao(postagemSalva.getDataCriacao())
                .funcionario(postagemSalva.getFuncionario())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        postagemRepository.save(newPostagemm);

    }

    public Page<Postagem> findAllPaginated(int pageNow, int pageSize, String sortField,
                                          String sortDirection, String keyword) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        PageRequest pageRequest = PageRequest.of((pageNow - 1), pageSize, sort);

        if (keyword != null) {
            return postagemRepository.findAll(keyword, pageRequest);
        }
        return postagemRepository.findAll(pageRequest);
    }
}//class
