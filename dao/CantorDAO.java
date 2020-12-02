package br.com.dao;

import java.util.List;
import javax.persistence.EntityManager;

import br.com.db.DB;
import br.com.db.DbException;
import br.com.model.Cantor;

public class CantorDAO implements InterfaceDAO<Cantor> {

	private EntityManager em = null;

	public CantorDAO() {
		this.em = DB.getEntityManager();
	}

	@Override
	public boolean insert(Cantor t) {
		System.out.println("Insert");
		try {
			em.getTransaction().begin();
			System.out.println(t + "inicio");
			em.persist(t);
			System.out.println(t + "meio");
			em.getTransaction().commit();
			System.out.println(t + "fim");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
		return true;
				
	}

	@Override
	public Cantor findById(int id) {
		System.out.println("findById");

		try {
			Cantor c = em.find(Cantor.class, id);
			if (c != null) {
				return c;
			}
		} catch (Exception e) {
			System.out.println("erroooo-----" + e.getMessage());
			throw new DbException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Cantor> findAll() {
		System.out.println("findAll");

		try {
			return em.createQuery("FROM " + Cantor.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("errooo ----- " + e.getMessage());
			throw new DbException(e.getMessage());
		}
	}


	@SuppressWarnings("finally")
	@Override
	public boolean update(Cantor t) {
		System.out.println("update");
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			return true;
		}
	}

	
	@Override
	public boolean delete(Cantor t) {
		System.out.println("delete");
		try {
			this.em.getTransaction().begin();
			this.em.remove(t);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}
}
