package com.lopes.comercial;

import com.lopes.comercial.model.Oportunidade;
import com.lopes.comercial.repository.OportunidadeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Comercial api application tests.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ComercialApiApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OportunidadeRepository oportunidadeRepository;

	/**
	 * Deve encontrar oportunidade.
	 */
	@Test
	public void deveEncontrarOportunidade() {
		// given
		Oportunidade oportunidade = new Oportunidade("Teste", "Teste prospecto", new BigDecimal(35000L));
		entityManager.persist(oportunidade);
		entityManager.flush();

		// when
		Optional<Oportunidade> oportunidadeEncontrada = oportunidadeRepository
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());

		// then
		String descricao = oportunidadeEncontrada.get().getDescricao();
		assertThat(descricao).isEqualTo(oportunidade.getDescricao());
	}

}
