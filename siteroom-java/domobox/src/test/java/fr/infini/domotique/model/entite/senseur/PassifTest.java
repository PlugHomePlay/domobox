package fr.infini.domotique.model.entite.senseur;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import org.junit.Test;

import fr.infini.domotique.model.entite.atmega.CodeSketch;

@Log
public class PassifTest {

	@Test
	public void createPassif(){
		Senseur senseur = new Passif();
		
		senseur.setIdentifiant(UUID.randomUUID());
		
		senseur.setPortAttach(7);
		
		CodeSketch code = new CodeSketch();
		
		code.addInclude("OneWire.h");
		code.addDefine("DS18B20 0x28");
		code.addDefine("BROCHE_ONEWIRE 7");
		code.addEntete("OneWire ds(BROCHE_ONEWIRE)");
		code.addEntete("boolean toggleComplete = false");
		code.addEntete("String inputString = \"\"");
		
		code.addFunction(getFileContent("getTemperature_DS18B20.fct"));
		code.setLoopCode(getFileContent("loop_DS18B20.fct"));
		code.setSetupCode(getFileContent("setup_DS18B20.fct"));
		
		log.info("Objet construit, debut des tests.");
		
		
	}
	
	@SneakyThrows
	private String getFileContent(String fileName){
		String toReturn = "";		
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		@Cleanup BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line;
		
		while((line=bufferedReader.readLine()) != null){
			toReturn += line + "\n";
		}
		
		return toReturn;
	}
}
