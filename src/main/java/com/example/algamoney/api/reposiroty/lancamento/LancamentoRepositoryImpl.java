package com.example.algamoney.api.reposiroty.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.reposiroty.filter.LancamentoFilter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//SEM PAGINAÇÃO
//	@Override
//	public List<Lancamento> filtrar(LancamentoFilter filter) {
//		
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
//		Root<Lancamento> root = criteria.from(Lancamento.class);
//		
//		//criar as restrições
//		Predicate[] predicates = criarRestricoes(filter, builder, root);
//		criteria.where(predicates);
//		
//		TypedQuery<Lancamento> query = entityManager.createQuery(criteria);
//		
//		return query.getResultList();
//	}
	
	@Override
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//criar as restrições
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = entityManager.createQuery(criteria);
		adicionarRestricoesPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, totalLancamentos(filter)) ;
	}

	private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (ObjectUtils.isNotEmpty(filter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get("descricao")), "%" + filter.getDescricao().toLowerCase() + "%"));
		}
		
		if (ObjectUtils.isNotEmpty(filter.getDataVencimentoInicio())) {
			predicates.add(builder.greaterThanOrEqualTo(
					root.get("dataVencimento"), filter.getDataVencimentoInicio()));
		}
		
		if (ObjectUtils.isNotEmpty(filter.getDataVencimentoFim())) {
			predicates.add(builder.lessThanOrEqualTo(
					root.get("dataVencimento"), filter.getDataVencimentoFim()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesPaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private long totalLancamentos(LancamentoFilter filter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return entityManager.createQuery(criteria).getSingleResult();
	}


}
