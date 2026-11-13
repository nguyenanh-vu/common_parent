package com.nguyenanhvu.serialisation.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class LocalDateTimeField<U extends DataModel<U>> extends AbstractStringField<LocalDateTime, U> {

	private final DateTimeFormatter formatter;
	
	public LocalDateTimeField(
			boolean optional,
			@NonNull String name,
			LocalDateTime defaultValue,
			@NonNull Function<U, LocalDateTime> valueGetter,
			@NonNull BiConsumer<U, LocalDateTime> valueSetter
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
		this.formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	}

	public LocalDateTimeField(
			boolean optional,
			@NonNull String name,
			LocalDateTime defaultValue,
			@NonNull Function<U, LocalDateTime> valueGetter,
			@NonNull BiConsumer<U, LocalDateTime> valueSetter,
			String format
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
		this.formatter = DateTimeFormatter.ofPattern(format);
	}

	@Override
	protected LocalDateTime fromString(String s) {
		if (s == null || s.isEmpty()) {
			return getDefaultValue();
		}
		try {
			return LocalDateTime.parse(s, formatter);	
		} catch (Exception e) {
			return getDefaultValue();
		}
	}

}
