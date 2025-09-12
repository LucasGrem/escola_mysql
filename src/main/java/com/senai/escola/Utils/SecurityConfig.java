package com.senai.escola.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.PasswordAuthentication;

@Configuration
@EnableMethodSecurity // Habilita uso do @PreAuthorize nos controllers
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Rotas públicas (exemplo: login, cadastro)
                        .requestMatchers("/auth/**").permitAll() //os "**" serve para dar todas as permissões

                        // Permissões específicas
                        .requestMatchers("/alunos/**").hasAnyRole("admin", "professor") //somente o admin e professor pode cadastrar alunos
                        .requestMatchers("/professor/**").hasRole("admin") //somente o admin pode cadastrar professores

                        // Qualquer outra rota precisa de autenticação
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    //incrementar a senha
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
