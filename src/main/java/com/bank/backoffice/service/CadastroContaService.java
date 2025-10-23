package com.bank.backoffice.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.bank.backoffice.model.Conta;
import com.bank.backoffice.repository.Contas;
import com.bank.backoffice.util.Transacional;

public class CadastroContaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Contas contas;

	@Transacional
	public Conta abrir(Conta c) {
		if (contas.porAgenciaNumero(c.getAgencia(), c.getNumero()) != null)
			throw new IllegalStateException("Conta já existente para agência/número.");
		return contas.salvar(c);
	}
}
