package br.com.uanderson.blackboxannotations.service;

import br.com.uanderson.blackboxannotations.repository.UserDetailsApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsApplicationService implements UserDetailsService {
    private final UserDetailsApplicationRepository userDetailsApplicationRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(
                userDetailsApplicationRepository.findByLogin(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Usuário '%s' não encontrado!", username)));
    }
}
