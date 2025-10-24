package com.bank.backoffice.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.model.Cliente;
import com.bank.backoffice.service.CadastroClienteService;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente = new Cliente();

	@Inject
	private CadastroClienteService service;

	public String salvar() {
		service.salvar(cliente);
		addMsg("Cliente salvo!");
		cliente = new Cliente();
		return null;
	}

	private void addMsg(String s) {
		javax.faces.context.FacesContext.getCurrentInstance().addMessage(null,
				new javax.faces.application.FacesMessage(s));
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente c) {
		this.cliente = c;
	}
}
