package fr.infini.domotique.siteroom.dao.implementation;

import lombok.Getter;
import lombok.SneakyThrows;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public enum DaoHelper {

	INSTANCE;
	
	@Getter
	private Morphia morphia;
	
	@Getter
	private Datastore dataStore;
	
	@SneakyThrows
	private DaoHelper() {
		morphia = new Morphia();
		morphia.mapPackage("fr.infini.domotique.siteroom.model");
		
		MongoClient mongo = new MongoClient("127.0.0.1", 27017);
		
		dataStore = morphia.createDatastore(mongo, "sensors");
	}
}
