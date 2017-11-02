package fr.ynovBank.javaBankDiallo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//import java.sql.Statement;

import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Compte;
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
	
	public static Compte getCompteByID(int compteID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Compte compte = em.find(Compte.class, compteID);
		
		em.close();
		return compte;
	}
	
	public static List<Compte> getComptes() {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Compte> tQueryC = em.createQuery("from Compte", Compte.class);
		List<Compte> comptes = tQueryC.getResultList();
		
		em.close();
		return comptes;
	}
	
	public static double getBalance(int accountID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
	
		Compte account = getCompteByID(accountID);
		List<Transaction> transactions = account.getTransactions();
		
		double balance=0;
		for (Transaction t : transactions) {
			balance += t.getMontant();
		}
		
		em.close();
		return balance;
	}
	
	public static double getBalanceAvailable(int clientID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Compte> tQuery = em.createQuery("from Compte where clientID = "+clientID+"", Compte.class);
		List<Compte> accounts = tQuery.getResultList();
		
		double balance=0;
		for (Compte a : accounts) {
			balance += getBalance(a.getNumero());
		}
		
		em.close();
		return balance;
	}
	
	public static void createTransfer(int clientSenderID, int clientReceiverID, int compteSenderID, int compteReceiverID, double amount, String wording) {
		
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		
		em.getTransaction().begin();
		
		// Sender
		Client clientSender = em.find(Client.class, clientSenderID);		
		List<Compte> accountsSender = clientSender.getComptes();
		Compte accountSender = accountsSender.get(compteSenderID);
		
		// Sender Transaction
		Transaction transactionSender = new Transaction();
		transactionSender.setCompte(accountSender);
		transactionSender.setLibelle(wording);
		transactionSender.setDate(new Date());
		transactionSender.setMontant(-amount);
		accountSender.setTransactions(new ArrayList<Transaction>());
		accountSender.getTransactions().add(transactionSender);
		
		// Receiver
		List<Compte> accountsReceiver = null;
		Client clientReceiver = null;
		if (clientSenderID!=clientReceiverID) {
			clientReceiver = em.find(Client.class, clientReceiverID);		
			accountsReceiver = clientReceiver.getComptes();
		}
		else {
			accountsReceiver = accountsSender;
		}
		Compte accountReceiver = accountsReceiver.get(compteReceiverID);

		// Receiver Transaction
		Transaction transactionReceiver = new Transaction();
		transactionReceiver.setCompte(accountReceiver);
		transactionReceiver.setLibelle(wording);
		transactionReceiver.setDate(new Date());
		transactionReceiver.setMontant(amount);
		accountReceiver.setTransactions(new ArrayList<Transaction>());
		accountReceiver.getTransactions().add(transactionReceiver);
		
		em.persist(clientSender);
		if (clientSenderID!=clientReceiverID) {em.persist(clientReceiver);}
		em.getTransaction().commit();
		em.close();
		
	}
	
	public static List<Compte> getOtherAccount(int clientID, int account) {
		
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Client client = ClientManager.getClientByID(clientID);
		List<Compte> comptes = client.getComptes();
		Compte compte = comptes.get(account);
		TypedQuery<Compte> tQueryC = em.createQuery("from compte where numero != " +compte.getNumero()+ "", Compte.class);
		List<Compte> otherAccounts = tQueryC.getResultList();
		
		em.close();
		return otherAccounts;
	}
	
	public static Client loginClient(String login, String passwd) {
		
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
	}
	
}
