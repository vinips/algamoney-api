package com.example.algamoney.api.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
	
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}

}
