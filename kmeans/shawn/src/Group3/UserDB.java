/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group3;

import entity.Account;
import entity.AccountType;
import entity.User;

import java.security.MessageDigest;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class UserDB {

	public static int insert(User user) {
		int result = 1; // success
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(user);
			em.getTransaction().commit();
			em.close();

			Account checkingAccount = new Account();
			checkingAccount.setType(AccountType.CHECKING.getValue());
			checkingAccount.setBalance(25);
			checkingAccount.setUser(user);
			if (AccountDB.insert(checkingAccount) > 0) {
				user.getAccountCollection().add(checkingAccount);
			}

			Account savingsAccount = new Account();
			savingsAccount.setType(AccountType.SAVINGS.getValue());
			savingsAccount.setBalance(0);
			savingsAccount.setUser(user);
			if (AccountDB.insert(savingsAccount) > 0) {
				user.getAccountCollection().add(savingsAccount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			result = 0;
		} finally {

		}

		return result;
	}

	public static List<User> findBy(String username, String password) {
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT u FROM User u "
				+ "WHERE u.username = :username AND u.password = :password")
				.setParameter("username", (Object) username)
				.setParameter("password", (Object) password);
		return (List<User>) query.getResultList();
	}

	public static User findBy(String username) {
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT u FROM User u "
				+ "WHERE u.username = :username")
				.setParameter("username", username);
		return (User) query.getSingleResult();
	}

	public static long update(String username, String newPassword) {
		long result = 0; // failed
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Query q = em.createQuery("UPDATE User u SET u.password = :password "
					+ "WHERE u.username = :username");
			q.setParameter("password", newPassword);
			q.setParameter("username", username);
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
	public static List<User> findUsers() {
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Toba4PU");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT u FROM User u ");
		return (List<User>) query.getResultList();
	}
	
	
}
