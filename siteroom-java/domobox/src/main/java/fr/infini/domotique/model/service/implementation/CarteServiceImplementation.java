package fr.infini.domotique.model.service.implementation;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import fr.infini.domotique.model.entite.carte.Carte;
import fr.infini.domotique.model.service.CarteService;

public enum CarteServiceImplementation implements CarteService{

	INSTANCE;
	
	public void compilerCode(Carte carte){
		//Properties properties = PropertiesTools.INSTANCE.load("/config.properties");
		this.constructIno();
	}
	
	private String constructIno(){
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		velocityEngine.init();
		Template template = velocityEngine.getTemplate("code.tpl");
		
		VelocityContext velocityContext = new VelocityContext();
		
		velocityContext.put("Nom_Code", "");
		
		return "";
	}
	
}
