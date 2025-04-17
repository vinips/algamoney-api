package com.example.algamoney.api.exception;

public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public PessoaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public PessoaNaoEncontradaException (Long pessoaId) {
		this(String.format("Pessoa de código %d não existe", pessoaId));
	}

}
