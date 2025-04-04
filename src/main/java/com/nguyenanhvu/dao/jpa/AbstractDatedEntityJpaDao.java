package com.nguyenanhvu.dao.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.nguyenanhvu.dao.IDatedEntityDao;
import com.nguyenanhvu.dao.ISearchProperty;
import com.nguyenanhvu.entity.IDatedEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public abstract class AbstractDatedEntityJpaDao<IDCLASS extends Comparable<IDCLASS>, T extends IDatedEntity<IDCLASS>>
extends AbstractJpaDao<IDCLASS, T>
implements IDatedEntityDao<IDCLASS, T> {

	public AbstractDatedEntityJpaDao(Class<T> clazz, EntityManagerFactory emf) {
		super(clazz, emf);
	}

	public AbstractDatedEntityJpaDao(Class<T> clazz, String tableName, EntityManagerFactory emf) {
		super(clazz, tableName, emf);
	}

	@Override
	public Collection<T> findWithTimestamp(Map<ISearchProperty<T>, Object> properties,
			Map<ISearchProperty<T>, Boolean> orderBy, Boolean deleted, TimeSearchCriteria criteria) {
		
		try (EntityManager em = getEntityManager()) {
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> rootQuery = cb.createQuery(clazz);
			Root<T> rootEntry = rootQuery.from(clazz);
			CriteriaQuery<T> all = getQuery(properties, orderBy, deleted, cb, rootQuery, rootEntry, criteria);
			return em.createQuery(all).getResultList();
		} catch (Exception e) {
			handleException(e);
			return Collections.emptyList();
		}
	}

	@Override
	public String getTimestampColumnName() {
		return "timestamp";
	}
	
	protected CriteriaQuery<T> getQuery(Map<ISearchProperty<T>, Object> properties, 
			Map<ISearchProperty<T>, Boolean> orderBy, Boolean deleted,
			CriteriaBuilder cb, CriteriaQuery<T> rootQuery, Root<T> rootEntry, TimeSearchCriteria criteria) {
		CriteriaQuery<T> all = rootQuery.select(rootEntry);
		
		List<jakarta.persistence.criteria.Predicate> predicates = getPredicates(properties,cb, rootEntry);
		if (deleted != null) {
			predicates.add(cb.equal(rootEntry.get("deleted"), deleted ? 1 : 0 ));
		}
		if (criteria != null) {
			predicates.addAll(getPredicates(criteria, cb, rootEntry));
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
	
	protected List<jakarta.persistence.criteria.Predicate> getPredicates(TimeSearchCriteria criteria, 
			CriteriaBuilder cb, Root<T> rootEntry) {

		List<jakarta.persistence.criteria.Predicate> res = new ArrayList<>();
		if (criteria == null) {
			return res;
		}
		String columnName = getTimestampColumnName();
		
		if (criteria.isNull) {
			res.add(cb.isNull(rootEntry.get(columnName)));
		}
		
		if (criteria.after != null) {
			if (criteria.strict) {
				res.add(cb.greaterThanOrEqualTo(rootEntry.get(columnName), java.sql.Timestamp.valueOf(criteria.after)));
			} else {
				res.add(cb.or(cb.isNull(rootEntry.get(columnName)), 
						cb.greaterThanOrEqualTo(rootEntry.get(columnName), java.sql.Timestamp.valueOf(criteria.after))));
			}
		}
		
		if (criteria.before != null) {
			if (criteria.strict) {
				res.add(cb.lessThanOrEqualTo(rootEntry.get(columnName), java.sql.Timestamp.valueOf(criteria.before)));
			} else {
				res.add(cb.or(cb.isNull(rootEntry.get(columnName)), 
						cb.lessThanOrEqualTo(rootEntry.get(columnName), java.sql.Timestamp.valueOf(criteria.before))));
			}
		}
		
		if (criteria.exact != null) {
			if (criteria.strict) {
				res.add(cb.equal(rootEntry.get(columnName), java.sql.Timestamp.valueOf(criteria.exact)));
			} else {
				res.add(cb.or(cb.isNull(rootEntry.get(columnName)), 
						cb.equal(rootEntry.get(columnName), java.sql.Timestamp.valueOf(criteria.exact))));
			}
		}
		
		return res;
	}
}
