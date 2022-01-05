package dev;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entites.Livre;

public class TestJpa {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("livre-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Livre livre1 = em.find(Livre.class, 2);

		System.out.println("Titre : " + livre1.getTitre() + " Auteur : " + livre1.getAuteur());

		Livre livreNouveau = new Livre();
		livreNouveau.setId(10);
		livreNouveau.setTitre("Hôtel du Nord");
		livreNouveau.setAuteur("Georges Simenon");

		em.persist(livreNouveau);

		Livre livreAModifier = em.find(Livre.class, 5);
		livreAModifier.setTitre("Du plaisir dans la cuisine");

		TypedQuery<Livre> query1 = em.createQuery("select l from Livre l where l.titre='Germinal'", Livre.class);
		System.out.println("Auteur du Livre Germinal : " + query1.getSingleResult().getAuteur());

		TypedQuery<Livre> query2 = em.createQuery("select l from Livre l where l.auteur='Emile Zola'", Livre.class);
		System.out.println("Titre du livre d'Emile Zola : " + query2.getSingleResult().getTitre());

		tx.commit();

		em.close();

		emf.close();

	}

}
