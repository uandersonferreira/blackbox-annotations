package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.dto.FuncionarioPostRequestBody;
import br.com.uanderson.blackboxannotations.dto.FuncionarioPutRequestBody;
import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    //salvar a data como data é mudar somente a apresentação dela para o usuário
    //encriptografar a senha
    public Funcionario save(FuncionarioPostRequestBody funcionarioPostRequestBody){
        var newFuncionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioPostRequestBody, newFuncionario);

        Funcionario funcionario = Funcionario.builder()
                .nome(newFuncionario.getNome())
                .login(newFuncionario.getLogin())
                .cargo(newFuncionario.getCargo())
                .senha(newFuncionario.getSenha())
                .dataNascimento(newFuncionario.getDataNascimento())
                .permissao(newFuncionario.getPermissao())
                .build();

        return funcionarioRepository.save(funcionario);
    }

    public void delete(long id){
        funcionarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(FuncionarioPutRequestBody funcionarioPutRequestBody){
        var newFuncionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioPutRequestBody, newFuncionario);

        Funcionario funcionarioSalvo = findByIdOrThrowBadRequestException(newFuncionario.getId());
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
