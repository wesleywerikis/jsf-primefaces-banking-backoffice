package com.bank.backoffice.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.bank.backoffice.model.Cliente;
import com.bank.backoffice.repository.Clientes;
import com.bank.backoffice.util.Transacional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	@Transacional
	public Cliente salvar(Cliente c) {
		return clientes.salvar(c);
	}

	@Transacional
	public void excluir(Cliente c) {
		clientes.remover(c);
	}

}
