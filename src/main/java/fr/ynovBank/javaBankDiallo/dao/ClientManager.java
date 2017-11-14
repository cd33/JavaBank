package fr.ynovBank.javaBankDiallo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//import java.sql.Statement;

import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Account;
import fr.ynovBank.javaBankDiallo.model.Transaction;
import fr.ynovBank.javaBankDiallo.dao.FactorySingleton;;

public class ClientManager {

	public static Client getClientByID(int clientID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Client client = em.find(Client.class, clientID);
		
		em.close();
		return client;
	}
	
	public static List<Client> getClients() {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
		List<Client> clients = tQuery.getResultList();
		
		em.close();
		return clients;
	}
	
	public static Account getAccountByID(int accountID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Account account = em.find(Account.class, accountID);
		
		em.close();
		return account;
	}
	
	public static List<Account> getAccounts() {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Account> tQueryC = em.createQuery("from Account", Account.class);
		List<Account> accounts = tQueryC.getResultList();
		
		em.close();
		return accounts;
	}
	
	public static double getBalance(int accountID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
	
		Account account = getAccountByID(accountID);
		List<Transaction> transactions = account.getTransactions();
		
		double balance=0;
		for (Transaction t : transactions) {
			balance += t.getAmount();
		}
		
		em.close();
		return balance;
	}
	
	public static double getBalanceAvailable(int clientID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Account> tQuery = em.createQuery("from Account where clientID = "+clientID+"", Account.class);
		List<Account> accounts = tQuery.getResultList();
		
		double balance=0;
		for (Account a : accounts) {
			balance += getBalance(a.getNumber());
		}
		
		em.close();
		return balance;
	}
	
	public static void createTransfer(int accountSenderID, int accountReceiverID, double amount, String wording) {
		
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		// Sender
		Account accountSender = getAccountByID(accountSenderID);
		
		// Sender Transaction
		Transaction transactionSender = new Transaction();
		transactionSender.setAccount(accountSender);
		transactionSender.setWording(wording);
		transactionSender.setDate(new Date());
		transactionSender.setAmount(-amount);
		accountSender.setTransactions(new ArrayList<Transaction>());
		accountSender.getTransactions().add(transactionSender);
		
		// Receiver
		Account accountReceiver = getAccountByID(accountReceiverID);

		// Receiver Transaction
		Transaction transactionReceiver = new Transaction();
		transactionReceiver.setAccount(accountReceiver);
		transactionReceiver.setWording(wording);
		transactionReceiver.setDate(new Date());
		transactionReceiver.setAmount(amount);
		accountReceiver.setTransactions(new ArrayList<Transaction>());
		accountReceiver.getTransactions().add(transactionReceiver);
		
		em.persist(transactionSender);
		if (transactionSender!=transactionReceiver) {
			em.persist(transactionReceiver);
		}
		em.getTransaction().commit();
		em.close();
		
	}
	
	public static List<Account> getOtherAccount(int clientID, int account) {
		
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Client client = ClientManager.getClientByID(clientID);
		List<Account> accounts = client.getAccounts();
		Account a = accounts.get(account);
		TypedQuery<Account> tQueryC = em.createQuery("from Account where numero != " +a.getNumber()+ "", Account.class);
		List<Account> otherAccounts = tQueryC.getResultList();
		
		em.close();
		return otherAccounts;
	}
	
	/*public static Client loginClient(String login, String passwd) {
		
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Client client = new Client();
		
		try {
			TypedQuery<Client> tQuery = em.createQuery("from Client where login='"+login+"' and passwd='"+passwd+"'", Client.class);
			client = tQuery.getSingleResult();
		}
		catch (Exception e) {
			client = null;
		}
		
		em.close();
		return client;
	}*/
	
	public static Client loginClient(Client client) throws Exception {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		try {
			TypedQuery<Client> tQuery = em.createQuery("from Client where login='"+client.getLogin()+"' and passwd='"+client.getPasswd()+"'", Client.class);
			client = tQuery.getSingleResult();
		}
		catch (Exception e) {
			client = null;
			throw new Exception("accountFail");
		}
		
		em.close();
		return client;
	}
	
	public static void createAccount(int clientID, String wording) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Client client = getClientByID(clientID);
		
		Account account = new Account();
		account.setWording(wording);
		account.setClient(client);
		
		em.persist(account);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void updatePasswd(int clientID, String oldPasswd, String newPasswd) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Client client = getClientByID(clientID);
		
		boolean check = client.getPasswd().equals(oldPasswd);
		if (check) {
			client.setPasswd(newPasswd);
			em.merge(client);
			em.getTransaction().commit();
		}
		em.close();
	}
	
	public static void createClient(Client client) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		em.persist(client);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Client refresh(Client entity) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Client client = em.find(Client.class, entity.getClientID());
		
		em.getTransaction().commit();
		em.close();
		return client;
	}
}