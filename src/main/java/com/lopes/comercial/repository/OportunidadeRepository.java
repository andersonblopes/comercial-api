package com.lopes.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lopes.comercial.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long>{
	
	//TODO Definir métodos de consultas personalizadas.

}
