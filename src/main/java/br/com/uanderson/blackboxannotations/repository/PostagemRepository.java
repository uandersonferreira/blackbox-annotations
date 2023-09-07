package br.com.uanderson.blackboxannotations.repository;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.model.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    @Query("""
            SELECT p FROM Postagem p
                WHERE p.titulo LIKE %?1%
                    OR CONCAT(p.dataCriacao, '') LIKE %?1%
            """)
    Page<Postagem> findAll(String keyword, Pageable pageable);
}
