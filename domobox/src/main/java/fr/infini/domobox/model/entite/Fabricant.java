package fr.infini.domobox.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.ToString;

import org.codehaus.jackson.annotate.JsonProperty;
import org.eclipse.persistence.annotations.UuidGenerator;


@Entity
@UuidGenerator(name="FAB_ID_GEN")
@ToString
public class Fabricant {

	@Id
	@GeneratedValue(generator="FAB_ID_GEN")
	@Column(name="f_uid", columnDefinition="do_identifiant_code_long not null")
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String nom;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}	
}
