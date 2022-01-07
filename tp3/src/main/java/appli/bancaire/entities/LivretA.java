package appli.bancaire.entities;

import javax.persistence.Entity;

@Entity
public class LivretA extends Compte {

	private Double taux;

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
}
