package com.nguyenanhvu.serialisation.impl;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class IntegerField<U extends DataModel<U>> extends AbstractField<Integer, U> {

	public IntegerField(
			boolean optional,
			@NonNull String name,
			Integer defaultValue,
			@NonNull Function<U, Integer> valueGetter,
			@NonNull BiConsumer<U, Integer> valueSetter
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
	}

	@Override
	public Integer read(Map<String, Object> map) {
		Object res = map == null ? null : map.get(name);
		if (res == null || ! (res instanceof Integer)) {
			return getDefaultValue();
		}
		return (Integer) res;
	}

	@Override
	public Integer readJson(JSONObject json) {
		Object res = json == null ? null : json.get(name);
		if (res == null || ! (res instanceof Integer)) {
			return getDefaultValue();
		}
		return (Integer) res;
	}

	@Override
	public Integer readIni(INIConfiguration config, List<String> section) {
		Integer res = config == null ? null : config.getInt(getIniKey(section));
		return res == null ? getDefaultValue() : res;
	}

}
