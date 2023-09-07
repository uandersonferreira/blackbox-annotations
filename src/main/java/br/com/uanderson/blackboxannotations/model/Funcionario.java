package br.com.uanderson.blackboxannotations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Este campo não pode ser vazio!")
    @Column(unique = true)
    private String nome;
    private String login;//login
    @NotEmpty(message = "Este campo não pode ser vazio!")
    private String senha;
    @NotEmpty(message = "Este campo não pode ser vazio!")
    private String cargo;
    @PastOrPresent(message = "Você nasceu no futuro? ")
    private LocalDate dataNascimento;
    private String permissao;//ROLES_ADMIN, ROLES_USER


}
/*
INSERT INTO funcionario (cargo, data_nascimento, login, nome, permissao, senha)
VALUES
    ('Analista de Recursos Humanos', '1992-04-12', 'lucas654', 'Lucas Mendes', 'ROLE_USER', '123'),
    ('Supervisor de Vendas', '1987-11-08', 'julia987', 'Júlia Oliveira', 'ROLE_ADMIN', '123'),
    ('Coordenador de Marketing', '1991-03-18', 'marcos321', 'Marcos Costa', 'ROLE_USER', '123'),
    ('Analista de Sistemas', '1994-08-27', 'lara654', 'Lara Cardoso', 'ROLE_USER,ROLE_ADMIN', '123'),
    ('Assistente Financeiro', '1996-02-05', 'gabriel789', 'Gabriel Almeida', 'ROLE_USER', '123'),
    ('Gerente de Vendas', '1985-02-15', 'joao123', 'João Silva', 'ROLE_ADMIN', '123'),
    ('Analista de Marketing', '1990-06-25', 'maria456', 'Maria Santos', 'ROLE_USER', '123'),
    ('Desenvolvedor Web', '1993-09-10', 'pedro789', 'Pedro Oliveira', 'ROLE_USER,ROLE_ADMIN', '123'),
    ('Assistente Administrativo', '1995-12-03', 'ana321', 'Ana Rodrigues', 'ROLE_USER', '123'),
    ('Gerente de Projetos', '1988-07-20', 'carlos987', 'Carlos Pereira', 'ROLE_ADMIN', '123');

 */