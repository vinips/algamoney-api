package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.reposiroty.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	public Categoria atualizar(Long categoriaId, Categoria categoria) {
		Optional<Categoria> categoriaSalva = categoriaRepository.findById(categoriaId);	
		
		if (categoriaSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(categoria, categoriaSalva.get(), "id");
		return categoriaRepository.save(categoriaSalva.get());
	}
	
}
