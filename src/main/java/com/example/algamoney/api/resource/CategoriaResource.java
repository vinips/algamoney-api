package com.example.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.reposiroty.CategoriaRepository;
import com.example.algamoney.api.service.CategoriaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> adicionar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva= categoriaRepository.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{categoriaId}")
	public ResponseEntity<Categoria> buscar(@PathVariable Long categoriaId) {
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) :  ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{categoriaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long categoriaId) {
		categoriaRepository.deleteById(categoriaId);
	}
	
	@PutMapping("/{categoriaId}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long categoriaId, @Valid @RequestBody Categoria categoriaEdit) {
		Categoria categoria = categoriaService.atualizar(categoriaId, categoriaEdit);
		
		return ResponseEntity.ok(categoria);
	}
	
	
}
