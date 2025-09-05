package com.senai.escola.Controller;

import com.senai.escola.Models.Escola;
import com.senai.escola.Service.EscolaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //defini que esta class vire a controladora
@RequestMapping("/escola") //Faz integração com a web
public class EscolaController {
    private final EscolaService escolaService;

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    @GetMapping
    public List<Escola> buscarEscolas() {
        return escolaService.buscarTodasEscolas();
    }

    @GetMapping
    public Escola salvar(@RequestBody Escola escola){
        return escolaService.salvarNovaEscola(escola);
    }

    @PutMapping("/id") //"("/{id}")" é para buscar algo especifico, nesse caso id
    public Escola atualizarEscola(@PathVariable Long id, @RequestBody Escola novaEscola){
        Escola verificarEscola = escolaService.buscarEscolaId(id);
        if (verificarEscola == null) return null;

        verificarEscola.setEmail(verificarEscola.getEmail());
        verificarEscola.setNome(novaEscola.getNome());
        verificarEscola.setTelefone(verificarEscola.getTelefone());
        //verificarEscola.setCnpj(verificarEscola.getCnpj());
        verificarEscola.setComponentes(verificarEscola.getComponentes());
        verificarEscola.setTurmas(verificarEscola.getTurmas());
        verificarEscola.setStatusAluno(verificarEscola.getStatusAluno());

        return escolaService.salvarNovaEscola(verificarEscola);
    }

    @DeleteMapping("/id") //"("/{id}")" é para buscar algo especifico, nesse caso id
    public void excluirEscola(@PathVariable Long id){ //"void" significa q é sem retorno
        escolaService.deletarEscola(id);
    }

    @GetMapping("/id")
    public Escola buscarEscolaPorId(@PathVariable Long id){ //"("/{id}")" é para buscar algo especifico, nesse caso id
        return escolaService.buscarEscolaId(id);
    }

}
