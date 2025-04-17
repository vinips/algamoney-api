package com.example.algamoney.api.resource;

import java.util.List;

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

import com.example.algamoney.api.event.RecursoEvent;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.reposiroty.PessoaRepository;
import com.example.algamoney.api.service.PessoaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscarOuFalhar(pessoaId);
		
		return ResponseEntity.ok(pessoa);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> adicionar(@Valid @RequestBody Pessoa novaPessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaService.salvar(novaPessoa);
		
		publisher.publishEvent(new RecursoEvent(pessoaSalva, response, pessoaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@DeleteMapping("/{pessoaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
		
	}
	
	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long pessoaId, @Valid @RequestBody Pessoa pessoaEditada, HttpServletResponse response) {
		Pessoa pessoa = pessoaService.atualizar(pessoaId, pessoaEditada);
		
		//CORRIGIR ISSO
		publisher.publishEvent(new RecursoEvent(pessoa, response, pessoa.getId()));
		
		return ResponseEntity.ok(pessoa);
	}

}
