package fr.ynovBank.javaBankDiallo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.ynovBank.javaBankDiallo.model.Client;

public class TransferManager {
	
	private Map<String, String> errors = new HashMap<String, String>();
	
	public Map<String, String> getErrors() {
	    return errors;
	}
	
	public void checkTransfer(HttpServletRequest request) {
		String wording = request.getParameter("wording");
		String amount = request.getParameter("amount");
		int accountSenderID = Integer.parseInt(request.getParameter("accountsFormControlSelect"));
	    
	    try {
	    	checkWording(wording);
	    } catch (Exception e) {
	        setError("wording", e.getMessage());
	    }
	
	    try {
	        checkAmount(amount, accountSenderID);
	    } catch (Exception e) {
	        setError("amount", e.getMessage());
	    }
	}
	
	public void checkCreateAccount(HttpServletRequest request) {
		String wording = request.getParameter("wording");
	    
	    try {
	    	checkWording(wording);
	    } catch (Exception e) {
	        setError("wording", e.getMessage());
	    }
	}

	private void checkWording(String wording) throws Exception {
		if (wording.isEmpty()) {
	        throw new Exception("wording");
	    }
	    else if (wording.length()<3 || wording.length()>30) {
	    	throw new Exception("wording2");
	    }
	}
	
	private void checkAmount(String amount, int accountSenderID) throws Exception {
		double balance = ClientManager.getBalance(accountSenderID);
		if (amount.isEmpty()) {
	        throw new Exception("amount");
	    }
		Double amountDouble = Double.parseDouble(amount);
	    if (amountDouble<=0) {
	        throw new Exception("amount");
	    }
	    else if (balance-amountDouble<-500) {
			throw new Exception("amount2");
		}
	}
	
    private void setError(String field, String message) {
        errors.put(field, message);
    }
    
    public static void TransferServlet(HttpServletRequest request) {
    	List<Client> clients = ClientManager.getClients();
		request.setAttribute("clients", clients);
		
		for (Client client2 : clients) {
			client2.getAccounts();
		}
    }
}
