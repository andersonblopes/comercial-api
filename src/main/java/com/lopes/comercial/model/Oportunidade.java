package com.lopes.comercial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * The type Oportunidade.
 */
@Entity
public class Oportunidade {

	/**
	 * The Id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The Nome prospecto.
	 */
	@NotEmpty
	@Size(max = 80)
	@javax.persistence.Column(name = "nome_prospecto")
	private String nomeProspecto;

	/**
	 * The Descricao.
	 */
	@NotEmpty
	@Size(max = 200)
	private String descricao;

	/**
	 * The Valor.
	 */
	@Min(0)
	@NotNull
	private BigDecimal valor;

	/**
	 * Instantiates a new Oportunidade.
	 */
	public Oportunidade() {

	}

	/**
	 * Instantiates a new Oportunidade.
	 *
	 * @param nomeProspecto the nome prospecto
	 * @param descricao     the descricao
	 * @param valor         the valor
	 */
	public Oportunidade(String nomeProspecto, String descricao, BigDecimal valor) {
		this.nomeProspecto = nomeProspecto;
		this.descricao = descricao;
		this.valor = valor;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets nome prospecto.
	 *
	 * @return the nome prospecto
	 */
	public String getNomeProspecto() {
		return nomeProspecto;
	}

	/**
	 * Sets nome prospecto.
	 *
	 * @param nomeProspecto the nome prospecto
	 */
	public void setNomeProspecto(String nomeProspecto) {
		this.nomeProspecto = nomeProspecto;
	}

	/**
	 * Gets descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Sets descricao.
	 *
	 * @param descricao the descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets valor.
	 *
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Sets valor.
	 *
	 * @param valor the valor
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Hash code int.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Equals boolean.
	 *
	 * @param obj the obj
	 * @return the boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oportunidade other = (Oportunidade) obj;
		if (id == null) {
			return other.id == null;
		} else
			return id.equals(other.id);
	}

	/**
	 * To string string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Oportunidade [id=" + id + ", nomeProspecto=" + nomeProspecto + ", descricao=" + descricao + ", valor="
				+ valor + "]";
	}

}
