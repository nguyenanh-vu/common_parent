package com.nguyenanhvu.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.nguyenanhvu.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;

public class JpaDaoTests {
	
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("H2");

	@Entity
	@Table(name = "TEST")
	private static class TestEntity extends AbstractEntity<Long> {
		
		public int flag = 0;
		
		public String toString() {
			return String.format("%d-%b", getId(), isDeleted());
		}
	}
	
	private static class TestDao extends AbstractJpaDao<Long, TestEntity> {
		
		public TestDao() {
			super(TestEntity.class, "TEST", emf);
		}

		@Override
		public void handleException(Exception e) {
		}
	}
	
	@AfterEach
    public void clear(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery(em.getCriteriaBuilder().createCriteriaDelete(TestEntity.class)).executeUpdate();
		em.getTransaction().commit();
		em.close();
		
    }
	
	@Test
	public void getEntityManagerTest() {
		TestDao dao = new TestDao();
		EntityManager em = dao.getEntityManager();
		assertNotNull(em);
		em.close();
		
		assertTrue(dao.testConnection());
	}
	
	@Test
	public void findAllEmptyTest() {
		TestDao dao = new TestDao();
		Collection<TestEntity> res = dao.findAll();
		assertNotNull(res);
		assertEquals(0, res.size());
	}
	
	@Test
	public void persistTest() {
		TestDao dao = new TestDao();
		
		for (int i = 0; i < 5; i++) {
			assertTrue(dao.save(new TestEntity()));
		}
		
		Collection<TestEntity> res = dao.findAll();
		assertNotNull(res);
		assertEquals(5, res.size());
		
		Collection<TestEntity> toPersist = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			assertTrue(toPersist.add(new TestEntity()));
		}
		assertEquals(5, dao.save(toPersist));
		res = dao.findAll();
		assertNotNull(res);
		assertEquals(10, res.size());
		assertEquals(0, dao.save(toPersist));
		for (TestEntity t : toPersist) {
			assertFalse(dao.save(t));
		}
		TestEntity n = new TestEntity();
		toPersist.add(n);
		for (TestEntity t : toPersist) {
			t.flag = 1;
		}
		assertEquals(5, dao.update(toPersist));
		dao.save(n);
		for (TestEntity t : toPersist) {
			TestEntity found = dao.findById(t.getId());
			assertNotNull(found);
			assertEquals(1, found.flag);
		}
		
