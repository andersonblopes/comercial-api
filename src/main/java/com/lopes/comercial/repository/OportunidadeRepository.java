package com.lopes.comercial.repository;

import com.lopes.comercial.model.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Oportunidade repository.
 */
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

	/**
	 * Find by descricao and nome prospecto optional.
	 *
	 * @param descricao     the descricao
	 * @param nomeProspecto the nome prospecto
	 * @return the optional
	 */
	Optional<Oportunidade> findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);

}
