package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public Page<Funcionario> listAllPageable(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Funcionario findByIdOrThrowBadRequestException(long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                      String.format("Funcionário, não encontrado pelo id '%d' informado", id))
                );
    }

    public Funcionario save(Funcionario funcionarioRequest){
        //salvar a data como data é mudar somente a apresentação dela para o usuário
        //encriptografar a senha
        Funcionario funcionario = Funcionario.builder()
                .nome(funcionarioRequest.getNome())
                .login(funcionarioRequest.getLogin())
                .cargo(funcionarioRequest.getCargo())
                .senha(funcionarioRequest.getSenha())
                .dataNascimento(funcionarioRequest.getDataNascimento())
                .permissao(funcionarioRequest.getPermissao())
                .build();

        return funcionarioRepository.save(funcionario);
    }

    public void delete(long id){
        funcionarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(Funcionario funcionarioRequest){
        Funcionario funcionarioSalvo = findByIdOrThrowBadRequestException(funcionarioRequest.getId());
        Funcionario funcionario = Funcionario.builder()
                .id(funcionarioSalvo.getId())
                .nome(funcionarioSalvo.getNome())
                .login(funcionarioSalvo.getLogin())
                .cargo(funcionarioSalvo.getCargo())
                .senha(funcionarioSalvo.getSenha())
                .dataNascimento(funcionarioSalvo.getDataNascimento())
                .permissao(funcionarioSalvo.getPermissao())
                .build();

        funcionarioRepository.save(funcionario);

    }

}//class
