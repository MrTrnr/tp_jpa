package appli.bancaire;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import appli.bancaire.entities.Adresse;
import appli.bancaire.entities.Banque;
import appli.bancaire.entities.Client;
import appli.bancaire.entities.Compte;

public class TestAppliBancaire {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-pu");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		// ajouter des banques dans la bdd
		tx.begin();

		// idée de refactoring : utiliser une liste des données et une boucle foreach
		Banque banqueNouveau = new Banque();
		banqueNouveau.setId(1);
		banqueNouveau.setNom("BNP");

		em.persist(banqueNouveau);

		Banque banqueNouveau2 = new Banque();
		banqueNouveau2.setId(2);
		banqueNouveau2.setNom("Société Générale");

		em.persist(banqueNouveau2);

		Banque banqueNouveau3 = new Banque();
		banqueNouveau3.setId(3);
		banqueNouveau3.setNom("Crédit Mutuel");

		em.persist(banqueNouveau3);

		tx.commit();

		// ajouter des clients dans la bdd
		tx.begin();

		Client clientNouveau = new Client();
		clientNouveau.setId(1);
		clientNouveau.setNom("Verne");
		clientNouveau.setPrenom("Jules");

		LocalDate dateDeNaissance = LocalDate.of(1828, 2, 8);
		clientNouveau.setDateNaissance(dateDeNaissance);

		Integer idBanque = 3;
		Banque uneBanque = em.find(Banque.class, idBanque);
		clientNouveau.setBanque(uneBanque);

		Adresse adresseClientNouveau = new Adresse();
		adresseClientNouveau.setNumero(5);
		adresseClientNouveau.setRue("Cours Olivier de Clisson");
		adresseClientNouveau.setCodePostal(44000);
		adresseClientNouveau.setVille("Nantes");
		clientNouveau.setAdresse(adresseClientNouveau);

		em.persist(clientNouveau);

		Client clientNouveau2 = new Client();
		clientNouveau2.setId(2);
		clientNouveau2.setNom("Mion");
		clientNouveau2.setPrenom("Jessica");

		LocalDate dateDeNaissance2 = LocalDate.of(1993, 10, 19);
		clientNouveau2.setDateNaissance(dateDeNaissance2);

		Integer idBanque2 = 1;
		Banque uneBanque2 = em.find(Banque.class, idBanque2);
		clientNouveau2.setBanque(uneBanque2);

		Adresse adresseClientNouveau2 = new Adresse();
		adresseClientNouveau2.setNumero(27);
		adresseClientNouveau2.setRue("Place du Commerce");
		adresseClientNouveau2.setCodePostal(49000);
		adresseClientNouveau2.setVille("Angers");
		clientNouveau2.setAdresse(adresseClientNouveau2);

		em.persist(clientNouveau2);

		tx.commit();

		// ajouter des comptes dans la bdd
		tx.begin();

		Compte compteNouveau = new Compte();
		compteNouveau.setId(1);
		compteNouveau.setNumero("369258147");
		compteNouveau.setSolde(200D);

		em.persist(compteNouveau);

		Compte compteNouveau2 = new Compte();
		compteNouveau2.setId(2);
		compteNouveau2.setNumero("2222");
		compteNouveau2.setSolde(-456.123D);

		em.persist(compteNouveau2);

		Compte compteNouveau3 = new Compte();
		compteNouveau3.setId(3);
		compteNouveau3.setNumero("33133133");
		compteNouveau3.setSolde(5624.12D);

		em.persist(compteNouveau3);

		Compte compteNouveau4 = new Compte();
		compteNouveau4.setId(4);
		compteNouveau4.setNumero("40404");
		compteNouveau4.setSolde(-45.16D);

		em.persist(compteNouveau4);

		tx.commit();

		em.close();

		emf.close();
	}

}
