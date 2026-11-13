package com.nguyenanhvu.serialisation.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class LocalDateField<U extends DataModel<U>> extends AbstractStringField<LocalDate, U> {

	private final DateTimeFormatter formatter;
	
	public LocalDateField(
			boolean optional,
			@NonNull String name,
			LocalDate defaultValue,
			@NonNull Function<U, LocalDate> valueGetter,
			@NonNull BiConsumer<U, LocalDate> valueSetter
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
		this.formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	}
	
	public LocalDateField(
			boolean optional,
			@NonNull String name,
			LocalDate defaultValue,
			@NonNull Function<U, LocalDate> valueGetter,
			@NonNull BiConsumer<U, LocalDate> valueSetter,
			String format
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
		this.formatter = DateTimeFormatter.ofPattern(format);
	}

	@Override
	protected LocalDate fromString(String s) {
		if (s == null || s.isEmpty()) {
			return getDefaultValue();
		}
		try {
			return LocalDate.parse(s, formatter);	
		} catch (Exception e) {
			return getDefaultValue();
		}
	}

	@Override
	public String toString(LocalDate value) {
		return value == null ? null : value.format(formatter);
	}

}
