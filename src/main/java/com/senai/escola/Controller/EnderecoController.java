package com.senai.escola.Controller;

import com.senai.escola.Models.Endereco;
import com.senai.escola.Service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //defini que esta class vire a controladora
@RequestMapping ("/endereco") //Faz integração com a web
public class EnderecoController {
    private final EnderecoService enderecoService; //precisa de um construtor, para que a variavel seja inicializada.

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> buscarEndereco(){
        return enderecoService.buscarTodosEndereco();
    }

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco){
        return enderecoService.salvarNovoEndereco(endereco);
    }

    @PutMapping("/{id}")
    public Endereco atualizarEndereco(@PathVariable Long id, @RequestBody Endereco novoEndereco){ //edita o dados do aluno
        Endereco verificarEndereco = enderecoService.buscarEnderecoId(id);
        if (verificarEndereco == null) return null;
        verificarEndereco.setCep(verificarEndereco.getCep());
        verificarEndereco.setLogadouro(verificarEndereco.getLogadouro());
        verificarEndereco.setComplemento(verificarEndereco.getComplemento());
        verificarEndereco.setUnidade(verificarEndereco.getUnidade());
        verificarEndereco.setLocalidade(verificarEndereco.getLocalidade());
        verificarEndereco.setUf(verificarEndereco.getUf());
        verificarEndereco.setEstado(verificarEndereco.getEstado());
        verificarEndereco.setRegiao(verificarEndereco.getRegiao());
        verificarEndereco.setGia(verificarEndereco.getGia());
        verificarEndereco.setDdd(verificarEndereco.getDdd());
        verificarEndereco.setSiafi(verificarEndereco.getSiafi());

        return enderecoService.salvarNovoEndereco(verificarEndereco);
    }

    @DeleteMapping("/{id}")
    public void excluirEndereco(@PathVariable Long id){ //"void" significa q é sem retorno
        enderecoService.deletarEndereco(id);
    }

    @GetMapping("/{id}") //"("/{id}")" é para buscar algo especifico
    public Endereco buscarEnderecoPorId(@PathVariable Long id){
        return enderecoService.buscarEnderecoId(id);
    }


}
