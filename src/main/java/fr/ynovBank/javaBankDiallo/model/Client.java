package fr.ynovBank.javaBankDiallo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientID;
	private String nom;
	private String prenom;
	private String passwd;
	private String login;
	
	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
	private List<Compte> comptes;
	
	public String toString() {
		String result = "ID : "+clientID+", Nom : "+nom+", Prénom : "+prenom+", Login : "+login;
		/*for (Compte c : comptes) {
			result += c.toString();
		}*/
		return result;
	}
	
	public Client() {
		
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
}
