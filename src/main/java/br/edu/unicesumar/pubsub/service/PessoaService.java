package br.edu.unicesumar.pubsub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.pubsub.domain.Pessoa;
import br.edu.unicesumar.pubsub.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Page<Pessoa> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public Pessoa update(Pessoa pessoa, Long id) {
        Optional<Pessoa> pessoaDoBancoDeDadosOpt = pessoaRepository.findById(id);

        if (pessoaDoBancoDeDadosOpt.isPresent()) {
            Pessoa pessoaDoBancoDeDados = pessoaDoBancoDeDadosOpt.get();
            pessoaDoBancoDeDados.setCpf(pessoa.getCpf());
            pessoaDoBancoDeDados.setIdade(pessoa.getIdade());
            pessoaDoBancoDeDados.setNome(pessoa.getNome());
            return this.save(pessoaDoBancoDeDados);
        }

        return null;

    }

    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Page<Pessoa> findByNome(String nome, Pageable pageable) {
        return pessoaRepository.findByNome(nome, pageable);
    }

}
