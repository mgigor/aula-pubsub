package br.edu.unicesumar.pubsub.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.pubsub.domain.Pessoa;
import br.edu.unicesumar.pubsub.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping("/search")
    public Page<Pessoa> findByNome(@PathParam(value = "nome") String nome, Pageable pageable) {
        return service.findByNome(nome, pageable);
    }

    @GetMapping("/{id}")
    public Pessoa buscarPessoaPorId(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<Pessoa> buscarTodasAsPessoas(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public Pessoa criarNovaPessoa(@RequestBody Pessoa pessoa) {
        return this.service.save(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa alterarDadosDaPessoa(@PathVariable(name = "id") Long id, @RequestBody Pessoa pessoa) {
        return this.service.update(pessoa, id);
    }

    @DeleteMapping("/{id}")
    public void deletarUmaPessoaExistente(@PathVariable(name = "id") Long id) {
        this.service.deleteById(id);
    }

}
