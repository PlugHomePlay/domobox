package fr.infini.domobox.model.entite;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.ToString;

import org.codehaus.jackson.annotate.JsonProperty;

import fr.infini.domobox.utils.dao.Dto;


@Entity
@SequenceGenerator(name = "CAP_SEQ", sequenceName = "SEQ_CAP", allocationSize = 1)
@ToString
public class Capteur implements Dto<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CAP_SEQ")
	@Column(name="c_uid")
	@JsonProperty
	private Long id;
	
	@Column(nullable=false)
	@JsonProperty
	private String nom;
	
	@JsonProperty
	private String description;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="f_uid", nullable=false)
	@JsonProperty
	private Fabricant fabricant;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="tc_uid", nullable=false)
	@JsonProperty
	private TypeDeCapteur typeDeCapteur;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="r_uid", nullable=false)
	@JsonProperty
	private Role role;

	@Column(name="sid", nullable=false)
	private String serial;
	
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

	public Fabricant getFabricant() {
		return fabricant;
	}

	public void setFabricant(Fabricant fabricant) {
		this.fabricant = fabricant;
	}

	public TypeDeCapteur getTypeDeCapteur() {
		return typeDeCapteur;
	}

	public void setTypeDeCapteur(TypeDeCapteur typeDeCapteur) {
		this.typeDeCapteur = typeDeCapteur;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	
	
	
}
