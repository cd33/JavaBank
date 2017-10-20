package fr.ynovBank.javaBankDiallo;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;

public class TestJPA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientManager.createTransfer(1, 1, 0, 1, 100, "test");
	}

}
