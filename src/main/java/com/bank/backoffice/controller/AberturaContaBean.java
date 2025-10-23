package com.bank.backoffice.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.model.Conta;
import com.bank.backoffice.model.TipoConta;
import com.bank.backoffice.service.CadastroContaService;

@Named
@ViewScoped
public class AberturaContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Conta conta = new Conta();

	@Inject
	private CadastroContaService service;

	public void abrir() {
		service.abrir(conta);
		addMsg("Conta aberta!");
		conta = new Conta();
	}

	public TipoConta[] geTipoContas() {
		return TipoConta.values();
	}

	private void addMsg(String s) {
		javax.faces.context.FacesContext.getCurrentInstance().addMessage(null,
				new javax.faces.application.FacesMessage(s));
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta c) {
		this.conta = c;
	}

}
