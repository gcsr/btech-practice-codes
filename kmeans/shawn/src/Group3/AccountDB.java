/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group3;

import entity.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author
 */
public class AccountDB {

	public static int insert(Account account) {
		int result = 1; // success
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(account);
			em.getTransaction().commit();
			account.setId(account.getId());
			result = account.getId();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			result = 0;
		} finally {
			em.close();
		}

		return result;
	}
	
	public static Account findBy(Integer id) {
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Account.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long update(int accountId, long balance) {
		long result = 0; // failed
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Query q = em.createQuery("UPDATE Account a SET a.balance = :balance "
					+ "WHERE a.id = :id");
			q.setParameter("balance", balance);
			q.setParameter("id", accountId);
			result = q.executeUpdate();
			em.getTransaction().commit();
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
