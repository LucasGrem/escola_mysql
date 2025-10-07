package com.senai.escola.Controller;

import com.senai.escola.Models.Escola;
import com.senai.escola.Service.EscolaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //defini que esta class vire a controladora
@RequestMapping("/escola") //Faz integração com a web
public class EscolaController {
    private final EscolaService escolaService;

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    // Apenas ADMIN pode ver lista de escolas
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Escola> buscarEscolas() {
        return escolaService.buscarTodasEscolas();
    }

    // Apenas ADMIN pode criar
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Escola salvar(@RequestBody Escola escola){
        return escolaService.salvarNovaEscola(escola);
    }

    // Apenas ADMIN pode atualizar
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")  //"("/{id}")" é para buscar algo especifico, nesse caso id
    public Escola atualizarEscola(@PathVariable Long id, @RequestBody Escola novaEscola){
        Escola verificarEscola = escolaService.buscarEscolaId(id);
        if (verificarEscola == null) return null;

        verificarEscola.setNome(novaEscola.getNome());
        verificarEscola.setEmail(novaEscola.getEmail());
        verificarEscola.setTelefone(novaEscola.getTelefone());
        verificarEscola.setCnpj(novaEscola.getCnpj());
        verificarEscola.setComponentes(novaEscola.getComponentes());
        verificarEscola.setTurmas(novaEscola.getTurmas());
        verificarEscola.setStatusAluno(novaEscola.getStatusAluno());

        return escolaService.salvarNovaEscola(verificarEscola);
    }

    // Apenas ADMIN pode deletar
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}") //"("/{id}")" é para buscar algo especifico, nesse caso id
    public void excluirEscola(@PathVariable Long id){ //"void" significa q é sem retorno
        escolaService.deletarEscola(id);
    }

    // Apenas ADMIN pode consultar
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Escola buscarEscolaPorId(@PathVariable Long id){ //"("/{id}")" é para buscar algo especifico, nesse caso id
        return escolaService.buscarEscolaId(id);
    }

}
