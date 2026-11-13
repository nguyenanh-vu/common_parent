package com.nguyenanhvu.serialisation.impl;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class UUIDField<U extends DataModel<U>> extends AbstractStringField<UUID, U> {

	public UUIDField(
			boolean optional,
			@NonNull String name,
			UUID defaultValue,
			@NonNull Function<U, UUID> valueGetter,
			@NonNull BiConsumer<U, UUID> valueSetter
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
	}
	
	@Override
	protected UUID fromString(String s) {
		try {
			return UUID.fromString((String) s);
		} catch (IllegalArgumentException e) {
			return getDefaultValue();
		}
	}
}
