package fr.ynovBank.javaBankDiallo;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;

public class TestJDBC {

	public static void main(String[] args) {
		ClientManager.loadClientByID(1);
	}
}
