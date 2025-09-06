package com.senai.escola.Controller;

import com.senai.escola.Models.Usuario;
import com.senai.escola.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController //defini que esta class vire a controladora
@RequestMapping ("/auth") //Faz integração com a web
@CrossOrigin(origins = "*") //permite chamadas do front-end
public class AuthController {
    private final UsuarioService usuarioService; //precisa de um construtor, para que a variavel seja inicializada.

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario){
        Usuario user = usuarioService.autenticar(usuario.getUsername(), usuario.getSenha());

        if (user != null){
            return "Login realizado com sucesso! Bem vindo, " + user.getUsername();
        }
        return "Login incorreto!";
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }

}
