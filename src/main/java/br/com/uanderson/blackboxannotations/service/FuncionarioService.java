package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public Page<Funcionario> listAllPageable(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public List<Funcionario> listAllNoPageable() {
        return funcionarioRepository.findAll();
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
    public Funcionario save(Funcionario funcionario){
        Funcionario newFuncionario = Funcionario.builder()
                .nome(funcionario.getNome())
                .login(funcionario.getLogin())
                .cargo(funcionario.getCargo())
                .senha(funcionario.getSenha())
                .dataNascimento(funcionario.getDataNascimento())
                .permissao(funcionario.getPermissao())
                .build();

        return funcionarioRepository.save(newFuncionario);
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

    public Page<Funcionario> findAllPaginated(int pageNow, int pageSize, String sortField,
                                          String sortDirection, String keyword) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        PageRequest pageRequest = PageRequest.of((pageNow - 1), pageSize, sort);

        if (keyword != null) {
            return funcionarioRepository.findAll(keyword, pageRequest);
        }
        return funcionarioRepository.findAll(pageRequest);
    }
}//class
