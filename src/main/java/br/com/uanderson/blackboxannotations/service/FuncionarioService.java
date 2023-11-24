package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.model.Role;
import br.com.uanderson.blackboxannotations.repository.FuncionarioRepository;
import br.com.uanderson.blackboxannotations.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final RoleRepository roleRepository;

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
    public Funcionario save(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Objeto Funcionário vazio.");
        }

        funcionario.setRoles(getAuthorities(funcionario.getCargo()));
        funcionario.setSenha(passwordEncoder().encode(funcionario.getPassword()));
        return funcionarioRepository.save(funcionario);
    }

    public void delete(long id) {
        funcionarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(Funcionario funcionarioRequest) {
        Funcionario funcionarioSalvo = findByIdOrThrowBadRequestException(funcionarioRequest.getId());

        Funcionario funcionario = Funcionario.builder()
                .id(funcionarioSalvo.getId())
                .nome(funcionarioRequest.getNome())
                .cargo(funcionarioRequest.getCargo())
                .dataNascimento(funcionarioRequest.getDataNascimento())
                .matricula(funcionarioRequest.getMatricula())
                .login(funcionarioSalvo.getLogin())
                .senha(passwordEncoder().encode(funcionarioSalvo.getPassword()))
                .build();

        funcionarioRepository.save(funcionario);

    }

    public Page<Funcionario> findAllPaginated(int pageNow, int pageSize, String sortField,
                                              String sortDirection, String keyword) {

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        PageRequest pageRequest = PageRequest.of((pageNow - 1), pageSize, sort);

        if (keyword != null) {
            return funcionarioRepository.findAll(keyword, pageRequest);
        }
        return funcionarioRepository.findAll(pageRequest);
    }


    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public List<Role> getAuthorities(String cargos) {
        return Arrays.stream(cargos.split(","))//separação por vírgula
                .map(Role::new)//tranforma o array de Strings em um SimpleGrantedAuthority
                .collect(Collectors.toList());
        /*
                    <option value="CPD">
                    <option value="REPOSITOR">
                    <option value="LÍDER">
                    <option value="GERENTE">
                    <option value="OPERADOR">
         */
    }


}//class
