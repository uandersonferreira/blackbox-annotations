package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class FuncionarioDetailsService implements UserDetailsService {
    private final FuncionarioRepository funcionarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(
                        funcionarioRepository.findFuncionarioByLogin(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Funcionário '%s' não encontrado!", username)));
    }
}
