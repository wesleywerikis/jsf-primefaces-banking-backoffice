package com.bank.backoffice.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.bank.backoffice.model.Cliente;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Cliente porId(Long id) {
		return em.find(Cliente.class, id);
	}

	public Cliente salvar(Cliente c) {
		return em.merge(c);
	}

	public void remover(Cliente c) {
		c = porId(c.getId());
		em.remove(c);
	}

	public List<Cliente> pesquisarPorNome(String prefixo) {
		TypedQuery<Cliente> q = em.createQuery("from Cliente c where c.nome like :p order by c.nome", Cliente.class);
		q.setParameter("p", prefixo + "%");
		return q.getResultList();
	}

}
