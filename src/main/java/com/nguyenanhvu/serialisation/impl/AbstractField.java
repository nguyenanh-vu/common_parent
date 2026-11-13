package com.nguyenanhvu.serialisation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;
import com.nguyenanhvu.serialisation.Field;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractField<T, U extends DataModel<U>> implements Field<T, U> {
	
	@Getter
	protected final boolean optional;
	@NonNull
	@Getter
	protected final String name;
	@Getter
	protected final T defaultValue;
	@NonNull
	protected final Function<U, T> valueGetter;
	@NonNull
	protected final BiConsumer<U, T> valueSetter;

	@Override
	public T get(U model) {
		return valueGetter.apply(model);
	}

	@Override
	public void set(U model, T value) {
		valueSetter.accept(model, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void writeJson(JSONObject json, T value) {
		if (json != null) {
			json.put(name, value);
		}
	}

	@Override
	public void write(Map<String, Object> map, T value) {
		if (map != null) {
			map.put(name, value);
		}
	}
	
	@Override
	public boolean readToModel(Map<String, Object> map, U model) {
		T value = read(map);
		if (value != null) {
			valueSetter.accept(model, value);
			return true;
		}
		return optional;
	}

	@Override
	public boolean readJsonToModel(JSONObject o, U model) {
		T value = readJson(o);
		if (value != null) {
			valueSetter.accept(model, value);
			return true;
		}
		return optional;
	}

	@Override
	public boolean readIniToModel(INIConfiguration config, List<String> section, U model) {
		T value = readIni(config, section);
		if (value != null) {
			valueSetter.accept(model, value);
			return true;
		}
		return optional;
	}

	@Override
	public boolean writeJsonFromModel(JSONObject json, U model) {
		T value = valueGetter.apply(model);
		if (value != null) {
			writeJson(json, value);
			return true;
		}
		return false;
	}

	@Override
	public boolean writeFromModel(Map<String, Object> map, U model) {
		T value = valueGetter.apply(model);
		if (value != null) {
			write(map, value);
			return true;
		}
		return false;
	}

	@Override
	public String toString(T value) {
		return value == null ? null : value.toString();
	}

	@Override
	public String toStringFromModel(U model) {
		if (model == null) {
			return null;
		}
		return toString(valueGetter.apply(model));
	}
	
	protected String getIniKey(List<String> section) {
		List<String> buffer = new ArrayList<>();
		if (section != null) {
			buffer.addAll(section);
		}
		buffer.add(name);
		return String.join(".", buffer);
	}
}
