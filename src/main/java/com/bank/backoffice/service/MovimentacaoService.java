package com.bank.backoffice.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.inject.Inject;

import com.bank.backoffice.model.Conta;
import com.bank.backoffice.model.Lancamento;
import com.bank.backoffice.model.Lancamento.Tipo;
import com.bank.backoffice.repository.Contas;
import com.bank.backoffice.repository.Lancamentos;
import com.bank.backoffice.util.Transacional;

public class MovimentacaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Contas contas;

	@Inject
	private Lancamentos lancamentos;

	@Transacional
	public void transferir(Long idOrigem, Long idDestino, BigDecimal valor, String historico) {
		if (valor == null || valor.signum() <= 0)
			throw new IllegalArgumentException("Valor inválido");
		if (idOrigem.equals(idDestino))
			throw new IllegalArgumentException("Contas devem ser distintas");


		Conta origem = contas.porId(idOrigem);
		Conta destino = contas.porId(idDestino);

		if (origem == null || destino == null)
			throw new IllegalStateException("Conta inexistente");
		if (origem.getSaldo().compareTo(valor) < 0)
			throw new IllegalStateException("Saldo insuficiente");

		String ref = UUID.randomUUID().toString();

		origem.setSaldo(origem.getSaldo().subtract(valor));
		Lancamento l1 = new Lancamento();
		l1.setConta(origem);
		l1.setTipo(Tipo.DEBITO);
		l1.setValor(valor);
		l1.setHistorico(historico != null ? historico : "Transf. para " + destino.getNumero());
		l1.setReferencia(ref);
		lancamentos.salvar(l1);

		destino.setSaldo(destino.getSaldo().add(valor));
		Lancamento l2 = new Lancamento();
		l2.setConta(destino);
		l2.setTipo(Tipo.CREDITO);
		l2.setValor(valor);
		l2.setHistorico(historico != null ? historico : "Transf de " + origem.getNumero());
		l2.setReferencia(ref);
		lancamentos.salvar(l2);

		contas.salvar(origem);
		contas.salvar(destino);

	}
}
