package dev;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dev.entites.Pizza;

public class CycleDeVie {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;

		emf = Persistence.createEntityManagerFactory("pizza-pu");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();
//		
//		Pizza p1 = new Pizza();
//		p1.setName("Regina");
//		
//		// p1 est "transient" => pas de lien entre p1 et la base de données
//		
//		em.persist(p1);
//		
//		// p1 devient "persistant"
//		
//		p1.setName("Reblochon");

//		Pizza p7 = em.find(Pizza.class, 7);
//		
//		// p7 étant issu d'une requête => Objet persistant
//		
//		p7.setName("Hawaïenne XL");
//		
//		em.remove(p7);

		Pizza p6 = em.find(Pizza.class, 6);

		// p6 est persistant / managé
		p6.setName("Pizza ananas");
		tx.commit();

		em.close();

		// après un em.close() ou em.clear() ou em.evict(...) ...
		// p6 est détaché
		p6.setName("Pizza ananas v3");

		EntityManager em2 = emf.createEntityManager();

		EntityTransaction tx2 = em2.getTransaction();

		tx2.begin();

		em2.merge(p6);
		
		// p6 va passer de "Détaché" à "Managé"
		
		p6.setName("Pizza ananas v3");
		
		tx2.commit();
		
		em2.close();

		emf.close();
	}

}
