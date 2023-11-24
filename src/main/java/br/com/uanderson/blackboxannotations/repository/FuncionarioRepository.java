package br.com.uanderson.blackboxannotations.repository;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("""
            SELECT f FROM Funcionario f
                INNER JOIN f.postagems p
                WHERE f.nome LIKE %?1%
                    OR f.cargo LIKE %?1%
                    OR f.cargo LIKE %?1%
                    OR p.conteudo LIKE CONCAT('%<p>', ?1, '</p>')
            """)
        Page<Funcionario> findAll(String keyword, Pageable pageable);
        Funcionario findFuncionarioByLogin(String login);
}
