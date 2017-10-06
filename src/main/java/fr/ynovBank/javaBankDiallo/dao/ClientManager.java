package fr.ynovBank.javaBankDiallo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

import fr.ynovBank.javaBankDiallo.model.Client;

public class ClientManager {

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
}
