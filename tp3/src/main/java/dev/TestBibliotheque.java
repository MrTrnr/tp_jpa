package dev;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dev.entities.Client;
import dev.entities.Emprunt;
import dev.entities.Livre;

public class TestBibliotheque {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotheque-pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		// extraire un emprunt et tous ses livres associés
		TypedQuery<Emprunt> unEmprunt = em.createQuery("select e from Emprunt e where e.id='2'", Emprunt.class);
		Set<Livre> listeDesLivresEmpruntes = unEmprunt.getSingleResult().getLivres();
		System.out.println("L'emprunt numéro 2 contient : ");
		for (Livre li : listeDesLivresEmpruntes) {
			System.out.println("livre emprunté : " + li.getTitre());
		}

		tx.commit();

		tx.begin();
		// extraire tous les emprunts d'un client donné
		TypedQuery<Client> unClient = em.createQuery("select c from Client c where c.id='3'", Client.class);
		Set<Emprunt> empruntsDUnClient = unClient.getSingleResult().getEmprunts();
		System.out.println("Le client numéro 3 a les emprunts suivants : ");
		for (Emprunt e : empruntsDUnClient) {
			System.out.println("emprunt n° : " + e.getId());
		}
		tx.commit();

		em.close();

		emf.close();

	}

}
