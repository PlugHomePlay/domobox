package fr.infini.domobox.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonProperty;

import fr.infini.domobox.utils.dao.Dto;

@Entity
@SequenceGenerator(name = "BOX_SEQ", sequenceName = "SEQ_BOX", allocationSize = 1)
public class Box implements Dto<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOX_SEQ")
	@Column(name="b_uid")
	@JsonProperty
	private Long id;
	
	@Column(nullable=false, unique=true)
	@JsonProperty
	private String nom;
	
	@JsonProperty
	private String description;
	
	public Box(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Box [id=" + id + ", nom=" + nom + ", description=" + description + "]";
	}
	
	
}
