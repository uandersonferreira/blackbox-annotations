package br.com.uanderson.blackboxannotations.dto;


import java.time.LocalDate;

public record FuncionarioPostRequestBody(String nome, String login, String senha, String cargo,
                                         LocalDate dataNascimento
) {
}
