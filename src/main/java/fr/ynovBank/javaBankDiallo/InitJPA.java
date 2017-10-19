package fr.ynovBank.javaBankDiallo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ynovBank.javaBankDiallo.dao.FactorySingleton;
import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Compte;
import fr.ynovBank.javaBankDiallo.model.Transaction;

public class InitJPA {

	//private static final 
	private static Logger logger = LogManager.getLogger(InitJPA.class);

	public static void main(String[] args) {
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().createEntityManager();
			
			em.getTransaction().begin();
			// Client 1
			Client client = new Client();
			client.setLogin("cd");
			client.setPasswd("cd");
			client.setNom("Diallo");
			client.setPrenom("Charles");
			// Compte 1
			Compte compte = new Compte();
			compte.setLibelle("Compte courant");
			compte.setClient(client);
			List<Compte> listeComptes = new ArrayList<Compte>();
			listeComptes.add(compte);
			client.setComptes(listeComptes);
			// Transaction 1
			Transaction transaction = new Transaction();
			transaction.setCompte(compte);
			transaction.setLibelle("Salaire");
			transaction.setDate(new Date());
			transaction.setMontant(10000);
			compte.setTransactions(new ArrayList<Transaction>());
			compte.getTransactions().add(transaction);
			// Transaction 2
			Transaction transaction2 = new Transaction();
			transaction2.setCompte(compte);
			transaction2.setLibelle("Loyer");
			transaction2.setDate(new Date());
			transaction2.setMontant(-5000);
			compte.getTransactions().add(transaction2);
			// Compte 2
			Compte compte2 = new Compte();
			compte2.setLibelle("Livret A");
			compte2.setClient(client);
			listeComptes.add(compte2);
			client.setComptes(listeComptes);
			// Transaction 1
			Transaction transactionA = new Transaction();
			transactionA.setCompte(compte2);
			transactionA.setLibelle("Epargne");
			transactionA.setDate(new Date());
			transactionA.setMontant(25000);
			compte2.setTransactions(new ArrayList<Transaction>());
			compte2.getTransactions().add(transactionA);
			
			// Client 2
			Client client2 = new Client();
			client2.setLogin("sd");
			client2.setPasswd("sd");
			client2.setNom("Dial");
			client2.setPrenom("Sidi");
			// Compte 1
			Compte compteC2 = new Compte();
			compteC2.setLibelle("Compte courant");
			compteC2.setClient(client2);
			List<Compte> listeComptesC2 = new ArrayList<Compte>();
			listeComptesC2.add(compteC2);
			client2.setComptes(listeComptesC2);
			// Transaction 1
			Transaction transactionC2 = new Transaction();
			transactionC2.setCompte(compteC2);
			transactionC2.setLibelle("Salaire");
			transactionC2.setDate(new Date());
			transactionC2.setMontant(100000);
			compteC2.setTransactions(new ArrayList<Transaction>());
			compteC2.getTransactions().add(transactionC2);
			// Transaction 2
			Transaction transaction2C2 = new Transaction();
			transaction2C2.setCompte(compteC2);
			transaction2C2.setLibelle("Loyer");
			transaction2C2.setDate(new Date());
			transaction2C2.setMontant(-500);
			compteC2.getTransactions().add(transaction2C2);
			// Transaction 3
			Transaction transaction3C2 = new Transaction();
			transaction3C2.setCompte(compteC2);
			transaction3C2.setLibelle("Pension Familiale");
			transaction3C2.setDate(new Date());
			transaction3C2.setMontant(-10000);
			compteC2.getTransactions().add(transaction3C2);
			
			em.persist(client);
			em.persist(client2);
			em.getTransaction().commit();
			
			TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
			List<Client> listeClients = tQuery.getResultList();
			/*TypedQuery<Compte> tQueryC = em.createQuery("from Compte", Compte.class);
			List<Compte> listeCompte = tQueryC.getResultList();
			TypedQuery<Transaction> tQueryT = em.createQuery("from Transaction", Transaction.class);
			List<Transaction> listeTransaction = tQueryT.getResultList();*/
	
			PersistenceUtil util = Persistence.getPersistenceUtil();
			for (Client c : listeClients)
			{
				logger.info(c.toString());
				logger.debug("is client loaded ? "+util.isLoaded(c));
				logger.debug("is client loaded ? "+util.isLoaded(c.getClientID()));
				Compte co = c.getComptes().get(0);
				logger.debug("are translations loaded ? "+util.isLoaded(co, "transactions"));
				co.getTransactions();
				logger.debug("are translations loaded now ? "+util.isLoaded(co, "transactions"));
				for(Transaction tran : co.getTransactions()) {
					logger.info(tran.toString());
				}
				//System.out.println (c);
			}
			logger.info("Size: "+ listeClients.size());
			
		} finally {
			em.close();
		}
		
	}
}
