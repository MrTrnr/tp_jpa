package appli.bancaire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Compte {

	@Id
	private Integer id;

	private String numero;

	private Double solde;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

}
