package fr.ynovBank.javaBankDiallo.dao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class TransferManager {
	//private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
	/*public String getResults() {
	    return result;
	}*/
	
	public Map<String, String> getErrors() {
	    return errors;
	}
	
	public void checkTransfer(HttpServletRequest request) {
		String wording = request.getParameter("wording");
		double amount = Double.parseDouble(request.getParameter("amount"));
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
	
	    /*if (errors.isEmpty()) {
	        result = "<fmt:message key='login.error.success'/>";
	    } else {
	        result = "<fmt:message key='login.error.fail'/>";
	    }*/
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
		if (wording == null) {
	        throw new Exception("<fmt:message key='transfer.error.wording'/>");
	    }
	    else if (wording.trim().length()<3 || wording.trim().length()>30) {
	    	throw new Exception("<fmt:message key='transfer.error.wording2'/>");
	    }
	}
	
	private void checkAmount(double amount, int accountSenderID) throws Exception {
		double balance = ClientManager.getBalance(accountSenderID);
	    if (amount<=0) {
	        throw new Exception("<fmt:message key='transfer.error.amount'/>");
	    }
	    else if (balance-amount<-500) {
			throw new Exception("<fmt:message key='transfer.error.amount2'/>");
		}
	}
	
    private void setError(String field, String message) {
        errors.put(field, message);
    }

    /*private static String getValueField(HttpServletRequest request, String nameField) {
        String value = request.getParameter(nameField);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }*/
}
