package dev;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dev.entities.Emprunt;
import dev.entities.Livre;

public class TestBibliotheque {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotheque-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		// trouver un emprunt et tous ses livres associés
		TypedQuery<Emprunt> unEmprunt = em.createQuery("select e from Emprunt e where e.id='2'", Emprunt.class);
		Set<Livre> listeDesLivresEmpruntes = unEmprunt.getSingleResult().getLivres();
		for (Livre li : listeDesLivresEmpruntes) {
			System.out.println("livre emprunté : " + li.getTitre());
		}
//		System.out.println("emprunt d'id 2 : " + unEmprunt.getSingleResult().getLivres());

//		Emprunt emprunt1 = em.find(Emprunt.class, 2);
//

		// requete JPQL extrayant un livre en fonction de son auteur
//		TypedQuery<Livre> query2 = em.createQuery("select l from Livre l where l.auteur='Emile Zola'", Livre.class);
//		System.out.println("Titre du livre d'Emile Zola : " + query2.getSingleResult().getTitre());
//
//		TypedQuery<Livre> query3 = em.createQuery("select l from Livre l", Livre.class);
//		List<Livre> tousLesLivres = query3.getResultList();
//		for (Livre li : tousLesLivres) {
//			System.out.println("L'auteur du Livre " + li.getTitre() + " est : " + li.getAuteur());
//		}
//		System.out.println("Titre : " + livre1.getTitre() + " Auteur : " + livre1.getAuteur());

		tx.commit();

		em.close();

		emf.close();

	}

}
