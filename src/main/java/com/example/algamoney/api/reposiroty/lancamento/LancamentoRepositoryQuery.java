package com.example.algamoney.api.reposiroty.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.reposiroty.filter.LancamentoFilter;

//Tem que ter o começo do ClasseRepository para o Spring encontrar automaticamente ela.
public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);

}
