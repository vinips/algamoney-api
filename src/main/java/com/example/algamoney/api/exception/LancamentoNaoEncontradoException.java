package com.example.algamoney.api.exception;

public class LancamentoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public LancamentoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public LancamentoNaoEncontradoException (Long lancamentoId) {
		this(String.format("Lançamento de código %d não existe", lancamentoId));
	}

}
