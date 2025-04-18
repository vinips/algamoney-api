package com.example.algamoney.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.algamoney.api.exception.EntidadeEmUsoException;
import com.example.algamoney.api.exception.LancamentoNaoEncontradoException;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.reposiroty.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	private static final String MSG_LANCAMENTO_EM_USO = "Lançamento de código %d não pode ser removido pois está em uso";

	
	public Lancamento buscarOuFalhar(Long lancamentoId) {
		return lancamentoRepository.findById(lancamentoId).orElseThrow(
				() -> new LancamentoNaoEncontradoException(lancamentoId));
	}
	
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		return lancamentoRepository.save(lancamento);
	}

	@Transactional
	public Lancamento atualizar(Long lancamentoId, Lancamento lancamentoEditada) {
		Lancamento lancamento = this.buscarOuFalhar(lancamentoId);
		
		BeanUtils.copyProperties(lancamentoEditada, lancamento, "id");
		
		return this.salvar(lancamento);
	}
	
	public List<Lancamento> buscarTodos() {
		return lancamentoRepository.findAll();
	}
	
	@Transactional
	public void deletar(Long lancamentoId) {
		try {
			lancamentoRepository.deleteById(lancamentoId);

			lancamentoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_LANCAMENTO_EM_USO, lancamentoId));	
		}
	}
	
	
}
