package com.bank.backoffice.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.bank.backoffice.model.Cliente;
import com.bank.backoffice.model.Conta;
import com.bank.backoffice.repository.Clientes;
import com.bank.backoffice.repository.Contas;
import com.bank.backoffice.util.Transacional;

public class CadastroContaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Contas contas;

	@Inject
	private Clientes clientes;

	@Transacional
	public Conta abrir(Conta c, Long clienteId) {
		if (clienteId == null) {
			throw new IllegalArgumentException("Informe o cliente (ID).");
		}

		final Cliente cliente = clientes.porId(clienteId);
		if (cliente == null) {
			throw new IllegalArgumentException("Cliente não encontrado: id=" + clienteId);
		}
		c.setCliente(cliente);

		c.setAgencia(c.getAgencia() != null ? c.getAgencia().trim() : null);
		c.setNumero(c.getNumero() != null ? c.getNumero().trim() : null);

		if (contas.porAgenciaNumero(c.getAgencia(), c.getNumero()) != null) {
			throw new IllegalStateException("Conta já existente para agência/número.");
		}
		if (c.getTipo() == null) {
			throw new IllegalArgumentException("Tipo da conta é obrigatório.");
		}
		if (c.getSaldo() == null) {
			c.setSaldo(java.math.BigDecimal.ZERO);
		}
		return contas.salvar(c);
	}
}
