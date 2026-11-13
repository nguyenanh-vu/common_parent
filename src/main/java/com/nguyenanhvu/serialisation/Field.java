package com.nguyenanhvu.serialisation;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

public interface Field<T, U extends DataModel<U>> {
	
	public boolean isOptional();
	
	public String getName();
	
	public T getDefaultValue();
	
	public T get(U model);
	
	public void set(U model, T value);
	
	public T read(Map<String, Object> map);
	
	public T readJson(JSONObject json);
	
	public T readIni(INIConfiguration config, List<String> section);
	
	public boolean readToModel(Map<String, Object> map, U model);
	
	public boolean readJsonToModel(JSONObject o, U model);
	
	public boolean readIniToModel(INIConfiguration config, List<String> section, U model);

	public void writeJson(JSONObject json, T value);
	
	public void write(Map<String, Object> map, T value);

	public boolean writeJsonFromModel(JSONObject json, U model);
	
	public boolean writeFromModel(Map<String, Object> map, U model);

	public String toString(T value);
	
	public String toStringFromModel(U model);
}
