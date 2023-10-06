package br.com.uanderson.blackboxannotations.repository;

import br.com.uanderson.blackboxannotations.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
