package com.nguyenanhvu.serialisation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;
import com.nguyenanhvu.serialisation.Factory;

import lombok.NonNull;

public class DataModelField<T extends DataModel<T>, U extends DataModel<U>> extends AbstractField<T,U> {

	@NonNull
	private final Factory<T> factory;
	
	public DataModelField(
			boolean optional,
			@NonNull String name,
			T defaultValue,
			@NonNull Function<U, T> valueGetter,
			@NonNull BiConsumer<U, T> valueSetter,
			@NonNull Factory<T> factory) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
		this.factory = factory;
	}

	@Override
	public T read(Map<String, Object> map) {
		Object res = map == null ? null : map.get(name);
		if (res == null || ! (res instanceof Map)) {
			return getDefaultValue();
		}
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> data = (Map<String, Object>) res;
			return factory.read(data);
		} catch(Exception e) {
			return getDefaultValue();
		}
	}

	@Override
	public T readJson(JSONObject json) {
		Object res = json == null ? null : json.get(name);
		if (res == null || ! (res instanceof JSONObject)) {
			return getDefaultValue();
		}
		try {
			JSONObject data = (JSONObject) res;
			return factory.readJson(data);
		} catch(Exception e) {
			return getDefaultValue();
		}
	}

	@Override
	public T readIni(INIConfiguration config, List<String> section) {
		String res = config == null ? null : config.getString(getIniKey(section));
		if (res == null || res.isEmpty()) {
			return getDefaultValue();
		}
		try {
			List<String> subsection = new ArrayList<>();
			subsection.add(res);
			subsection.addAll(section);
			return factory.readIni(config, subsection);
		} catch(Exception e) {
			return getDefaultValue();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void writeJson(JSONObject json, T value) {
		if (json != null) {
			if (value == null) {
				json.put(name, null);
			} else {
				JSONObject j = new JSONObject();
				value.toJson(j);
				json.put(name, j);
			}
		}
	}

	@Override
	public void write(Map<String, Object> map, T value) {
		if (map != null) {
			if (value == null) {
				map.put(name, null);
			} else {
				Map<String, Object> submap = new HashMap<>();
				value.toMap(submap);
				map.put(name, submap);
			}
		}
	}

}
