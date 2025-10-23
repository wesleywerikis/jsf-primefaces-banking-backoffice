package com.bank.backoffice.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.model.Conta;
import com.bank.backoffice.model.Lancamento;
import com.bank.backoffice.repository.Lancamentos;

@Named
@ViewScoped
public class ExtratoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Conta conta;
	private Date de;
	private Date ate;

	private int first = 0;
	private int pageSize = 20;
	private long total = 0;
	private List<Lancamento> pagina;

	@Inject
	private Lancamentos repo;

	public void buscar() {
		if (conta == null) {
			pagina = Collections.emptyList();
			total = 0;
			return;
		}
		pagina = repo.extrato(conta, de, ate, first, pageSize);
		total = repo.extratoCount(conta, de, ate);
	}

	public void next() {
		if (first + pageSize < total) {
			first += pageSize;
			buscar();
		}
	}

	public void prev() {
		if (first - pageSize >= 0) {
			first -= pageSize;
			buscar();
		}
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Date getDe() {
		return de;
	}

	public void setDe(Date de) {
		this.de = de;
	}

	public Date getAte() {
		return ate;
	}

	public void setAte(Date ate) {
		this.ate = ate;
	}

	public List<Lancamento> getPagina() {
		return pagina;
	}

	public int getFirst() {
		return first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotal() {
		return total;
	}

}
