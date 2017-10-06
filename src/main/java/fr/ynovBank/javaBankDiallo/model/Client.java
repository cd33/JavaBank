package fr.ynovBank.javaBankDiallo.model;

public class Client {
	
	private String nom;
	private String prenom;
	private int clientID;
	private String passwd;
	private String login;
	
	public String toString() {
		return "ID : "+clientID+", Nom : "+nom+", Prénom : "+prenom+", Login : "+login;
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
	
	

}
