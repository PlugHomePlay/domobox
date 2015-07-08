package fr.infini.domotique.model.entite.atmega;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CodeSketch {
	
	@Getter
	private final List<String> include = new ArrayList<String>();
	
	@Getter
	private final List<String> define = new ArrayList<String>();
	
	@Getter
	private final List<String> enTete = new ArrayList<String>();
	
	@Getter
	@Setter
	private String setupCode;
	
	@Getter
	@Setter
	private String loopCode;
	
	@Getter
	private final List<String> functionsCode = new ArrayList<String>();
	
	public void addInclude(String include){
		this.include.add(include);
	}
	
	public void addDefine(String define){
		this.define.add(define);
	}
	
	public void addEntete(String enTete){
		this.enTete.add(enTete);
	}
	
	public void addFunction(String function){
		this.functionsCode.add(function);
	}
	
}
