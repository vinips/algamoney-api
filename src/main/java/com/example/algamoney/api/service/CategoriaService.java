package com.example.algamoney.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.algamoney.api.exception.CategoriaNaoEncontradaException;
import com.example.algamoney.api.exception.EntidadeEmUsoException;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.reposiroty.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static final String MSG_CATEGORIA_EM_USO = "Categoria de código %d não pode ser removida pois está em uso";
	
	public Categoria buscarOuFalhar(Long categoriaId) {
		return categoriaRepository.findById(categoriaId).orElseThrow(
				() -> new CategoriaNaoEncontradaException(categoriaId));
	}
	
	@Transactional
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Transactional
	public Categoria atualizar(Long categoriaId, Categoria categoriaEditada) {
		Categoria categoria = this.buscarOuFalhar(categoriaId);
		
		BeanUtils.copyProperties(categoriaEditada, categoria, "id");
		return this.salvar(categoria);
	}
	
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}
	
	@Transactional
	public void deletar(Long categoriaId) {
		try {
			categoriaRepository.deleteById(categoriaId);

			categoriaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CATEGORIA_EM_USO, categoriaId));	
		}
	}
	
}
