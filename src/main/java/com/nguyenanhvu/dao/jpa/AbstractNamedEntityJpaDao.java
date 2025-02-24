package com.nguyenanhvu.dao.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.nguyenanhvu.dao.INamedEntityDao;
import com.nguyenanhvu.entity.INamedEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public abstract class AbstractNamedEntityJpaDao<IDCLASS extends Comparable<IDCLASS>, T extends INamedEntity<IDCLASS>> 
extends AbstractJpaDao<IDCLASS, T>
implements INamedEntityDao<IDCLASS, T> {

	public AbstractNamedEntityJpaDao(Class<T> clazz, EntityManagerFactory emf) {
		super(clazz, emf);
	}

	public AbstractNamedEntityJpaDao(Class<T> clazz, String tableName, EntityManagerFactory emf) {
		super(clazz, tableName, emf);
	}

	@Override
	public T findByName(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		try (EntityManager em = getEntityManager()) {
			List<T> res = em.createQuery(getFindNameDeletedQuery(em, false, name)).getResultList();
			if (res.isEmpty()) {
				return null;
			}
			return res.get(0);
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	@Override
	public Collection<T> findByNames(Collection<String> names) {
		if (names == null || names.isEmpty()) {
			return  new ArrayList<>();
		}
		try (EntityManager em = getEntityManager()) {
			return em.createQuery(getFindNamesDeletedQuery(em, false, names)).getResultList();
		} catch (Exception e) {
			handleException(e);
			return  new ArrayList<>();
		}
	}

	@Override
	public boolean removeByName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> res = em.createQuery(getFindNameDeletedQuery(em, null, name)).getResultList();
			if (res.isEmpty()) {
				return false;
			}
			T t = res.get(0);
			em.getTransaction().begin();
			remove(em, t);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public int removeByNames(Collection<String> names) {
		if (names == null || names.isEmpty()) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> res = em.createQuery(getFindNamesDeletedQuery(em, null, names)).getResultList();
			if (res.isEmpty()) {
				return 0;
			}
			em.getTransaction().begin();
			for (T t : res) {
				remove(em, t);
			}
			em.getTransaction().commit();
			return res.size();
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean deleteByName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> res = em.createQuery(getFindNameDeletedQuery(em, false, name)).getResultList();
			if (res.isEmpty()) {
				return false;
			}
			T t = res.get(0);
			em.getTransaction().begin();
			t.setDeleted();
			em.persist(t);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public int deleteByNames(Collection<String> names) {
		if (names == null || names.isEmpty()) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> res = em.createQuery(getFindNamesDeletedQuery(em, false, names)).getResultList();
			if (res.isEmpty()) {
				return 0;
			}
			em.getTransaction().begin();
			for (T t : res) {
				t.setDeleted();
				em.persist(t);
			}
			em.getTransaction().commit();
			return res.size();
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean restoreByName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> res = em.createQuery(getFindNameDeletedQuery(em, true, name)).getResultList();
			if (res.isEmpty()) {
				return false;
			}
			T t = res.get(0);
			em.getTransaction().begin();
			t.setDeleted(false);
			em.persist(t);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public int restoreByNames(Collection<String> names) {
		if (names == null || names.isEmpty()) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> res = em.createQuery(getFindNamesDeletedQuery(em, true, names)).getResultList();
			if (res.isEmpty()) {
				return 0;
			}
			em.getTransaction().begin();
			for (T t : res) {
				t.setDeleted(false);
				em.persist(t);
			}
			em.getTransaction().commit();
			return res.size();
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}
	
	protected CriteriaQuery<T> getFindNameDeletedQuery(EntityManager em, Boolean deleted, String name) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> rootEntry = query.from(clazz);
		if (deleted == null) {
			return query.where(cb.equal(rootEntry.get("name"), name));
		} else {
			return query.where(cb.and(
							cb.equal(rootEntry.get("deleted"), deleted ? 1 : 0),
							cb.equal(rootEntry.get("name"), name)));
		}
	}
	
	protected CriteriaQuery<T> getFindNamesDeletedQuery(EntityManager em, Boolean deleted, Collection<String> names) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> rootEntry = query.from(clazz);
		if (deleted == null) {
			return query.where(rootEntry.get("name").in(names));
		} else {
			return query.where(cb.and(
							cb.equal(rootEntry.get("deleted"), deleted ? 1 : 0),
							rootEntry.get("name").in(names)));
		}
	}

}
