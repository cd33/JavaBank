package fr.ynovBank.javaBankDiallo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.persistence.TypedQuery;

//import java.sql.Statement;

import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Compte;
import fr.ynovBank.javaBankDiallo.model.Transaction;

public class ClientManager {
	
	private static String PERSISTENCE_UNIT_NAME = "javaBankDiallo";
	private static EntityManagerFactory factory;

	public static Client loadClientByID(int clientID) {
		Client result = new Client();
		
		try {
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/javabank","root","");
			
			//Statement stmt= con.createStatement();
			PreparedStatement stmt = con.prepareStatement("SELECT clientID, nom, prenom, login FROM client WHERE clientID=?");
			stmt.setInt(1, clientID);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				result.setClientID(rs.getInt("clientID"));
				result.setNom(rs.getString("nom"));
				result.setPrenom(rs.getString("prenom"));
				result.setLogin(rs.getString("login"));
				
				System.out.println("Clients trouvés : "+result.toString());
			}
			
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<Client> loadClients() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		//em.persist(client);
		em.getTransaction().begin();
		
		TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
		List<Client> listeClients = tQuery.getResultList();
		/*TypedQuery<Compte> tQueryC = em.createQuery("from Compte", Compte.class);
		List<Compte> listeCompte = tQueryC.getResultList();
		TypedQuery<Transaction> tQueryT = em.createQuery("from Transaction", Transaction.class);
		List<Transaction> listeTransaction = tQueryT.getResultList();

		*/
		for (Client c : listeClients)
		{
			System.out.println (c);
		}
		/*

		System.out.println("Nombre Client: "+listeClients.size());
		System.out.println("Nombre Compte: "+listeCompte.size());
		System.out.println("Nombre Transaction: "+listeTransaction.size());*/	
		em.close();
		factory.close();
		return listeClients;
	}
}
