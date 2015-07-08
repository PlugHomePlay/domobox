package fr.infini.domotique.siteroom.model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity("cartes")
public class Senseur {
	
	@Id
	private ObjectId id;

	private String nom;
	
	private String description;
	
	private LocalDate createdAt;
	
	private String picture;
	
	@Reference
	private Carte carte;
	
}
