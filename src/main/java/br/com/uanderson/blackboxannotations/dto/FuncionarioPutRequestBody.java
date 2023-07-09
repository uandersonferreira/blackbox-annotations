package br.com.uanderson.blackboxannotations.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FuncionarioPutRequestBody(Long id, String nome, String login, String senha, String cargo,
                                        LocalDate dataNascimento, String permissao //ROLES_ADMIN, ROLES_USER
) {
}

/*
        Long id,
        @NotEmpty(message = "Este campo não pode ser vazio!")
        @Column(unique = true)
        String nome,
        @NotEmpty(message = "Este campo não pode ser vazio!")
        String login,//login
        @NotEmpty(message = "Este campo não pode ser vazio!")
        String senha,
        @NotEmpty(message = "Este campo não pode ser vazio!")
        String cargo,
        @PastOrPresent(message = "Você nasceu no futuro? ")
        LocalDateTime dataNascimento,
        String permissao //ROLES_ADMIN, ROLES_USER
 */