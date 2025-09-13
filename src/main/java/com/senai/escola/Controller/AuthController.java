package com.senai.escola.Controller;

import com.senai.escola.Models.Usuario;
import com.senai.escola.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController //defini que esta class vire a controladora
@RequestMapping ("/auth") //Faz integração com a web
@CrossOrigin(origins = "*") //permite chamadas do front-end
public class AuthController {
    private final UsuarioService usuarioService; //precisa de um construtor, para que a variavel seja inicializada.
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }

//    @PostMapping("/login")
//    public String login(@RequestBody Usuario usuario){
//        Usuario user = usuarioService.fazerLogin(usuario.getUsername(), usuario.getSenha());
//
//        if (user != null){
//            return "Login realizado com sucesso! Bem vindo, " + user.getUsername();
//        }
//        return "Login incorreto!";
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuario.getUsername(),
                            usuario.getSenha()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Busca o usuário para obter a role
            Usuario user = usuarioService.findByUsername(usuario.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            return ResponseEntity.ok("Login realizado com sucesso! Bem-vindo, " + user.getUsername() +
                    " (Role: " + user.getRole() + ")");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario){
        try {
            // verifica se o usuário já existe
            if (usuarioService.findByUsername(usuario.getUsername()).isPresent()){
                return ResponseEntity.badRequest().body("Usuário já existe!");
            }
            Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok("Usuário cadastrado com sucesso. Bem vindo " + novoUsuario.getUsername());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao cadastrar usuário: " + e.getMessage());
        }
//        return usuarioService.salvarUsuario(usuario);
    }

}
