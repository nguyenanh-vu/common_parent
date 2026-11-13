package com.nguyenanhvu.serialisation.impl;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class CharField<U extends DataModel<U>> extends AbstractField<Character, U> {

	public CharField(
			boolean optional,
			@NonNull String name,
			Character defaultValue,
			@NonNull Function<U, Character> valueGetter,
			@NonNull BiConsumer<U, Character> valueSetter
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
	}

	@Override
	public Character read(Map<String, Object> map) {
		Object res = map == null ? null : map.get(name);
		if (res == null || ! (res instanceof Character)) {
			return getDefaultValue();
		}
		return (Character) res;
	}

	@Override
	public Character readJson(JSONObject json) {
		Object res = json == null ? null : json.get(name);
		if (res == null || ! (res instanceof Character)) {
			return getDefaultValue();
		}
		return (Character) res;
	}

	@Override
	public Character readIni(INIConfiguration config, List<String> section) {
		String res = config == null ? null : config.getString(getIniKey(section));
		return (res == null || res.isEmpty()) ? getDefaultValue() : res.charAt(0);
	}

}
