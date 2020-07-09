package br.com.digitalinnovationone.example.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalinnovationone.example.restful.controller.request.SoldadoEditRequest;
import br.com.digitalinnovationone.example.restful.controller.response.SoldadoListResponse;
import br.com.digitalinnovationone.example.restful.controller.response.SoldadoResponse;
import br.com.digitalinnovationone.example.restful.entity.SoldadoEntity;
import br.com.digitalinnovationone.example.restful.service.SoldadoService;

@RestController
@RequestMapping("/v1/soldado")
public class SoldadoController {
	
	@Autowired
	private SoldadoService soldadoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<SoldadoResponse> buscarSoldado(@PathVariable Long id) {
		return ResponseEntity.ok(soldadoService.buscarSoldado(id));
	}
	
	@PostMapping
	public ResponseEntity<SoldadoEntity> criarSoldado(@RequestBody SoldadoEntity soldadoEntity){
		soldadoService.criarSoldado(soldadoEntity);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SoldadoEntity> editarSoldado(@PathVariable Long id,@RequestBody SoldadoEditRequest soldadoEditRequest){
		soldadoService.alterarSoldado(id,soldadoEditRequest);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SoldadoEntity> deletarSoldado(@PathVariable Long id){
		soldadoService.deletarSoldado(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/frente-castelo/{id}")
	public ResponseEntity<SoldadoEntity> frenteCastelo(@PathVariable Long id){
		// FAZER ALGO
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<SoldadoListResponse>> buscarSoldados() {
		return ResponseEntity.ok(soldadoService.buscarSoldados());
	}

}
