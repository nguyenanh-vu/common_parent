package com.nguyenanhvu.serialisation.impl;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public abstract class AbstractStringField<T, U extends DataModel<U>> extends AbstractField<T, U> {

	public AbstractStringField(
			boolean optional,
			@NonNull String name,
			T defaultValue,
			@NonNull Function<U, T> valueGetter,
			@NonNull BiConsumer<U, T> valueSetter) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
	}

	@Override
	public T read(Map<String, Object> map) {
		Object res = map == null ? null : map.get(name);
		if (res == null || ! (res instanceof String)) {
			return getDefaultValue();
		}
		return fromString((String) res);
	}

	@Override
	public T readJson(JSONObject json) {
		Object res = json == null ? null : json.get(name);
		if (res == null || ! (res instanceof String)) {
			return getDefaultValue();
		}
		return fromString((String) res);
	}

	@Override
	public T readIni(INIConfiguration config, List<String> section) {
		String res = config == null ? null : config.getString(getIniKey(section));
		return fromString((String) res);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void writeJson(JSONObject json, T value) {
		if (json != null) {
			json.put(name, toString(value));
		}
	}

	@Override
	public void write(Map<String, Object> map, T value) {
		if (map != null) {
			map.put(name, toString(value));
		}
	}

	protected abstract T fromString(String s);
	
}
