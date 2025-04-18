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
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.service.LancamentoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Lancamento> listar() {
		return lancamentoService.buscarTodos();
	}
	
	@GetMapping("/{lancamentoId}")
	public ResponseEntity<Lancamento> buscar(@PathVariable Long lancamentoId) {
		Lancamento lancamento = lancamentoService.buscarOuFalhar(lancamentoId);
		
		return ResponseEntity.ok(lancamento);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> adicionar(@Valid @RequestBody Lancamento novoLancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(novoLancamento);
		
		publisher.publishEvent(new RecursoEvent(lancamentoSalvo, response, lancamentoSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@PutMapping("/{lancamentoId}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long lancamentoId, @Valid @RequestBody Lancamento lancamentoEditado) {
		Lancamento lancamento = lancamentoService.atualizar(lancamentoId, lancamentoEditado);
		
		return ResponseEntity.ok(lancamento);
	}
	
	@DeleteMapping("/{lancamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long lancamentoId) {
		lancamentoService.deletar(lancamentoId);
	}
	
	
	

}
