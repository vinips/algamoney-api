package com.example.algamoney.api.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public CategoriaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CategoriaNaoEncontradaException (Long categoriaId) {
		this(String.format("Categoria de código %d não existe", categoriaId));
	}

}
