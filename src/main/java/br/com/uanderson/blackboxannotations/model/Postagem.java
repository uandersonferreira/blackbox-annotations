package br.com.uanderson.blackboxannotations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="VARCHAR(128)")
    private String titulo;
    @Lob
    @Column(columnDefinition = "BLOB")
    private String conteudo;
    private String imagem;
    private LocalDateTime dataCriacao;//= LocalDateTime.now()
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    @JoinColumn
    private Funcionario funcionario;



}//class
