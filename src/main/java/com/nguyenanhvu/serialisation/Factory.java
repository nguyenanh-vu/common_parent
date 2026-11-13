package com.nguyenanhvu.serialisation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

public interface Factory<T extends DataModel<T>> {
	
	public Set<Field<?,T>> getFields();
	
	public Map<String, Field<?,T>> getFieldAsMap();
	
	public Field<?,T> getField(String name);
	
	public T read(Map<String, Object> map);
	
	public T readJson(JSONObject o);
	
	public T readIni(INIConfiguration config, List<String> section);
}
