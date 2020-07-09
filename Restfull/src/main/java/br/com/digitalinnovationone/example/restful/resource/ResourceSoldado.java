package br.com.digitalinnovationone.example.restful.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitalinnovationone.example.restful.controller.SoldadoController;
import br.com.digitalinnovationone.example.restful.controller.request.SoldadoEditRequest;
import br.com.digitalinnovationone.example.restful.controller.response.SoldadoListResponse;
import br.com.digitalinnovationone.example.restful.controller.response.SoldadoResponse;
import br.com.digitalinnovationone.example.restful.entity.SoldadoEntity;


@Component
public class ResourceSoldado {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public SoldadoListResponse criarLink(SoldadoEntity soldadoEntity) {
		SoldadoListResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoListResponse.class);
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SoldadoController.class).buscarSoldado(soldadoEntity.getId())).withSelfRel();
		soldadoListResponse.add(link);
		return soldadoListResponse;
	}
	
	public SoldadoResponse criarLinkDetalhe(SoldadoEntity soldadoEntity) {
		SoldadoResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoResponse.class);
		if(soldadoEntity.getStatus().contains("morto")) {
			Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SoldadoController.class).deletarSoldado(soldadoEntity.getId()))
					.withRel("remover")
					.withTitle("Deletar soldado")
					.withType("delete");
			soldadoListResponse.add(link);
		} else if(soldadoEntity.getStatus().contains("vivo")) {
			Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SoldadoController.class).frenteCastelo(soldadoEntity.getId()))
					.withRel("batalhar")
					.withTitle("Ir para frente do castelo")
					.withType("put");
			soldadoListResponse.add(link);
		}
		return soldadoListResponse;
	}
	
	public SoldadoResponse getSoldado(SoldadoEntity soldadoEntity) {
		return objectMapper.convertValue(soldadoEntity, SoldadoResponse.class);
	}
	
	public SoldadoEntity alterarSoldado(SoldadoEditRequest soldadoEditRequest) {
		return objectMapper.convertValue(soldadoEditRequest, SoldadoEntity.class);
	}

}
