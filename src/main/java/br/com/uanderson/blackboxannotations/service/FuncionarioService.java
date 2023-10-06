package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements UserDetailsService {
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
        if (funcionario == null){
            throw new IllegalArgumentException("Objeto Funcionário vazio.");
        }

        funcionario.setSenha(passwordEncoder().encode(funcionario.getPassword()));
        return funcionarioRepository.save(funcionario);
    }

    public void delete(long id){
        funcionarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(Funcionario funcionarioRequest){
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

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        PageRequest pageRequest = PageRequest.of((pageNow - 1), pageSize, sort);

        if (keyword != null) {
            return funcionarioRepository.findAll(keyword, pageRequest);
        }
        return funcionarioRepository.findAll(pageRequest);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(
                        funcionarioRepository.findByLogin(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Funcionário '%s' não encontrado!", username)));
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}//class
