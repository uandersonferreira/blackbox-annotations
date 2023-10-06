package br.com.uanderson.blackboxannotations.repository;

import br.com.uanderson.blackboxannotations.model.UserDetailsApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsApplicationRepository  extends JpaRepository<UserDetailsApplication, Long> {
    UserDetailsApplication findByLogin(String login);
}
