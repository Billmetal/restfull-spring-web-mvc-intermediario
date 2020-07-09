package br.com.digitalinnovationone.example.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalinnovationone.example.restful.entity.SoldadoEntity;

@Repository
public interface SoldadoRepository extends JpaRepository<SoldadoEntity, Long>{

}
