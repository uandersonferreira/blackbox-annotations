package br.com.uanderson.blackboxannotations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authorities implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "preencha o campo roles")
    private String role;
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private List<UserDetailsApplication> userDetailsApplications;

    @Override
    public String getAuthority() {
        return this.role;
    }


}//class
