package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.algamoney.api.exception.PessoaNaoEncontradaException;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.reposiroty.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public Pessoa atualizar(Long pessoaId, Pessoa pessoaEditada) {
		Pessoa pessoa = this.buscarOuFalhar(pessoaId);
		
		BeanUtils.copyProperties(pessoaEditada, pessoa, "id");
		return this.salvar(pessoa);
	}
	
	
	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(
				() -> new PessoaNaoEncontradaException(pessoaId));
	}
	
	
}
