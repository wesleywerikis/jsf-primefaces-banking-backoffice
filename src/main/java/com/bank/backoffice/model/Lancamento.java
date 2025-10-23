package com.bank.backoffice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "lancamento", indexes = { @Index(name = "idx_conta_data", columnList = "conta_id,dataHora") })
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Tipo {
		DEBITO, CREDITO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "conta_id")
	private Conta conta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Tipo tipo;

	@NotNull
	@Column(nullable = false, precision = 19, scale = 2)
	private BigDecimal valor;

	@NotBlank
	@Size(max = 200)
	@Column(nullable = false, length = 200)
	private String historico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataHora = new Date();

	@Size(max = 50)
	@Column(length = 50)
	private String referencia;

	@Version
	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Lancamento))
			return false;
		return Objects.equals(id, ((Lancamento) o).id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
