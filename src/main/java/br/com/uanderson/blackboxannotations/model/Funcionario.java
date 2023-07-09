package br.com.uanderson.blackboxannotations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private LocalDateTime dataNascimento;
    private String permissao;//ROLES_ADMIN, ROLES_USER


}
