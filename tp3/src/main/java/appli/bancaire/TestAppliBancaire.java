package appli.bancaire;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import appli.bancaire.entities.Adresse;
import appli.bancaire.entities.Banque;
import appli.bancaire.entities.Client;
import appli.bancaire.entities.Compte;
import appli.bancaire.entities.Operation;

public class TestAppliBancaire {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-pu");
		EntityManager em = emf.createEntityManager();

		// ajouter des banques dans la bdd
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Banque banqueNouveau = new Banque();
		banqueNouveau.setNom("BNP");

		em.persist(banqueNouveau);

		Banque banqueNouveau2 = new Banque();
		banqueNouveau2.setNom("Société Générale");

		em.persist(banqueNouveau2);

		Banque banqueNouveau3 = new Banque();
		banqueNouveau3.setNom("Crédit Mutuel");

		em.persist(banqueNouveau3);

		tx.commit();

		// ajouter des clients dans la bdd
		EntityTransaction tx2 = em.getTransaction();
		tx2.begin();

		Client clientNouveau = new Client();
		clientNouveau.setNom("Verne");
		clientNouveau.setPrenom("Jules");

//		LocalDate dateDeNaissance = LocalDate.of(1828, 2, 8);
//		clientNouveau.setDateNaissance(dateDeNaissance);

		clientNouveau.setDateNaissance(LocalDate.of(1828, 2, 8));

//		Integer idBanque = 3;
//		Banque uneBanque = em.find(Banque.class, idBanque);
//		clientNouveau.setBanque(uneBanque);

		clientNouveau.setBanque(em.find(Banque.class, 3));

		Adresse adresseClientNouveau = new Adresse();
		adresseClientNouveau.setNumero(5);
		adresseClientNouveau.setRue("Cours Olivier de Clisson");
		adresseClientNouveau.setCodePostal(44000);
		adresseClientNouveau.setVille("Nantes");
		clientNouveau.setAdresse(adresseClientNouveau);

		em.persist(clientNouveau);

		Client clientNouveau2 = new Client();
		clientNouveau2.setNom("Mion");
		clientNouveau2.setPrenom("Jessica");

		clientNouveau2.setDateNaissance(LocalDate.of(1993, 10, 19));

		clientNouveau2.setBanque(em.find(Banque.class, 1));

		Adresse adresseClientNouveau2 = new Adresse();
		adresseClientNouveau2.setNumero(27);
		adresseClientNouveau2.setRue("Place du Commerce");
		adresseClientNouveau2.setCodePostal(49000);
		adresseClientNouveau2.setVille("Angers");
		clientNouveau2.setAdresse(adresseClientNouveau2);

		em.persist(clientNouveau2);

		Client clientNouveau3 = new Client();
		clientNouveau3.setNom("Brioche");
		clientNouveau3.setPrenom("Martine");

		clientNouveau3.setDateNaissance(LocalDate.of(2001, 7, 3));

		clientNouveau3.setBanque(em.find(Banque.class, 2));

		Adresse adresseClientNouveau3 = new Adresse();
		adresseClientNouveau3.setNumero(17);
		adresseClientNouveau3.setRue("rue des Chouettes");
		adresseClientNouveau3.setCodePostal(56000);
		adresseClientNouveau3.setVille("Vannes");
		clientNouveau3.setAdresse(adresseClientNouveau3);

		em.persist(clientNouveau3);

		tx2.commit();

		// ajouter des comptes dans la bdd
		EntityTransaction tx3 = em.getTransaction();
		tx3.begin();

		Compte compteNouveau = new Compte();
		compteNouveau.setNumero("369258147");
		compteNouveau.setSolde(200D);

		em.persist(compteNouveau);

		Compte compteNouveau2 = new Compte();
		compteNouveau2.setNumero("2222");
		compteNouveau2.setSolde(-456.123D);

		em.persist(compteNouveau2);

		Compte compteNouveau3 = new Compte();
		compteNouveau3.setNumero("33133133");
		compteNouveau3.setSolde(5624.12D);

		em.persist(compteNouveau3);

		Compte compteNouveau4 = new Compte();
		compteNouveau4.setNumero("40404");
		compteNouveau4.setSolde(-45.16D);

		em.persist(compteNouveau4);

		tx3.commit();

		// ajouter des opérations dans la bdd
		EntityTransaction tx4 = em.getTransaction();
		tx4.begin();

		Operation operationNouveau = new Operation();

//		LocalDateTime dateOperation = LocalDateTime.of(2022, 02, 01, 22, 51, 17);
//		operationNouveau.setDate(dateOperation);

		operationNouveau.setDate(LocalDateTime.of(2022, 02, 01, 22, 51, 17));
		operationNouveau.setMontant(-23.29D);
		operationNouveau.setMotif("Frais bancaires semestriels");
		operationNouveau.setCompte(em.find(Compte.class, 3));

		em.persist(operationNouveau);

		Operation operationNouveau2 = new Operation();

		operationNouveau2.setDate(LocalDateTime.of(2019, 12, 25, 12, 42, 33));
		operationNouveau2.setMontant(1963D);
		operationNouveau2.setMotif("Salaire Décembre");
		operationNouveau2.setCompte(em.find(Compte.class, 1));

		em.persist(operationNouveau2);

		tx4.commit();

		// ajouter des relations comptes/clients
		// le client d'ID 2 possède les comptes d'ID 1 et d'ID 4
		EntityTransaction tx5 = em.getTransaction();
		tx5.begin();

		Set<Compte> comptesDUnClient = new HashSet<>();

		Client unClient = em.find(Client.class, 2);

//		Compte unCompte = em.find(Compte.class, 1);
//		Compte unAutreCompte = em.find(Compte.class, 4);
//
//		comptesDUnClient.add(unCompte);
//		comptesDUnClient.add(unAutreCompte);

		comptesDUnClient.add(em.find(Compte.class, 1));
		comptesDUnClient.add(em.find(Compte.class, 4));

		unClient.setComptes(comptesDUnClient);

		em.persist(unClient);

		tx5.commit();

		// ajouter un compte qui a 2 clients
		// le compte d'ID
		EntityTransaction tx6 = em.getTransaction();
		tx6.begin();
		tx6.commit();

		em.close();

		emf.close();
	}

}
