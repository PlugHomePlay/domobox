package fr.infini.domobox.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@SequenceGenerator(name = "TDC_SEQ", sequenceName = "SEQ_TDC", allocationSize = 1)
public class TypeDeCapteur {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TDC_SEQ")
	@Column(name="tc_uid")
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String libelle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
}
