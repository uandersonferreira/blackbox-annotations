package br.com.uanderson.blackboxannotations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Preencha o campo login")
    private String login;
    @NotBlank(message = "Preencha o campo senha")
    private  String senha;
    @NotEmpty(message = "Este campo não pode ser vazio!")
    private String nome;
    @NotEmpty(message = "Preencha o campo matricula")
    private String matricula;
    @NotEmpty(message = "Preencha o campo cargo")
    private String cargo;
    @PastOrPresent(message = "Informe uma data valida")
    @NotNull(message = "Preencha o campo data")
    private LocalDate dataNascimento;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_roles_funcionarios",
            joinColumns = @JoinColumn(name = "funcionario_login_fk"),
            inverseJoinColumns = @JoinColumn(name = "role_fk")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "funcionario")
    private List<Postagem> postagems;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", cargo='" + cargo + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", roles=" + roles +
                ", postagems=" + postagems +
                '}';
    }
}
