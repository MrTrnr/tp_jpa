package appli.bancaire.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VIREMENT")
public class Virement extends Operation {
	private String beneficaire;

	public String getBeneficaire() {
		return beneficaire;
	}

	public void setBeneficaire(String beneficaire) {
		this.beneficaire = beneficaire;
	}

}
