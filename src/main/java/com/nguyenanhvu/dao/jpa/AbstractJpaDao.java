package com.nguyenanhvu.dao.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nguyenanhvu.dao.IDao;
import com.nguyenanhvu.dao.ISearchProperty;
import com.nguyenanhvu.entity.IEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public abstract class AbstractJpaDao <IDCLASS extends Comparable<IDCLASS>, T extends IEntity<IDCLASS>> implements IDao<IDCLASS, T> {
	
	protected final Class<T> clazz;
	protected final String tableName;
	private final EntityManagerFactory emf;
	
	public AbstractJpaDao(Class<T> clazz, EntityManagerFactory emf) {
		this.clazz = clazz;
		this.tableName = clazz.getSimpleName();
		this.emf = emf;
	}
	
	public AbstractJpaDao(Class<T> clazz, String tableName, EntityManagerFactory emf) {
		this.clazz = clazz;
		this.tableName = tableName;
		this.emf = emf;
	}

	@Override
	public T findById(IDCLASS id) {
		if (id == null) {
			return null;
		}
		try (EntityManager em = getEntityManager()) {
			T res = em.find(clazz, id);
			if (res == null || res.isDeleted()) {
				return null;
			}
			return res;
		} catch (Exception e) {
			handleException(e);
			return null;
		} 
	}

	@Override
	public Collection<T> findByIds(Collection<IDCLASS> ids) {
		if (ids == null) {
			return new ArrayList<>();
		}
		try (EntityManager em = getEntityManager()) {
			return em.createQuery(getFindIdsDeletedQuery(em, false, ids)).getResultList();
		} catch (Exception e) {
			handleException(e);
			return new ArrayList<>();
		} 
	}

	@Override
	public Collection<T> findAll() {
		return find(null, null, null);
	}

	@Override
	public Collection<T> findAll(boolean deleted) {
		return find(null, null, deleted);
	}

	@Override
	public boolean save(T entity) {
		EntityManager em = getEntityManager();
		if (entity == null) {
			return false;
		}
		try {
			boolean res;
			em.getTransaction().begin();
			res = save(em, entity);
			em.getTransaction().commit();
			return res;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public int save(Collection<T> entities) {
		EntityManager em = getEntityManager();
		if (entities == null) {
			return 0;
		}
		try {
			int res = 0;
			em.getTransaction().begin();
			for (T t : entities) {
				if (save(em, t)) {
					res ++;
				}
			}
			em.getTransaction().commit();
			return res;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		EntityManager em = getEntityManager();
		if (entity == null) {
			return false;
		}
		try {
			boolean res;
			em.getTransaction().begin();
			res = saveOrUpdate(em, entity);
			em.getTransaction().commit();
			return res;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public int saveOrUpdate(Collection<T> entities) {
		EntityManager em = getEntityManager();
		if (entities == null) {
			return 0;
		}
		try {
			int res = 0;
			em.getTransaction().begin();
			for (T t : entities) {
				if (saveOrUpdate(em, t)) {
					res ++;
				}
			}
			em.getTransaction().commit();
			return res;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean update(T entity) {
		EntityManager em = getEntityManager();
		if (entity == null) {
			return false;
		}
		try {
			boolean res;
			em.getTransaction().begin();
			res = update(em, entity);
			em.getTransaction().commit();
			return res;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public int update(Collection<T> entities) {
		EntityManager em = getEntityManager();
		if (entities == null) {
			return 0;
		}
		try {
			int res = 0;
			em.getTransaction().begin();
			for (T t : entities) {
				if (update(em, t)) {
					res ++;
				}
			}
			em.getTransaction().commit();
			return res;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean remove(T entity) {
		EntityManager em = getEntityManager();
		if (entity == null) {
			return false;
		}
		try {
			if (!has(em, entity)) {
				return false;
			}
			em.getTransaction().begin();
			remove(em, entity);
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
	public int remove(Collection<T> entities) {
		if (entities == null) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			int count = 0;
			em.getTransaction().begin();
			for (T entity : entities) {
				if (has(em, entity)) {
					remove(em, entity);
					count ++;
				}
			}
			em.getTransaction().commit();
			return count;
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean removeById(IDCLASS id) {
		if (id == null) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			T t = em.find(clazz, id);
			if (t == null) {
				return false;
			}
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
	public int removeByIds(Collection<IDCLASS> ids) {
		if (ids == null) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			Collection<T> res = em.createQuery(getFindIdsQuery(em, ids)).getResultList();
			em.getTransaction().begin();
			for (T t : res ) {
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
	public boolean delete(T entity) {
		if (entity == null) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			if (!has(em, entity) || entity.isDeleted()) {
				return false;
			}
			em.getTransaction().begin();
			entity.setDeleted();
			persist(em, entity);
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
	public int delete(Collection<T> entities) {
		if (entities == null) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> toDelete = new ArrayList<>();
			for (T t : entities) {
				if (has(em, t) && !t.isDeleted()) {
					toDelete.add(t);
				}
			}
			if (toDelete.isEmpty()) {
				return 0;
			}
			em.getTransaction().begin();
			for (T t : toDelete ) {
				t.setDeleted();
				persist(em, t);
			}
			em.getTransaction().commit();
			return toDelete.size();
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}
	
	@Override
	public boolean deleteById(IDCLASS id) {
		if (id == null) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			T t = em.find(clazz, id);
			if (t == null || t.isDeleted()) {
				return false;
			}
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
	public int deleteByIds(Collection<IDCLASS> ids) {
		if (ids == null) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			Collection<T> res = em.createQuery(getFindIdsDeletedQuery(em, false, ids)).getResultList();
			if (res.isEmpty()) {
				return 0;
			}
			em.getTransaction().begin();
			for (T t : res ) {
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
	public boolean restore(T entity) {
		if (entity == null) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			if (entity == null || !entity.isDeleted()) {
				return false;
			}
			em.getTransaction().begin();
			entity.setDeleted(false);
			persist(em, entity);
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
	public int restore(Collection<T> entities) {
		if (entities == null) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			List<T> toRestore = new ArrayList<>();
			for (T t : entities) {
				if (t.isDeleted()) {
					toRestore.add(t);
				}
			}
			if (toRestore.isEmpty()) {
				return 0;
			}
			em.getTransaction().begin();
			for (T t : toRestore ) {
				t.setDeleted(false);
				persist(em, t);
			}
			em.getTransaction().commit();
			return toRestore.size();
		} catch (Exception e) {
			handleException(e);
			em.getTransaction().rollback();
			return 0;
		} finally {
			em.close();
		}
	}
	
	@Override
	public boolean restoreById(IDCLASS id) {
		if (id == null) {
			return false;
		}
		EntityManager em = getEntityManager();
		try {
			T t = em.find(clazz, id);
			if (t == null || !t.isDeleted()) {
				return false;
			}
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
	public int restoreByIds(Collection<IDCLASS> ids) {
		if (ids == null) {
			return 0;
		}
		EntityManager em = getEntityManager();
		try {
			Collection<T> res = em.createQuery(getFindIdsDeletedQuery(em, true, ids)).getResultList();
			if (res.isEmpty()) {
				return 0;
			}
			em.getTransaction().begin();
			for (T t : res ) {
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

	@Override
	public boolean testConnection() {
		try (EntityManager em = getEntityManager()) {
			return em.createNativeQuery("SELECT 1 FROM DUAL").getResultList() != null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public Collection<T> find(Map<ISearchProperty<T>, Object> properties, 
			Map<ISearchProperty<T>, Boolean> orderBy, Boolean deleted) {
		
		try (EntityManager em = getEntityManager()) {
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> rootQuery = cb.createQuery(clazz);
			Root<T> rootEntry = rootQuery.from(clazz);
			CriteriaQuery<T> all = getQuery(properties, orderBy, deleted, cb, rootQuery, rootEntry);
			return em.createQuery(all).getResultList();
		} catch (Exception e) {
			handleException(e);
			return new ArrayList<>();
		}
	}
	
	protected CriteriaQuery<T> getQuery(Map<ISearchProperty<T>, Object> properties, 
			Map<ISearchProperty<T>, Boolean> orderBy, Boolean deleted,
			CriteriaBuilder cb, CriteriaQuery<T> rootQuery, Root<T> rootEntry) {
		CriteriaQuery<T> all = rootQuery.select(rootEntry);
		
		List<jakarta.persistence.criteria.Predicate> predicates = getPredicates(properties,cb, rootEntry);
		if (deleted != null) {
			predicates.add(cb.equal(rootEntry.get("deleted"), deleted ? 1 : 0 ));
		}
		
		
		if (!predicates.isEmpty()) {
			if (predicates.size() == 1) {
				all = all.where(predicates.get(0));
			} else {
				all = all.where(cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0])));
			}
		}
		
		List<jakarta.persistence.criteria.Order> orderBys = getOrderBys(orderBy, cb, rootEntry);
		if (!orderBys.isEmpty()) {
			all = all.orderBy(orderBys.toArray(new jakarta.persistence.criteria.Order[0]));
		}
		return all;
	}
	
	protected List<jakarta.persistence.criteria.Predicate> getPredicates(Map<ISearchProperty<T>, Object> properties, 
			CriteriaBuilder cb, Root<T> rootEntry) throws IllegalArgumentException {
		
		List<jakarta.persistence.criteria.Predicate> res = new ArrayList<>();
		
		if (properties == null || properties.isEmpty()) {
			return res;
		}
		
		for (Entry<ISearchProperty<T>, Object> e : properties.entrySet()) {
			Object o = e.getValue();
			ISearchProperty<T> property = e.getKey();
			String columnName = property.getColumnName();
			if (o == null) {
				res.add(cb.isNull(rootEntry.get(columnName)));
			} else if (property.getClazz().isInstance(o)) {
				if (String.class.isInstance(o)) {
					res.add(cb.like(rootEntry.get(columnName), (String) o) );
				} else {
					res.add(cb.equal(rootEntry.get(columnName), o) );
				}
			} else if(Collection.class.isInstance(o)) {
				Collection<?> c = (Collection<?>) o;
				if (c.isEmpty()) {
					res.add(cb.isNull(rootEntry.get(columnName)));
				} else {
					Object firstElemement = c.iterator().next();
					if (property.getClazz().isInstance(firstElemement)) {
						res.add(rootEntry.get(columnName).in(c.toArray()));
					} else {
						throw new IllegalArgumentException(String.format("expected %s, got Collection<%s>", 
								property.getClazz().getName(), firstElemement.getClass().getName()));
					}
				} 
			} else {
				throw new IllegalArgumentException(String.format("expected %s, got %s", 
						property.getClazz().getName(), o.getClass().getName()));
			}
		}
		
		return res;
	}
	
	protected List<jakarta.persistence.criteria.Order> getOrderBys(Map<ISearchProperty<T>, Boolean> orderBy, 
			CriteriaBuilder cb, Root<T> rootEntry) {

		List<jakarta.persistence.criteria.Order> res = new ArrayList<>();
		
		if (orderBy == null || orderBy.isEmpty()) {
			return res;
		}
		for (Entry<ISearchProperty<T>, Boolean> e : orderBy.entrySet()) {
			if (Boolean.FALSE.equals(e.getValue())) {
				res.add(cb.desc(rootEntry.get(e.getKey().getColumnName())));
			} else {
				res.add(cb.asc(rootEntry.get(e.getKey().getColumnName())));
			}
		}
		return res;
	}
	
	protected CriteriaQuery<T> getFindIdsQuery(EntityManager em, Collection<IDCLASS> ids) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> rootEntry = query.from(clazz);
		CriteriaQuery<T> all = query
				.where(rootEntry.get("id").in(ids));
		return all;
	}
	
	protected CriteriaQuery<T> getFindIdsDeletedQuery(EntityManager em, boolean deleted, Collection<IDCLASS> ids) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> rootEntry = query.from(clazz);
		CriteriaQuery<T> all = query
				.where(cb.and(
						cb.equal(rootEntry.get("deleted"), deleted ? 1 : 0),
						rootEntry.get("id").in(ids)));
		return all;
	}
	
	protected boolean has(EntityManager em, T e) {
		return e.getId() != null && em.find(clazz, e.getId()) != null;
	}
	
	public boolean save(EntityManager em, T e) {
		if (!has(em, e)) {
			em.persist(e);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean saveOrUpdate(EntityManager em, T e) {
		if (has(em, e)) {
			em.persist(em.merge(e));
		} else {
			em.persist(e);
		}
		return true;
	}
	
	public boolean update(EntityManager em, T e) {
		if (has(em, e)) {
			em.persist(em.merge(e));
		} else {
			return false;
		}
		return true;
	}
	
	protected void remove(EntityManager em, T e) {
		em.remove(em.contains(e) ? e : em.merge(e));
	}
	
	protected void persist(EntityManager em, T e) {
		em.persist(em.contains(e) ? e : em.merge(e));
	}
	
}
 