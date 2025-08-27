package com.senai.escola.Controller;

import com.senai.escola.Models.Professor;
import com.senai.escola.Service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //defini que esta class vire a controladora
@RequestMapping ("/professor") //Faz integração com a web
public class ProfessorController {
    private final ProfessorService professorService; //precisa de um construtor, para que a variavel seja inicializada.

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> buscarProfessor(){
        return professorService.buscarTodosProfessores();
    }

    @PostMapping
    public Professor salvar(@RequestBody Professor professor){
        return professorService.salvarNovoProfessor(professor);
    }

    @DeleteMapping("/{id}")
    public void excluirProfessor(@PathVariable Long id){ //"void" significa q é sem retorno
        professorService.deletarProfessor(id);
    }

    @GetMapping("/{id}") //"("/{id}")" é para buscar algo especifico
    public Professor buscarProfessorPorId(@PathVariable Long id){
        return professorService.buscarProfessorId(id);
    }


}
