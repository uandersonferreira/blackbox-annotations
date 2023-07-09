package br.com.uanderson.blackboxannotations.repository;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
