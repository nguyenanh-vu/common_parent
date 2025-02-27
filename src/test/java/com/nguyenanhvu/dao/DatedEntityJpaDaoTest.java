package com.nguyenanhvu.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nguyenanhvu.dao.IDatedEntityDao.TimeSearchCriteria;
import com.nguyenanhvu.dao.jpa.AbstractDatedEntityJpaDao;
import com.nguyenanhvu.entity.impl.AbstractDatedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import lombok.Getter;

public class DatedEntityJpaDaoTest {

	
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("H2");

	@Entity
	@Table(name = "DATED_TEST")
	private static class TestDatedEntity extends AbstractDatedEntity<Long> {
		
		public static enum TestDatedEntitySearchProperty implements ISearchProperty<TestDatedEntity> {
			
			TIMESTAMP("timestamp", java.sql.Timestamp.class)
			;
			
			@Getter
			public final String columnName;
			@Getter
			public final Class<?> clazz;
			
			private TestDatedEntitySearchProperty(String columnName, Class<?> clazz) {
				this.columnName = columnName;
				this.clazz = clazz;
			}
		}
		
		public TestDatedEntity() {
			super();
		}
		
		public TestDatedEntity(java.sql.Timestamp timestamp) {
			super(timestamp);
		}
		
		public String toString() {
			return String.format("%d-%b", getId(), isDeleted());
		}
	}
	
	private static class TestDatedEntityDao extends AbstractDatedEntityJpaDao<Long, TestDatedEntity> {
		
		public TestDatedEntityDao() {
			super(TestDatedEntity.class, "DATED_TEST", emf);
		}

		@Override
		public void handleException(Exception e) {
			e.printStackTrace();
		}

		@Override
		public ISearchProperty<TestDatedEntity>[] getSearchProperties() {
			return TestDatedEntity.TestDatedEntitySearchProperty.values();
		}
	}
	
	@Test
	public void findWithDateTest() {
		TestDatedEntityDao dao = new TestDatedEntityDao();
		List<TestDatedEntity> toTest = new ArrayList<>();
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2025, 11, 10, 0, 0))));
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2025, 11, 10, 0, 0))));
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2025, 11, 15, 0, 0))));
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2025, 11, 15, 0, 0))));
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2025, 12, 10, 0, 0))));
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2026, 11, 10, 0, 0))));
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2026, 11, 11, 0, 0))));
		toTest.add(new TestDatedEntity(java.sql.Timestamp.valueOf(LocalDateTime.of(2026, 12, 10, 0, 0))));
		toTest.add(new TestDatedEntity());
		toTest.add(new TestDatedEntity());
		
		dao.save(toTest);
		assertEquals(10, dao.findWithTimestamp(null, null, null, null).size());
		assertEquals(8, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.after(LocalDateTime.of(2025, 11, 15, 0, 0), false)).size());
		assertEquals(6, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.after(LocalDateTime.of(2025, 11, 15, 0, 0), true)).size());
		assertEquals(6, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.before(LocalDateTime.of(2025, 11, 15, 0, 0), false)).size());
		assertEquals(4, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.before(LocalDateTime.of(2025, 11, 15, 0, 0), true)).size());
		assertEquals(3, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.day(LocalDateTime.of(2026, 11, 10, 0, 0), false)).size());
		assertEquals(1, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.day(LocalDateTime.of(2026, 11, 10, 0, 0), true)).size());
		assertEquals(3, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.exact(LocalDateTime.of(2026, 11, 10, 0, 0), false)).size());
		assertEquals(1, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.exact(LocalDateTime.of(2026, 11, 10, 0, 0), true)).size());
		assertEquals(7, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.between(
						LocalDateTime.of(2025, 11, 15, 0, 0), 
						LocalDateTime.of(2026, 11, 11, 0, 0), 
						false)).size());
		assertEquals(5, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.between(
						LocalDateTime.of(2025, 11, 15, 0, 0), 
						LocalDateTime.of(2026, 11, 11, 0, 0), 
						true)).size());
		assertEquals(2, dao.findWithTimestamp(null, null, null, 
				TimeSearchCriteria.isNull()).size());
	}
}
