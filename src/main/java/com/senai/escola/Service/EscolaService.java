package com.senai.escola.Service;

import com.senai.escola.Interface.EscolaRepository;
import com.senai.escola.Models.Escola;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscolaService {
    private final EscolaRepository repository;

    public EscolaService(EscolaRepository repository) {
        this.repository = repository;
    }

    public List<Escola> buscarTodasEscolas(){
        return repository.findAll();
    }

    public Escola salvarNovaEscola(Escola escola) {
        return repository.save(escola);
    }

    public Escola buscarEscolaId (Long id){
        return repository.findById(id).orElse(null);
    }

    public void deletarEscola (Long id){ //"void" siginifica que não tem retorno
        repository.deleteById(id);
    }

}
