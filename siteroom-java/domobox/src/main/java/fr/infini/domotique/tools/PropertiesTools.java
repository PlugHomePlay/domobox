package fr.infini.domotique.tools;

import java.util.Properties;

import lombok.SneakyThrows;

public enum PropertiesTools {

	INSTANCE;
	
	@SneakyThrows
	public Properties load(String fileName){
		Properties toReturn = new Properties();
//		@Cleanup FileInputStream fileInputStream = new FileInputStream(fileName);
		toReturn.load(PropertiesTools.class.getClassLoader().getResourceAsStream(fileName));
		return toReturn;
	}
	
}
