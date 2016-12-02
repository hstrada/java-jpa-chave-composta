package br.com.jpa.helper;

import javax.persistence.EntityManager;

import br.com.jpa.entity.Cliente;

public class VendasHelper {
	private EntityManager em;

	public VendasHelper(EntityManager em) {
		this.em = em;
	}

	public void salvar(Cliente cliente) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
}