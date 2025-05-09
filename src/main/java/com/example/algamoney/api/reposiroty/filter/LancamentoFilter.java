package com.example.algamoney.api.reposiroty.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {
	
	private String descricao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimentoInicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimentoFim;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getDataVencimentoInicio() {
		return dataVencimentoInicio;
	}
	
	public void setDataVencimentoInicio(LocalDate dataVencimentoInicio) {
		this.dataVencimentoInicio = dataVencimentoInicio;
	}
	
	public LocalDate getDataVencimentoFim() {
		return dataVencimentoFim;
	}
	
	public void setDataVencimentoFim(LocalDate dataVencimentoFim) {
		this.dataVencimentoFim = dataVencimentoFim;
	}
	
	
	
	

	
}
