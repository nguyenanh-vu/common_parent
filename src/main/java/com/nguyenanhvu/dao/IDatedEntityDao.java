package com.nguyenanhvu.dao;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

import com.nguyenanhvu.entity.IDatedEntity;

public interface IDatedEntityDao<IDCLASS extends Comparable<IDCLASS>, T extends IDatedEntity<IDCLASS>> extends IDao<IDCLASS, T> {
	
	public static class TimeSearchCriteria {
		
		public final LocalDateTime after;
		
		public final LocalDateTime before ;
		
		public final LocalDateTime exact;
		
		public final boolean strict;
		
		public final boolean isNull;
		
		private TimeSearchCriteria(LocalDateTime after, LocalDateTime before,
				LocalDateTime exact, boolean strict, boolean isNull) {
			this.after = after;
			this.before = before;
			this.exact = exact;
			this.strict = strict;
			this.isNull = isNull;
		}
		
		public static TimeSearchCriteria after(LocalDateTime after, boolean strict) {
			return new TimeSearchCriteria(after, null, null, strict, false);
		}
		
		public static TimeSearchCriteria before(LocalDateTime before, boolean strict) {
			return new TimeSearchCriteria(null, before, null, strict, false);
		}
		
		public static TimeSearchCriteria exact(LocalDateTime exact, boolean strict) {
			return new TimeSearchCriteria(null, null, exact, strict, false);
		}
		
		public static TimeSearchCriteria between(LocalDateTime after, LocalDateTime before, boolean strict) {
			return new TimeSearchCriteria(after, before, null, strict, false);
		}
		
		public static TimeSearchCriteria day(LocalDateTime day, boolean strict) {
			LocalDateTime after = LocalDateTime.of(day.getYear(), day.getMonth(), day.getDayOfMonth(), 0, 0);
			LocalDateTime before = after.plusDays(1).minusNanos(1);
			return new TimeSearchCriteria(after, before, null, strict, false);
		}
		
		public static TimeSearchCriteria isNull() {
			return new TimeSearchCriteria(null, null, null, false, true);
		}
	}
	
	public String getTimestampColumnName();
	
	public Collection<T> findWithTimestamp(Map<ISearchProperty<T>, Object> properties, 
			Map<ISearchProperty<T>, Boolean> orderBy, Boolean deleted, TimeSearchCriteria criteria);
}
