package com.nguyenanhvu.serialisation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration2.INIConfiguration;
import org.json.simple.JSONObject;

public interface DataModel<T extends DataModel<T>> {
	
	public Set<Field<?,T>> getFields();
	
	public Map<String, Field<?,T>> getFieldAsMap();
	
	public Field<?,T> getField(String name);
	
	public boolean read(Map<String, Object> map);
	
	public boolean readJson(JSONObject o);
	
	public boolean readIni(INIConfiguration config, List<String> section);
	
	public void toJson(JSONObject o);

	public void toMap(Map<String, Object> map);
	
	public String toCsv(Set<String> headers, String separator);

}
