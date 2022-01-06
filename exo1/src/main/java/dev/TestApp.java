package dev;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entites.Livre;

public class TestApp {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("livre-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		// trouver un livre par son ID
		Livre livre1 = em.find(Livre.class, 2);

		System.out.println("Titre : " + livre1.getTitre() + " Auteur : " + livre1.getAuteur());

		// ajouter un livre dans la bdd
		Livre livreNouveau = new Livre();
		livreNouveau.setId(11);
		livreNouveau.setTitre("Hôtel du Nord");
		livreNouveau.setAuteur("Georges Simenon");

		em.persist(livreNouveau);

		// modifier le Titre d'un livre en connaissant son ID
		// la requete find permet de persister l'objet
		Livre livreAModifier = em.find(Livre.class, 5);
		livreAModifier.setTitre("Du plaisir dans la cuisine");

		// requete JPQL extrayant un livre en fonction de son titre
		TypedQuery<Livre> query1 = em.createQuery("select l from Livre l where l.titre='Germinal'", Livre.class);
		System.out.println("Auteur du Livre Germinal : " + query1.getSingleResult().getAuteur());

		// requete JPQL extrayant un livre en fonction de son auteur
		TypedQuery<Livre> query2 = em.createQuery("select l from Livre l where l.auteur='Emile Zola'", Livre.class);
		System.out.println("Titre du livre d'Emile Zola : " + query2.getSingleResult().getTitre());

		// supprimer un livre
		Livre livreASupprimer = em.find(Livre.class, 3);
		if (livreASupprimer != null) {
			em.remove(livreASupprimer);
		}

		// récuperer tous les livres
		TypedQuery<Livre> query3 = em.createQuery("select l from Livre l", Livre.class);
		List<Livre> tousLesLivres = query3.getResultList();
		for (Livre li : tousLesLivres) {
			System.out.println("L'auteur du Livre " + li.getTitre() + " est : " + li.getAuteur());
		}

		tx.commit();

		em.close();

		emf.close();

	}

}
