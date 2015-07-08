package fr.infini.domobox.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@SequenceGenerator(name = "ROL_SEQ", sequenceName = "SEQ_ROL", allocationSize = 1)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ROL_SEQ")
	@Column(name="r_uid")
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String nom;

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

	
	
}
