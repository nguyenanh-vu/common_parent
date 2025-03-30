package com.nguyenanhvu.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.nguyenanhvu.dao.jpa.AbstractJpaDao;
import com.nguyenanhvu.entity.impl.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

public class EmbeddedIdEntityDaoTest {
	
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("H2");

	@Entity
	@Table(name = "EMBEDDEDIDTEST")
	@IdClass(EmbeddedIdTestEntity.IdClass.class)
	@Accessors(chain = true)
	private static class EmbeddedIdTestEntity extends AbstractEntity<EmbeddedIdTestEntity.IdClass> {
		
		public static enum TestSearchProperty implements ISearchProperty<EmbeddedIdTestEntity> {
			
			KEY1("key1", Long.class),
			KEY2("key2", String.class)
			;
			
			@Getter
			public final String columnName;
			@Getter
			public final Class<?> clazz;
			
			private TestSearchProperty(String columnName, Class<?> clazz) {
				this.columnName = columnName;
				this.clazz = clazz;
			}
		}
		
		@NoArgsConstructor
		@AllArgsConstructor
		public static class IdClass implements Comparable<IdClass>{

			@Getter
			@Setter
			private long key1 = 0l;

			@Getter
			@Setter
			@NonNull
			private String key2 = "";

			@Override
			public int compareTo(IdClass o) {
				int res = Long.compare(this.key1, o.key1);
				if (res == 0) {
					return this.key2.compareTo(o.key2);
				} else {
					return res;
				}
			}
			
		}

		@Id
		@Column(name = "KEY1", nullable = false)
		@Getter
		@Setter
		private long key1 = 0l;

		@Id
		@Column(name = "KEY2", nullable = false)
		@Getter
		@Setter
		private String key2 = "";
		
		public EmbeddedIdTestEntity() {
		}
		
		public EmbeddedIdTestEntity(long key1, @NonNull String key2) {
			this.key1 = key1;
			this.key2 = key2;
		}
		
		public String toString() {
			return String.format("%d-%b", getId(), isDeleted());
		}

		@Override
		public IdClass getId() {
			return new IdClass(key1, key2);
		}

		@Override
		public EmbeddedIdTestEntity setId(IdClass id) {
			if (id == null) {
				this.key1 = 0l;
				this.key2 = "";
			} else {
				this.key1 = id.key1;
				this.key2 = id.key2;
			}
			return this;
		}
	}
	
	private static class TestEmbeddedIdDao extends AbstractJpaDao<EmbeddedIdTestEntity.IdClass, EmbeddedIdTestEntity> {
		
		public TestEmbeddedIdDao() {
			super(EmbeddedIdTestEntity.class, "EMBEDDEDIDTEST", emf);
		}

		@Override
		public void handleException(Exception e) {
		}

		@Override
		public ISearchProperty<EmbeddedIdTestEntity>[] getSearchProperties() {
			return EmbeddedIdTestEntity.TestSearchProperty.values() ;
		}
	}
	
	@AfterEach
    public void clear(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery(em.getCriteriaBuilder().createCriteriaDelete(EmbeddedIdTestEntity.class)).executeUpdate();
		em.getTransaction().commit();
		em.close();
		
    }
	
	@Test
	public void tests() {
		TestEmbeddedIdDao dao = new TestEmbeddedIdDao();
		
		assertTrue(dao.save(new EmbeddedIdTestEntity(0, "AA")));
		assertTrue(dao.save(new EmbeddedIdTestEntity(0, "BB")));
		assertTrue(dao.save(new EmbeddedIdTestEntity(0, "CC")));
		assertTrue(dao.save(new EmbeddedIdTestEntity(1, "AA")));
		assertTrue(dao.save(new EmbeddedIdTestEntity(1, "BB")));
		assertTrue(dao.save(new EmbeddedIdTestEntity(1, "CC")));
		
		assertNull(dao.findById(new EmbeddedIdTestEntity.IdClass()));
		assertThrows(NullPointerException.class, () -> dao.findById(new EmbeddedIdTestEntity.IdClass(0, null)));
		assertNull(dao.findById(new EmbeddedIdTestEntity.IdClass(0, "DD")));
		assertNull(dao.findById(new EmbeddedIdTestEntity.IdClass(2, "AA")));
		
		assertNotNull(dao.findById(new EmbeddedIdTestEntity.IdClass(0, "AA")));
		assertNotNull(dao.findById(new EmbeddedIdTestEntity.IdClass(1, "AA")));
		assertNotNull(dao.findById(new EmbeddedIdTestEntity.IdClass(0, "BB")));
		
		assertEquals(3, dao.find(Collections.singletonMap(EmbeddedIdTestEntity.TestSearchProperty.KEY1, 1l), null, null).size());
		assertEquals(2, dao.find(Collections.singletonMap(EmbeddedIdTestEntity.TestSearchProperty.KEY2, "AA"), null, null).size());
		assertEquals(0, dao.find(Collections.singletonMap(EmbeddedIdTestEntity.TestSearchProperty.KEY1, 2l), null, null).size());
		assertEquals(0, dao.find(Collections.singletonMap(EmbeddedIdTestEntity.TestSearchProperty.KEY2, "DD"), null, null).size());
	}

}
