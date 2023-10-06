package br.com.uanderson.blackboxannotations.config;

import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfig {
    private final FuncionarioService funcionarioService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/webjars/**").permitAll()
                    .requestMatchers("/login/**").permitAll()
                    .requestMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                        .logoutSuccessUrl("/login")
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Autowired
    public void configureUserDetails(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(funcionarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
