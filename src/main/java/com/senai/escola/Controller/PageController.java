package com.senai.escola.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    @GetMapping("/login.html")
    public String login() {
        return "forward:/static/login.html";
    }

    @GetMapping("/escola.html")
    public String escola() {
        return "forward:/template/escola/escola.html";
    }

    @GetMapping("/professor.html")
    public String professor() {
        return "forward:/template/professores/professor.html";
    }

    @GetMapping("/aluno.html")
    public String aluno() {
        return "forward:/template/alunos/aluno.html";
    }
}

