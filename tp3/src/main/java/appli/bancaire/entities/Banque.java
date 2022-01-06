package appli.bancaire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Banque {

	@Id
	private Integer id;

	private String nom;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
