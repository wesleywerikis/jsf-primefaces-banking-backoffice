package com.bank.backoffice.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.model.Cliente;
import com.bank.backoffice.model.Conta;
import com.bank.backoffice.model.TipoConta;
import com.bank.backoffice.service.CadastroContaService;

@Named
@ViewScoped
public class AberturaContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Conta conta = new Conta();
	private Long clienteId;

	@Inject
	private CadastroContaService service;

	public String abrir() {
		service.abrir(conta, clienteId);
		addMsg("Conta aberta!");
		conta = new Conta();
		clienteId = null;
		return null;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long id) {
		this.clienteId = id;
	}

	public TipoConta[] getTipoContas() {
		return TipoConta.values();
	}

	private void addMsg(String s) {
		javax.faces.context.FacesContext.getCurrentInstance().addMessage(null,
				new javax.faces.application.FacesMessage(s));
	}

	public Conta getConta() {
		if (conta == null)
			conta = new Conta();
		if (conta.getCliente() == null)
			conta.setCliente(new Cliente());
		return conta;
	}

	public void setConta(Conta c) {
		this.conta = c;
	}

}
