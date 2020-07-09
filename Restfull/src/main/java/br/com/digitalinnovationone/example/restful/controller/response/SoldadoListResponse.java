package br.com.digitalinnovationone.example.restful.controller.response;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SoldadoListResponse extends RepresentationModel<SoldadoListResponse>{
	
	private Long id;
	private String nome;
	private String raca;
	
	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	
	@JsonProperty("id")
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
}
