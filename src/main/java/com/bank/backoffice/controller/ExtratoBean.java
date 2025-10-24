package com.bank.backoffice.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.model.Conta;
import com.bank.backoffice.model.Lancamento;
import com.bank.backoffice.repository.Contas;
import com.bank.backoffice.repository.Lancamentos;

@Named
@ViewScoped
public class ExtratoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date de;
	private Date ate;

	private int first = 0;
	private int pageSize = 20;
	private long total = 0;
	private List<Lancamento> pagina;

	private Long contaId;
	private Conta conta;

	@Inject
	private Contas contas;

	@Inject
	private Lancamentos repo;

	public void buscar() {
		first = 0;
		if (contaId == null) {
			pagina = Collections.emptyList();
			total = 0;
			return;
		}
		conta = contas.porId(contaId);
		if (conta == null) {
			addMsg("Conta não encontrada");
			pagina = Collections.emptyList();
			total = 0;
			return;
		}

		Date deNorm = asStartOfDay(de);
		Date ateNorm = nextDayStart(ate);

		pagina = repo.extrato(conta, deNorm, ateNorm, first, pageSize);
		total = repo.extratoCount(conta, deNorm, ateNorm);

		if (total == 0) {
			addMsg("Nenhum lançamento encontrado para os filtros informados.");
		}
	}

	private static Date asStartOfDay(Date d) {
		if (d == null)
			return null;

		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	private static Date nextDayStart(Date d) {
		if (d == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(asStartOfDay(d));
		c.add(Calendar.DATE, 1);
		return c.getTime();
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

	private void addMsg(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
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
