//package com.senai.escola.Service;
//
//import com.senai.escola.Interface.UsuarioRepository;
//import com.senai.escola.Models.Usuario;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Optional;
//
//@Service
//public class UsuarioService implements UserDetailsService {
//    private final UsuarioRepository repository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Usuario usuario = repository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
//
//        // Use ROLE_ (com underline) e formate - ALTERAÇÃO
//        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRole());
//
//        // CORREÇÃO: campo "senha" (minúsculo)
//        return new User(
//                usuario.getUsername(),
//                usuario.getSenha(),
//                Collections.singletonList(authority));
//    }
//
//    public Optional<Usuario> findByUsername(String username){
//        return repository.findByUsername(username);
//    }
//
//    public Usuario fazerLogin(String username, String senha){
//        return repository.findByUsernameAndSenha(username, senha).orElse(null);
//    }
//
//    public Usuario salvarUsuario(Usuario usuario){
//        // codifica a senha antes de salvar
//        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
//
//        return repository.save(usuario);
//    }
//}