		for (TestEntity t : toPersist) {
			t.flag = 0;
			assertTrue(dao.update(t));
			TestEntity found = dao.findById(t.getId());
			assertEquals(0, found.flag);
		}
		
	}
	
	@Test
	public void testFindById() {
		
		Collection<Long> idsToTest = new ArrayList<>();
		TestDao dao = new TestDao();
		TestEntity e;
		for (int i = 0; i < 5; i++) {
			e = new TestEntity();
			dao.save(e);
			idsToTest.add(e.getId());
		}
		
		for (Long l : idsToTest) {
			assertNotNull(dao.findById(l));
		}
		
		Collection<TestEntity> res = dao.findByIds(idsToTest);
		assertNotNull(res);
		assertEquals(5, res.size());
		
		assertNull(dao.findById(null));
		res = dao.findByIds(null);
		assertNotNull(res);
		assertEquals(0, res.size());
	}
	
	@Test
	public void testRemove() {
		List<TestEntity> toTest = new ArrayList<>();
		TestDao dao = new TestDao();
		TestEntity e;
		for (int i = 0; i < 5; i++) {
			e = new TestEntity();
			toTest.add(e);
			dao.save(e);
		}
		assertTrue(dao.remove(toTest.get(2)));
		Collection<TestEntity> res = dao.findAll();
		assertNotNull(res);
		assertEquals(4, res.size());
		e = dao.findById(toTest.get(2).getId());
		assertNull(e);
		e = dao.findById(toTest.get(3).getId());
		assertNotNull(e);
		assertEquals(toTest.get(3).getId(), e.getId());
		assertFalse(dao.remove(new TestEntity()));
		e = null;
		assertFalse(dao.remove(e));
		List<TestEntity> toRemove = null;
		assertEquals(0, dao.remove(toRemove));
		
		toRemove = new ArrayList<>();
		toRemove.add(toTest.get(1));
		toRemove.add(toTest.get(2));
		toRemove.add(toTest.get(3));
		toRemove.add(new TestEntity());
		
		assertEquals(2, dao.remove(toRemove));
		res = dao.findAll();
		assertNotNull(res);
		assertEquals(2, res.size());
	}
	
	@Test
	public void testRemoveById() {
		
		List<Long> idsToTest = new ArrayList<>();
		TestDao dao = new TestDao();
		TestEntity e;
		for (int i = 0; i < 5; i++) {
			e = new TestEntity();
			dao.save(e);
			idsToTest.add(e.getId());
		}
		
		assertTrue(dao.removeById(idsToTest.get(2)));
		
		Collection<TestEntity> res = dao.findByIds(idsToTest);
		assertNotNull(res);
		assertEquals(4, res.size());
		
		e = dao.findById(idsToTest.get(2));
		assertNull(e);
		
		List<Long> toRemove = new ArrayList<>();
		toRemove.add(idsToTest.get(1));
		toRemove.add(idsToTest.get(2));
		toRemove.add(idsToTest.get(3));
		
		assertEquals(2, dao.removeByIds(toRemove));
		res = dao.findByIds(idsToTest);
		assertNotNull(res);
		assertEquals(2, res.size());
		
		e = dao.findById(idsToTest.get(3));
		assertNull(e);
	}
	
	@Test
	public void testDelete() {
		List<TestEntity> toTest = new ArrayList<>();
		TestDao dao = new TestDao();
		TestEntity e;
		for (int i = 0; i < 5; i++) {
			e = new TestEntity();
			dao.save(e);
			toTest.add(e);
		}
		
		e = null;
		assertFalse(dao.delete(e));
		assertFalse(dao.delete(new TestEntity()));
		List<TestEntity> toRemove = null;
		assertEquals(0, dao.delete(toRemove));
		toRemove = new ArrayList<>();
		toRemove.add(new TestEntity());
		assertEquals(0, dao.delete(toRemove));
		
		assertTrue(dao.delete(toTest.get(2)));
		Collection<TestEntity> res = dao.findAll();
		assertNotNull(res);
		assertEquals(5, res.size());
		
		res = dao.findAll(false);
		assertNotNull(res);
		assertEquals(4, res.size());
		
		e = dao.findById(toTest.get(2).getId());
		assertNull(e);
		e = dao.findById(toTest.get(3).getId());
		assertNotNull(e);
		assertFalse(e.isDeleted());
		assertEquals(toTest.get(3).getId(), e.getId());
		
		toRemove = new ArrayList<>();
		toRemove.add(toTest.get(1));
		toRemove.add(toTest.get(2));
		toRemove.add(toTest.get(3));
		toRemove.add(new TestEntity());
		
		assertEquals(2, dao.delete(toRemove));
		res = dao.findAll();
		assertNotNull(res);
		assertEquals(5, res.size());
		res = dao.findAll(false);
		assertNotNull(res);
		assertEquals(2, res.size());
		res = dao.findAll(true);
		assertNotNull(res);
		assertEquals(3, res.size());
		for (TestEntity t : res) {
			assertTrue(t.isDeleted());
		}
	}

	@Test
	public void testDeleteById() {
		
		List<Long> idsToTest = new ArrayList<>();
		TestDao dao = new TestDao();
		TestEntity e;
		for (int i = 0; i < 5; i++) {
			e = new TestEntity();
			dao.save(e);
			idsToTest.add(e.getId());
		}
		
		assertTrue(dao.deleteById(idsToTest.get(2)));
		
		Collection<TestEntity> res = dao.findByIds(idsToTest);
		assertNotNull(res);
		assertEquals(4, res.size());
		
		e = dao.findById(idsToTest.get(2));
		assertNull(e);
		
		List<Long> toRemove = new ArrayList<>();
		toRemove.add(idsToTest.get(1));
		toRemove.add(idsToTest.get(2));
		toRemove.add(idsToTest.get(3));
		
		assertEquals(2, dao.deleteByIds(toRemove));
		
		res = dao.findByIds(toRemove);
		assertNotNull(res);
		assertEquals(0, res.size());
		res = dao.findByIds(idsToTest);
		assertNotNull(res);
		assertEquals(2, res.size());
		
		e = dao.findById(idsToTest.get(3));
		assertNull(e);
	}
	
	@Test
	public void testRestore() {
		List<TestEntity> toTest = new ArrayList<>();
		TestDao dao = new TestDao();
		TestEntity e;
		for (int i = 0; i < 5; i++) {
			e = new TestEntity();
			toTest.add(e);
			dao.save(e);
		}
		dao.delete(toTest.get(2));
		dao.restore(toTest.get(2));
		Collection<TestEntity> res = dao.findAll();
		assertNotNull(res);
		assertEquals(5, res.size());
		
		res = dao.findAll(false);
		assertNotNull(res);
		assertEquals(5, res.size());
		
		e = dao.findById(toTest.get(2).getId());
		assertNotNull(e);
		assertFalse(e.isDeleted());
		assertEquals(toTest.get(2).getId(), e.getId());
		
		List<TestEntity> toRemove = new ArrayList<>();
		toRemove.add(toTest.get(1));
		toRemove.add(toTest.get(2));
		toRemove.add(toTest.get(3));
		toRemove.add(new TestEntity());
		
		dao.delete(toRemove);
		dao.restore(toRemove);
		res = dao.findAll();
		assertNotNull(res);
		assertEquals(5, res.size());
		res = dao.findAll(false);
		assertNotNull(res);
		assertEquals(5, res.size());
		for (TestEntity t : res) {
			assertFalse(t.isDeleted());
		}
		res = dao.findAll(true);
		assertNotNull(res);
		assertEquals(0, res.size());
	}

	@Test
	public void testRestoreById() {
		
		List<Long> idsToTest = new ArrayList<>();
		TestDao dao = new TestDao();
		TestEntity e;
		for (int i = 0; i < 5; i++) {
			e = new TestEntity();
			dao.save(e);
			idsToTest.add(e.getId());
		}
		
		dao.deleteById(idsToTest.get(2));
		dao.restoreById(idsToTest.get(2));
		
		Collection<TestEntity> res = dao.findByIds(idsToTest);
		assertNotNull(res);
		assertEquals(5, res.size());
		
		e = dao.findById(idsToTest.get(2));
		assertNotNull(e);
		assertFalse(e.isDeleted());
		assertEquals(idsToTest.get(2), e.getId());
		
		List<Long> toRemove = new ArrayList<>();
		toRemove.add(idsToTest.get(1));
		toRemove.add(idsToTest.get(2));
		toRemove.add(idsToTest.get(3));
		
		dao.deleteByIds(toRemove);
		dao.restoreByIds(toRemove);
		
		res = dao.findByIds(toRemove);
		assertNotNull(res);
		assertEquals(3, res.size());
		res = dao.findByIds(idsToTest);
		assertNotNull(res);
		assertEquals(5, res.size());
		
		e = dao.findById(idsToTest.get(3));
		assertNotNull(e);
	}
	
}
