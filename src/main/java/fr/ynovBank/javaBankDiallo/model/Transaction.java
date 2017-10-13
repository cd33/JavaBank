package fr.ynovBank.javaBankDiallo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transacID;
	private String libelle;
	private Date date;
	private double montant;
	
	@ManyToOne
	@JoinColumn(name="numeroCompte")
	private Compte leCompte;
	
	
	public int getTransacID() {
		return transacID;
	}

	public void setTransacID(int transacID) {
		this.transacID = transacID;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return leCompte;
	}

	public void setCompte(Compte compte) {
		this.leCompte = compte;
	}

}
