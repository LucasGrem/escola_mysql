package com.senai.escola.Controller;

import java.util.List;

import com.senai.escola.Models.Aluno;
import org.springframework.web.bind.annotation.*;

import com.senai.escola.Models.Aluno;
import com.senai.escola.Service.AlunoService;

@RestController //defini que esta class vire a controladora
@RequestMapping ("/alunos") //Faz integração com a web
public class AlunoController {
    private final AlunoService alunoService; //precisa de um construtor, para que a variavel seja inicializada.

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> buscarAlunos(){
        return alunoService.buscarTodosAlunos();
    }

    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno){
        return alunoService.salvarNovoAluno(aluno);
    }

    @PutMapping("/{id}")
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody Aluno novoAluno){ //edita o dados do aluno
        Aluno verificarAluno = alunoService.buscarAlunoId(id);
        if (verificarAluno == null) return null;
        verificarAluno.setNome(novoAluno.getNome());
        verificarAluno.setEmail(novoAluno.getEmail());
        verificarAluno.setTelefone(novoAluno.getTelefone());

        return alunoService.salvarNovoAluno(verificarAluno);
    }

    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable Long id){ //"void" significa q é sem retorno
        alunoService.deletarAluno(id);
    }

    @GetMapping("/{id}") //"("/{id}")" é para buscar algo especifico
    public Aluno buscarAlunoPorId(@PathVariable Long id){
        return alunoService.buscarAlunoId(id);
    }


}
