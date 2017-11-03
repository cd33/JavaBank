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
	private String wording;
	private Date date;
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="accountNumber")
	private Account account;
	
	
	public int getTransacID() {
		return transacID;
	}

	public void setTransacID(int transacID) {
		this.transacID = transacID;
	}

	public String getWording() {
		return wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
