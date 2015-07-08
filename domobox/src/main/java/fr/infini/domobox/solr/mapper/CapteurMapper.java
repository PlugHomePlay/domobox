package fr.infini.domobox.solr.mapper;

import java.util.Collection;

import org.apache.solr.common.SolrDocument;

import fr.infini.domobox.model.entite.Capteur;
import fr.infini.domobox.model.entite.Fabricant;
import fr.infini.domobox.model.entite.Role;

public class CapteurMapper implements Mapper<SolrDocument, Capteur> {

	@Override
	public Capteur map(SolrDocument type) {
		
		Capteur capteur = new Capteur();
		
		Collection<String> fields = type.getFieldNames();
		
		for(String field : fields){
			Object value = type.getFieldValue(field);
			
			if("id".equals(field)){
				capteur.setId((Long) value);
			} else if("description".equals(field)){
				capteur.setDescription(getString(value));
			} else if("nom".equals(field)){
				capteur.setNom(getString(value));
			} else if("fabricant".equals(field)){
				Fabricant fabricant = new Fabricant();
				fabricant.setNom(getString(value));
				capteur.setFabricant(fabricant);
			} else if("role".equals(field)){
				Role role = new Role();
				role.setNom(getString(value));
				capteur.setRole(role);
			}
		}
		
		return capteur;
	}

}
