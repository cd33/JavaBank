package fr.ynovBank.javaBankDiallo.dao;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import fr.ynovBank.javaBankDiallo.model.Client;

public class LoginManager {

    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public Client checkLogin(HttpServletRequest request) {
		String login = request.getParameter("login");
		String passwd = request.getParameter("passwd");

        Client client = new Client();
        
        try {
        	checkLogin(login);
        } catch (Exception e) {
            setError("login", e.getMessage());
        }
        client.setLogin(login);

        try {
            checkPasswd(passwd);
        } catch (Exception e) {
            setError("passwd", e.getMessage());
        }
        client.setPasswd(passwd);

        return client;
    }
    
    public Client checkUpdatePasswd(HttpServletRequest request) {
		String oldPasswd = request.getParameter("oldPasswd");
		String newPasswd = request.getParameter("newPasswd");
		String newPasswd2 = request.getParameter("newPasswd2");

        Client client = new Client();

        try {
            checkPasswd(oldPasswd, newPasswd, newPasswd2);
        } catch (Exception e) {
            setError("passwd", e.getMessage());
        }
        client.setPasswd(newPasswd);

        return client;
    }

    public Client checkClient(HttpServletRequest request) {
		String name = request.getParameter("name");
		String firstname = request.getParameter("firstname");
		String login = request.getParameter("login");
		String passwd = request.getParameter("passwd");

        Client client = new Client();
        
        try {
        	checkName(name);
        } catch (Exception e) {
            setError("name", e.getMessage());
        }
        client.setName(name);
        
        try {
        	checkFirstname(firstname);
        } catch (Exception e) {
            setError("firstname", e.getMessage());
        }
        client.setFirstname(firstname);

        try {
        	checkLogin(login);
        } catch (Exception e) {
            setError("login", e.getMessage());
        }
        client.setLogin(login);

        try {
            checkPasswd(passwd);
        } catch (Exception e) {
            setError("passwd", e.getMessage());
        }
        client.setPasswd(passwd);

        return client;
    }
    
    private void checkName(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("name");
        }
    }
    
    private void checkFirstname(String firstname) throws Exception {
        if (firstname.isEmpty()) {
            throw new Exception("firstname");
        }
    }

    private void checkLogin(String login) throws Exception {
	    if (!login.isEmpty()) {
	    	if (login.length()<3) {
	    		throw new Exception("login");
	    	}
	    } else {
	        throw new Exception("login2");
	    }
	}

	private void checkPasswd(String passwd) throws Exception{
		if (!passwd.isEmpty()) {
	    	if (passwd.length()<8) {
	    		throw new Exception("passwd");
	    	}
	    } else {
	        throw new Exception("passwd2");
	    }
	}
	
	private void checkPasswd(String oldPasswd, String newPasswd, String newPasswd2) throws Exception{
		if (!oldPasswd.isEmpty() && !newPasswd.isEmpty() && !newPasswd2.isEmpty()) {
	    	if (oldPasswd.length()<8 || newPasswd.length()<8 || newPasswd2.length()<8) {
	    		throw new Exception("passwd");
	    	}
	    	if (!newPasswd.equals(newPasswd2)) {
	    		throw new Exception("passwd3");
	    	}
	    } else {
	        throw new Exception("passwd2");
	    }
	}

    private void setError(String field, String message) {
        errors.put(field, message);
    }
}

