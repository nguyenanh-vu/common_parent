package com.nguyenanhvu.serialisation.impl;

import java.util.function.BiConsumer;
import java.util.function.Function;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class EnumField<T extends Enum<T>, U extends DataModel<U>> extends AbstractStringField<T, U> {

	@NonNull
	private final Class<T> clazz;
	
	public EnumField(
			boolean optional,
			@NonNull String name,
			T defaultValue,
			@NonNull Function<U, T> valueGetter,
			@NonNull BiConsumer<U, T> valueSetter,
			@NonNull Class<T> clazz
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
		this.clazz = clazz;
	}
	
	@Override
	protected T fromString(String s) {
		try {
			return Enum.valueOf(clazz, ((String) s).trim());
		} catch (IllegalArgumentException e) {
			return getDefaultValue();
		}
	}

	@Override
	public String toString(T value) {
		return value.name();
	}

}
