package com.nguyenanhvu.serialisation.impl;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class StringField<U extends DataModel<U>> extends AbstractField<String, U> {

	public StringField(
			boolean optional,
			@NonNull String name,
			String defaultValue,
			@NonNull Function<U, String> valueGetter,
			@NonNull BiConsumer<U, String> valueSetter
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
	}

	@Override
	public String read(Map<String, Object> map) {
		Object res = map == null ? null : map.get(name);
		if (res == null || ! (res instanceof String)) {
			return getDefaultValue();
		}
		return (String) res;
	}

	@Override
	public String readJson(JSONObject json) {
		Object res = json == null ? null : json.get(name);
		if (res == null || ! (res instanceof String)) {
			return getDefaultValue();
		}
		return (String) res;
	}

	@Override
	public String readIni(INIConfiguration config, List<String> section) {
		String res = config == null ? null : config.getString(getIniKey(section));
		return (res == null || res.isEmpty()) ? getDefaultValue() : res;
	}

}
