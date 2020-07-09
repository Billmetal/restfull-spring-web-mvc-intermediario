package br.com.digitalinnovationone.example.restful.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import br.com.digitalinnovationone.example.restful.controller.request.SoldadoEditRequest;
import br.com.digitalinnovationone.example.restful.controller.response.SoldadoListResponse;
import br.com.digitalinnovationone.example.restful.controller.response.SoldadoResponse;
import br.com.digitalinnovationone.example.restful.entity.SoldadoEntity;
import br.com.digitalinnovationone.example.restful.repository.SoldadoRepository;
import br.com.digitalinnovationone.example.restful.resource.ResourceSoldado;


@Service
public class SoldadoService {
	
	@Autowired
	private SoldadoRepository soldadoRepository;
	
	@Autowired
	private ResourceSoldado resourceSoldado;
	
	public SoldadoResponse buscarSoldado(Long id) {		
		return resourceSoldado.criarLinkDetalhe(soldadoRepository.findById(id).orElseThrow());
	}
	
	public void criarSoldado(SoldadoEntity soldadoEntity) {
		soldadoRepository.save(soldadoEntity);
	}

	public void alterarSoldado(Long id, SoldadoEditRequest soldadoEditRequest) {
		SoldadoEntity soldadoEntity = resourceSoldado.alterarSoldado(soldadoEditRequest);
		soldadoEntity.setId(id);
		soldadoRepository.save(soldadoEntity);
	}

	public void deletarSoldado(Long id) {
		soldadoRepository.delete(soldadoRepository.findById(id).orElseThrow());
	}
	
	public CollectionModel<SoldadoListResponse> buscarSoldados(){
		List<SoldadoEntity> all = soldadoRepository.findAll();		
		return CollectionModel.of(all.stream().map(it -> resourceSoldado.criarLink(it))
							.collect(Collectors.toList()));
	}

}
