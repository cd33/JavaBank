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
import fr.ynovBank.javaBankDiallo.model.Account;
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
			client.setName("Diallo");
			client.setFirstname("Charles");
			// Account 1
			Account account = new Account();
			account.setWording("Compte courant");
			account.setClient(client);
			List<Account> AccountsList = new ArrayList<Account>();
			AccountsList.add(account);
			client.setAccounts(AccountsList);
			// Transaction 1
			Transaction transaction = new Transaction();
			transaction.setAccount(account);
			transaction.setWording("Salaire");
			transaction.setDate(new Date());
			transaction.setAmount(10000);
			account.setTransactions(new ArrayList<Transaction>());
			account.getTransactions().add(transaction);
			// Transaction 2
			Transaction transaction2 = new Transaction();
			transaction2.setAccount(account);
			transaction2.setWording("Loyer");
			transaction2.setDate(new Date());
			transaction2.setAmount(-5000);
			account.getTransactions().add(transaction2);
			// Account 2
			Account account2 = new Account();
			account2.setWording("Livret A");
			account2.setClient(client);
			AccountsList.add(account2);
			client.setAccounts(AccountsList);
			// Transaction 1
			Transaction transactionA = new Transaction();
			transactionA.setAccount(account2);
			transactionA.setWording("Epargne");
			transactionA.setDate(new Date());
			transactionA.setAmount(25000);
			account2.setTransactions(new ArrayList<Transaction>());
			account2.getTransactions().add(transactionA);
			
			// Client 2
			Client client2 = new Client();
			client2.setLogin("sd");
			client2.setPasswd("sd");
			client2.setName("Dial");
			client2.setFirstname("Sidi");
			// Account 1
			Account accountC2 = new Account();
			accountC2.setWording("Compte courant");
			accountC2.setClient(client2);
			List<Account> AccountsListC2 = new ArrayList<Account>();
			AccountsListC2.add(accountC2);
			client2.setAccounts(AccountsListC2);
			// Transaction 1
			Transaction transactionC2 = new Transaction();
			transactionC2.setAccount(accountC2);
			transactionC2.setWording("Salaire");
			transactionC2.setDate(new Date());
			transactionC2.setAmount(100000);
			accountC2.setTransactions(new ArrayList<Transaction>());
			accountC2.getTransactions().add(transactionC2);
			// Transaction 2
			Transaction transaction2C2 = new Transaction();
			transaction2C2.setAccount(accountC2);
			transaction2C2.setWording("Loyer");
			transaction2C2.setDate(new Date());
			transaction2C2.setAmount(-500);
			accountC2.getTransactions().add(transaction2C2);
			// Transaction 3
			Transaction transaction3C2 = new Transaction();
			transaction3C2.setAccount(accountC2);
			transaction3C2.setWording("Pension Familiale");
			transaction3C2.setDate(new Date());
			transaction3C2.setAmount(-10000);
			accountC2.getTransactions().add(transaction3C2);
			
			em.persist(client);
			em.persist(client2);
			em.getTransaction().commit();
			
			TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
			List<Client> clientsList = tQuery.getResultList();
			/*TypedQuery<Account> tQueryC = em.createQuery("from Account", Account.class);
			List<Account> AccountsList = tQueryC.getResultList();
			TypedQuery<Transaction> tQueryT = em.createQuery("from Transaction", Transaction.class);
			List<Transaction> transactionsList = tQueryT.getResultList();*/
	
			PersistenceUtil util = Persistence.getPersistenceUtil();
			for (Client c : clientsList)
			{
				logger.info(c.toString());
				logger.debug("is client loaded ? "+util.isLoaded(c));
				logger.debug("is client loaded ? "+util.isLoaded(c.getClientID()));
				Account co = c.getAccounts().get(0);
				logger.debug("are translations loaded ? "+util.isLoaded(co, "transactions"));
				co.getTransactions();
				logger.debug("are translations loaded now ? "+util.isLoaded(co, "transactions"));
				for(Transaction tran : co.getTransactions()) {
					logger.info(tran.toString());
				}
				//System.out.println (c);
			}
			logger.info("Size: "+ clientsList.size());
			
		} finally {
			em.close();
		}
		
	}
}
