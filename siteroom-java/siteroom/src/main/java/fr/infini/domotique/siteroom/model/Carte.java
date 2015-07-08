package fr.infini.domotique.siteroom.model;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;


@Entity("cartes")
/**
 * Modele définissant une carte.
 * Une carte comporte un ou plusieurs senseurs. 
 * @author primael
 *
 */
public class Carte {

	@Id
	private String id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String portName;
	
	@Getter
	@Setter
	private String picture;
	
	@Getter
	@Setter
	private Date createdAt;
	
	@Getter
	@Setter
	private String description;
	
	@Reference
	@Getter
	@Setter
	private Set<Senseur> senseurs;
	
	public void setId(ObjectId id){
		this.id = id.toString();
	}
	
	public ObjectId getId(){
		return id==null?null:new ObjectId(id);
	}
}
