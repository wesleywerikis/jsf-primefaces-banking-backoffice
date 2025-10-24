package com.bank.backoffice.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.bank.backoffice.model.Conta;

public class Contas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Conta porId(Long id) {
		return em.find(Conta.class, id);
	}

	public Conta porAgenciaNumero(String ag, String num) {
		try {
			return em.createQuery("from Conta c where c.agencia = :agencia and c.numero = :numero", Conta.class)
					.setParameter("agencia", ag).setParameter("numero", num).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Conta salvar(Conta c) {
		return em.merge(c);
	}
}