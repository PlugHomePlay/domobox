package fr.infini.domobox.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonProperty;

import fr.infini.domobox.utils.dao.Dto;
import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries({
	@NamedQuery(name="utilisateur.findByLogin", query="select u from Utilisateur u where u.identifiant = :login"),
	@NamedQuery(name="utilisateur.findByMail", query="select u from Utilisateur u where u.email = :email")
})
@SequenceGenerator(name = "USR_SEQ", sequenceName = "SEQ_USR", allocationSize = 1)
public class Utilisateur implements Dto<Long> {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USR_SEQ")
	@JsonProperty
	private Long id;
	
	@Getter
	@Setter
	@Column(nullable = false, unique = true)
	@JsonProperty
	private String identifiant;
	
	@Getter
	@Setter
	@Column(nullable = false)
	@JsonProperty
	private String password;
	
	@Getter
	@Setter
	@Column(nullable = false)
	@JsonProperty
	private String salt;
	
	@Getter
	@Setter
	@Column(nullable = false, unique = true)
	@JsonProperty
	private String email;
}
