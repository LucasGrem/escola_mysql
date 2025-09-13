package com.senai.escola.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // Habilita uso do @PreAuthorize nos controllers
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // Rotas públicas (exemplo: login, cadastro)
//                        .requestMatchers("/auth/**").permitAll() //os "**" serve para dar todas as permissões
//
//                        // Permissões específicas
//                        .requestMatchers("/alunos/**").hasAnyRole("admin", "professor") //somente o admin e professor pode cadastrar alunos
//                        .requestMatchers("/professor/**").hasRole("admin") //somente o admin pode cadastrar professores
//
//                        // Qualquer outra rota precisa de autenticação
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
//
//    //incrementar a senha
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}

//Depois que testar e funcionar, VOLTE para a configuração segura
@Bean
public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    // Rotas públicas (exemplo: login, cadastro, arquivos estáticos)
                    .requestMatchers("/", "/index.html", "/static/**", "/auth/**", "/login.html").permitAll()

                    // Permissões específicas
                    .requestMatchers("/aluno/**").hasAnyRole("ADMIN", "PROFESSOR")
                    .requestMatchers("/professores/**").hasAnyRole("ADMIN")

                    // Qualquer outra rota precisa de autenticação
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login.html")
                    .permitAll()
            )
            .logout(logout -> logout
                    .permitAll()
            );

    return httpSecurity.build();
}




@Bean
//encriptar a senha do user afim de não deixar publica
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();//vai passar um hash no tempo de execução
}

}
