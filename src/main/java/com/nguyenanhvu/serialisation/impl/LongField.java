package com.nguyenanhvu.serialisation.impl;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;

import lombok.NonNull;

public class LongField<U extends DataModel<U>> extends AbstractField<Long, U> {

	public LongField(
			boolean optional,
			@NonNull String name,
			Long defaultValue,
			@NonNull Function<U, Long> valueGetter,
			@NonNull BiConsumer<U, Long> valueSetter
			) {
		super(optional, name, defaultValue, valueGetter, valueSetter);
	}

	@Override
	public Long read(Map<String, Object> map) {
		Object res = map == null ? null : map.get(name);
		if (res == null || ! (res instanceof Long)) {
			return getDefaultValue();
		}
		return (Long) res;
	}

	@Override
	public Long readJson(JSONObject json) {
		Object res = json == null ? null : json.get(name);
		if (res == null || ! (res instanceof Long)) {
			return getDefaultValue();
		}
		return (Long) res;
	}

	@Override
	public Long readIni(INIConfiguration config, List<String> section) {
		Long res = config == null ? null : config.getLong(getIniKey(section));
		return res == null ? getDefaultValue() : res;
	}

}
