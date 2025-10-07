//package com.senai.escola.Utils;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//@EnableMethodSecurity // Habilita uso do @PreAuthorize nos controllers
//public class SecurityConfig {
//
//    @Bean
//    // Cria o AuthenticationManager - o "chefe" que gerencia toda a autenticação
//    // Responsável por verificar usuários, senhas e gerenciar login
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // Rotas públicas (exemplo: login, cadastro, arquivos estáticos)
//                        .requestMatchers(
//                                "/",
//                                "/index.html",
//                                "/login.html",
//                                "/static/**",
//                                "/auth/**",
//                                "/template/**",  // ← ADICIONE ESTA LINHA!
//                                "/css/**",
//                                "/js/**",
//                                "/images/**",
//                                "/api/**"
//                        ).permitAll()
//
//                        // Permissões específicas
////                        .requestMatchers("/aluno/**").hasAnyRole("ADMIN", "PROFESSOR")
////                        .requestMatchers("/professores/**").hasAnyRole("ADMIN")
//
//                        // Qualquer outra rota precisa de autenticação
//                        .anyRequest().authenticated()
//                );
////                .formLogin(form -> form
////                        .loginPage("/login.html")
////                        .permitAll()
////                )
////                .logout(logout -> logout
////                        .permitAll()
////                );
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(List.of("*"));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    //encriptar a senha do user afim de não deixar publica
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();//vai passar um hash no tempo de execução
//    }
//}