package com.bank.backoffice.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bank.backoffice.model.Conta;
import com.bank.backoffice.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public Lancamento salvar(Lancamento l) {
		return em.merge(l);
	}

	public List<Lancamento> extrato(Conta conta, Date de, Date ate, int first, int pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Lancamento> cq = cb.createQuery(Lancamento.class);
		Root<Lancamento> root = cq.from(Lancamento.class);
		Predicate p = cb.equal(root.get("conta"), conta);
		if (de != null)
			p = cb.and(p, cb.greaterThanOrEqualTo(root.<Date>get("dataHora"), de));
		if (ate != null)
			p = cb.and(p, cb.lessThan(root.<Date>get("dataHora"), ate));
		cq.where(p);
		cq.orderBy(cb.desc(root.get("dataHora")), cb.desc(root.get("id")));
		return em.createQuery(cq).setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public long extratoCount(Conta conta, Date de, Date ate) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Lancamento> root = cq.from(Lancamento.class);
		Predicate p = cb.equal(root.get("conta"), conta);
		if (de != null)
			p = cb.and(p, cb.greaterThanOrEqualTo(root.<Date>get("dataHora"), de));
		if (ate != null)
			p = cb.and(p, cb.lessThan(root.<Date>get("dataHora"), ate));
		cq.select(cb.count(root)).where(p);
		return em.createQuery(cq).getSingleResult();
	}

}
