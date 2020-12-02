package br.com.dao;

import java.util.List;
import javax.persistence.EntityManager;

import br.com.db.DB;
import br.com.db.DbException;
import br.com.model.Musica;

public class MusicaDAO implements InterfaceDAO<Musica> {

	private EntityManager em = null;

	public MusicaDAO() {
		this.em = DB.getEntityManager();
	}

	@Override
	public boolean insert(Musica t) {
		System.out.println("Insert");
		try {
			System.out.println(t + "inicio");
			em.getTransaction().begin();
			System.out.println(t + "meio");
			em.persist(t);
			System.out.println(t + "fim");
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
		return true;
	}

	@Override
	public Musica findById(int id) {
		System.out.println("findById");

		try {
			Musica p = em.find(Musica.class, id);
			if (p != null) {
				return p;
			}
		} catch (Exception e) {
			System.out.println("vish..." + e.getMessage());
			throw new DbException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Musica> findAll() {
		System.out.println("findAll");

		try {
			return em.createQuery("FROM " + Musica.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("vix... " + e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	@Override
	public boolean update(Musica t) {
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
	public boolean delete(Musica t) {
		System.out.println("delete");
		try {
			this.em.getTransaction().begin();
			this.em.remove(t);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

