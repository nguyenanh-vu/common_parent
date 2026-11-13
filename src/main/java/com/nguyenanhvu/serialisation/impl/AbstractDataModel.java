package com.nguyenanhvu.serialisation.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

import com.nguyenanhvu.serialisation.DataModel;
import com.nguyenanhvu.serialisation.Field;

public abstract class AbstractDataModel<T extends AbstractDataModel<T>> implements DataModel<T> {
	
	protected final Map<String, Field<?,T>> fieldMap;
	
	public AbstractDataModel(Set<Field<?,T>> fields) {
		fieldMap = new HashMap<>();
		for (Field<?,T> f : fields) {
			fieldMap.put(f.getName().toUpperCase(), f);
		}
	}
	
	@Override
	public Set<Field<?,T>> getFields() {
		return new HashSet<>(fieldMap.values());
	}

	@Override
	public Map<String, Field<?,T>> getFieldAsMap() {
		return Collections.unmodifiableMap(fieldMap);
	}

	@Override
	public Field<?,T> getField(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		return fieldMap.getOrDefault(name.toUpperCase(), null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean read(Map<String, Object> map) {
		for (Field<?,T> f : fieldMap.values()) {
			if (!f.readToModel(map, (T) this)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean readJson(JSONObject o) {
		for (Field<?,T> f : fieldMap.values()) {
			if (!f.readJsonToModel(o, (T) this)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean readIni(INIConfiguration config, List<String> section) {
		for (Field<?,T> f : fieldMap.values()) {
			if (!f.readIniToModel(config, section, (T) this)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void toJson(JSONObject o) {
		for (Field<?,T> f : fieldMap.values()) {
			f.writeJsonFromModel(o, (T) this);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void toMap(Map<String, Object> map) {
		for (Field<?,T> f : fieldMap.values()) {
			f.writeFromModel(map, (T) this);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toCsv(Set<String> headers, String separator) {
		List<String> buffer = new ArrayList<>();
		for (String s : headers) {
			String res = null;
			Field<?, T> field = getField(s);
			if (field != null) {
				res = field.toStringFromModel((T) this);
			}
			if (res != null) {
				res = "";
			}
			buffer.add(res);
		}
		return String.join(separator, buffer);
	}

}
