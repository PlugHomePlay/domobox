package fr.infini.domobox.solr.mapper;

import java.util.ArrayList;

public interface Mapper<T, R> {

	R map(T type);
	
	default String getString(Object value){
		@SuppressWarnings("unchecked")
		ArrayList<String> arrayList = (ArrayList<String>)value;
		return arrayList.get(0);
	}
}
