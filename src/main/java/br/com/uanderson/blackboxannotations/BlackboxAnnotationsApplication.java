package br.com.uanderson.blackboxannotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlackboxAnnotationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlackboxAnnotationsApplication.class, args);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("SENHA "+ passwordEncoder.encode("123"));
    }

}
