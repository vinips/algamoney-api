package com.example.algamoney.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.algamoney.api.exception.EntidadeEmUsoException;
import com.example.algamoney.api.exception.LancamentoNaoEncontradoException;
import com.example.algamoney.api.exception.NegocioException;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.reposiroty.LancamentoRepository;
import com.example.algamoney.api.reposiroty.PessoaRepository;
import com.example.algamoney.api.reposiroty.filter.LancamentoFilter;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaReposiroty;
	
	private static final String MSG_LANCAMENTO_EM_USO = "Lançamento de código %d não pode ser removido pois está em uso";
	
	private static final String MSG_PESSOA_INATIVA = "Pessoa de código '%d' não pode ser incluída no lançamento pois está inativa";
	
	public Lancamento buscarOuFalhar(Long lancamentoId) {
		return lancamentoRepository.findById(lancamentoId).orElseThrow(
				() -> new LancamentoNaoEncontradoException(lancamentoId));
	}
	
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaReposiroty.findById(lancamento.getPessoa().getId());
		
		if (pessoa.isPresent() && pessoa.get().isInativo()) {
			throw new NegocioException(String.format(MSG_PESSOA_INATIVA, lancamento.getPessoa().getId()));
		}
		
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
	
	public Page<Lancamento> buscarTodosComFiltro(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
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
