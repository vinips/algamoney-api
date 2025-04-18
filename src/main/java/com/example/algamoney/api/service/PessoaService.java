package com.example.algamoney.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.algamoney.api.exception.EntidadeEmUsoException;
import com.example.algamoney.api.exception.PessoaNaoEncontradaException;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.reposiroty.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public static final String MSG_PESSOA_EM_USO = "Pessoa de código %d não pode ser removida pois está em uso";

	
	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(
				() -> new PessoaNaoEncontradaException(pessoaId));
	}
	
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
	
	public List<Pessoa> buscarTodos() {
		return pessoaRepository.findAll();
	}
	
	@Transactional
	public void deletar(Long pessoaId) {
		try {
			pessoaRepository.deleteById(pessoaId);

			pessoaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PESSOA_EM_USO, pessoaId));	
		}
	}
	
	
}
