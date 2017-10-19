package fr.ynovBank.javaBankDiallo.dao;

import javax.persistence.EntityManager;

//import java.sql.Statement;

import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Compte;
import fr.ynovBank.javaBankDiallo.dao.FactorySingleton;;

public class ClientManager {

	public static Client getClientByID(int clientID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		
		em.getTransaction().begin();
		
		Client client = em.find(Client.class, clientID);
		
		em.close();
		return client;
	}
	
	public static Compte getCompteByID(int compteID) {
		EntityManager em = FactorySingleton.getInstance().createEntityManager();
		
		em.getTransaction().begin();
		
		Compte compte = em.find(Compte.class, compteID);
		
		em.close();
		return compte;
	}
	
}
