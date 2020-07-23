/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group3;

import entity.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author 
 */
public class TransactionDB {
	
	public static long insert(Transaction transaction){
		long result = 1; // success
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(transaction);
			em.getTransaction().commit();
			result = transaction.getId();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			result = 0;
		} finally {
			em.close();
		}

		return result;
	}
}
