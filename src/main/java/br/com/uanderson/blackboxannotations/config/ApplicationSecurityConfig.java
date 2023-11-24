package br.com.uanderson.blackboxannotations.config;

import br.com.uanderson.blackboxannotations.service.FuncionarioDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@Log4j2
@EnableMethodSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig {
    private final FuncionarioDetailsService funcionarioDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);//desabilitando o CSRF
        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/css/**", "/js/**","/imagens/**", "/webjars/**").permitAll()
                    .requestMatchers("/login/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/funcionario/login-registrar/save").permitAll()
                    .requestMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll()
                        .loginProcessingUrl("/process-login")
                        .defaultSuccessUrl("/layout")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                        .logoutSuccessUrl("/?logout=true").permitAll()
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        log.info("Password encoder[123] {}", passwordEncoder().encode("master@2023"));
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorne um PasswordEncoder adequado, como BCryptPasswordEncoder,
        // para codificar as senhas dos usuários
        return new BCryptPasswordEncoder();
    }
}
