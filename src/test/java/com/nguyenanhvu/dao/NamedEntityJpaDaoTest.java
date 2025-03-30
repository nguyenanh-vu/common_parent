package com.nguyenanhvu.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.nguyenanhvu.dao.jpa.AbstractNamedEntityJpaDao;
import com.nguyenanhvu.entity.impl.AbstractNamedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class NamedEntityJpaDaoTest {

	
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("H2");
	
	private static final String[] TOTEST = {
			"AAAAA",
			"BBBBB",
			"CCCCC",
			"DDDDD",
			"EEEEE"
	};

	@Entity
	@Table(name = "NAMED_TEST")
	@Accessors(chain = true)
	private static class TestNamedEntity extends AbstractNamedEntity<Long> {
		
		public static enum TestNamedEntitySearchProperty implements ISearchProperty<TestNamedEntity> {
			
			NAME("name", Integer.class),
			DELETED("deleted", Integer.class)
			;
			
			@Getter
			public final String columnName;
			@Getter
			public final Class<?> clazz;
			
			private TestNamedEntitySearchProperty(String columnName, Class<?> clazz) {
				this.columnName = columnName;
				this.clazz = clazz;
			}
		}
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Getter
		@Setter
		private Long id = null;
		
		@SuppressWarnings("unused")
		public TestNamedEntity() {
			super();
		}
		
		public TestNamedEntity(String name) {
			super(name);
		}
		
		public String toString() {
			return String.format("%d-%b", getId(), isDeleted());
		}
	}
	
	private static class TestNamedEntityDao extends AbstractNamedEntityJpaDao<Long, TestNamedEntity> {
		
		public TestNamedEntityDao() {
			super(TestNamedEntity.class, "NAMED_TEST", emf);
		}

		@Override
		public void handleException(Exception e) {
		}

		@Override
		public ISearchProperty<TestNamedEntity>[] getSearchProperties() {
			return TestNamedEntity.TestNamedEntitySearchProperty.values();
		}
	}
	
	@AfterEach
    public void clear(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery(em.getCriteriaBuilder().createCriteriaDelete(TestNamedEntity.class)).executeUpdate();
		em.getTransaction().commit();
		em.close();
		
    }
	
	@Test
	public void persistTest() {
		TestNamedEntityDao dao = new TestNamedEntityDao();
		for (String s : TOTEST) {
			assertTrue(dao.save(new TestNamedEntity(s)));
		}
		for (String s : TOTEST) {
			assertFalse(dao.save(new TestNamedEntity(s)));
		}
	}
	
	@Test
	public void testFindByName() {
		
		TestNamedEntityDao dao = new TestNamedEntityDao();
		TestNamedEntity e;
		for (String s : TOTEST) {
			assertTrue(dao.save(new TestNamedEntity(s)));
		}
		
		for (String l : TOTEST) {
			assertNotNull(dao.findByName(l));
		}
		
		Collection<TestNamedEntity> res = dao.findByNames(Arrays.asList(TOTEST));
		assertNotNull(res);
		assertEquals(5, res.size());
		
		e = dao.findByName(null);
		assertNull(e);
		res = dao.findByNames(null);
		assertNotNull(res);
		assertEquals(0, res.size());
		e = dao.findByName("FFFFF");
		assertNull(e);
		
		Collection<String> toFind = new ArrayList<>();
		toFind.addAll(Arrays.asList(TOTEST));
		toFind.add("FFFFF");
		res = dao.findByNames(toFind);
		assertNotNull(res);
		assertEquals(5, res.size());
	}
	
	@Test
	public void testRemoveByName() {
		
		TestNamedEntityDao dao = new TestNamedEntityDao();
		TestNamedEntity e;
		for (String s : TOTEST) {
			dao.save(new TestNamedEntity(s));
		}
		
		assertTrue(dao.removeByName("CCCCC"));
		
		Collection<TestNamedEntity> res = dao.findByNames(Arrays.asList(TOTEST));
		assertNotNull(res);
		assertEquals(4, res.size());
		
		e = dao.findByName("CCCCC");
		assertNull(e);
		
		List<String> toRemove = new ArrayList<>();
		toRemove.add("BBBBB");
		toRemove.add("CCCCC");
		toRemove.add("DDDDD");
		toRemove.add("FFFFF");
		
		assertEquals(2, dao.removeByNames(toRemove));
		res = dao.findByNames(Arrays.asList(TOTEST));
		assertNotNull(res);
		assertEquals(2, res.size());
		
		e = dao.findByName("DDDDD");
		assertNull(e);
		
		assertEquals(2, dao.findAll().size());
	}

	@Test
	public void testDeleteByName() {
		
		TestNamedEntityDao dao = new TestNamedEntityDao();
		TestNamedEntity e;
		for (String s : TOTEST) {
			dao.save(new TestNamedEntity(s));
		}
		
		assertTrue(dao.deleteByName("CCCCC"));
		
		Collection<TestNamedEntity> res = dao.findByNames(Arrays.asList(TOTEST));
		assertNotNull(res);
		assertEquals(4, res.size());
		
		e = dao.findByName("CCCCC");
		assertNull(e);
		
		res = dao.findAll();
		assertNotNull(res);
		assertEquals(5, res.size());
		
		List<String> toRemove = new ArrayList<>();
		toRemove.add("BBBBB");
		toRemove.add("CCCCC");
		toRemove.add("DDDDD");
		toRemove.add("FFFFF");
		
		assertEquals(2, dao.deleteByNames(toRemove));
		
		res = dao.findByNames(toRemove);
		assertNotNull(res);
		assertEquals(0, res.size());
		res = dao.findByNames(Arrays.asList(TOTEST));
		assertNotNull(res);
		assertEquals(2, res.size());
		res = dao.findAll(true);
		assertNotNull(res);
		assertEquals(3, res.size());
		res = dao.findAll(false);
		assertNotNull(res);
		assertEquals(2, res.size());
		
		e = dao.findByName("DDDDD");
		assertNull(e);
		
		assertEquals(5, dao.findAll().size());
		for (TestNamedEntity t : dao.findAll()) {
			if (toRemove.contains(t.getName())) {
				assertTrue(t.isDeleted());
			} else {
				assertFalse(t.isDeleted());
			}
		}
	}

	@Test
	public void testRestoreById() {
		
		TestNamedEntityDao dao = new TestNamedEntityDao();
		TestNamedEntity e;
		for (String s : TOTEST) {
			dao.save(new TestNamedEntity(s));
		}
		
		dao.deleteByName("CCCCC");
		assertTrue(dao.restoreByName("CCCCC"));
		
		Collection<TestNamedEntity> res = dao.findByNames(Arrays.asList(TOTEST));
		assertNotNull(res);
		assertEquals(5, res.size());
		
		e = dao.findByName("CCCCC");
		assertNotNull(e);
		assertFalse(e.isDeleted());
		
		List<String> toRemove = new ArrayList<>();
		toRemove.add("BBBBB");
		toRemove.add("CCCCC");
		toRemove.add("DDDDD");
		
		dao.deleteByNames(toRemove);
		toRemove.add("AAAAA");
		toRemove.add("FFFFF");
		assertEquals(3, dao.restoreByNames(toRemove));
		
		res = dao.findByNames(toRemove);
		assertNotNull(res);
		assertEquals(4, res.size());
		res = dao.findByNames(Arrays.asList(TOTEST));
		assertNotNull(res);
		assertEquals(5, res.size());
		
		e = dao.findByName("DDDDD");
		assertNotNull(e);
	}
}
