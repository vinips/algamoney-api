package com.example.algamoney.api.event.listener;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.event.RecursoEvent;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class RecursoAtualizadoListener implements ApplicationListener<RecursoEvent> {

	@Override
	public void onApplicationEvent(RecursoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		
		adicionarHeaderLocation(response);
	}
	
	private void adicionarHeaderLocation(HttpServletResponse response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{codigo}")
				.buildAndExpand(1)
				.toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}

}
