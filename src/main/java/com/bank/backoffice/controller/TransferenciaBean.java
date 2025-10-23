package com.bank.backoffice.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.service.MovimentacaoService;

@Named
@ViewScoped
public class TransferenciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long contaOrigemId;
	private Long contaDestinoId;
	private BigDecimal valor;
	private String historico;

	@Inject
	private MovimentacaoService service;

	public void transferir() {
		service.transferir(contaOrigemId, contaDestinoId, valor, historico);
		addMsg("Transferência concluída!");
		valor = null;
		historico = null;
	}

	private void addMsg(String s) {
		javax.faces.context.FacesContext.getCurrentInstance().addMessage(null,
				new javax.faces.application.FacesMessage(s));
	}

	public Long getContaOrigemId() {
		return contaOrigemId;
	}

	public void setContaOrigemId(Long contaOrigemId) {
		this.contaOrigemId = contaOrigemId;
	}

	public Long getContaDestinoId() {
		return contaDestinoId;
	}

	public void setContaDestinoId(Long contaDestinoId) {
		this.contaDestinoId = contaDestinoId;
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

}
