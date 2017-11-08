package fr.ynovBank.javaBankDiallo.dao;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import fr.ynovBank.javaBankDiallo.model.Client;

public class LoginManager {
	
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResults() {
        return result;
    }

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

        if (errors.isEmpty()) {
            result = "<fmt:message key='login.error.success'/>";
        } else {
            result = "<fmt:message key='login.error.fail'/>";
        }

        return client;
    }

    public Client checkClient(HttpServletRequest request) {
		String name = request.getParameter("name");
		String firstname = request.getParameter("firstname");
		String login = request.getParameter("login");
		String passwd = request.getParameter("passwd");
        /*String login = getValueField(request, "login");
        String passwd = getValueField(request, "passwd");*/

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

        if (errors.isEmpty()) {
            result = "<fmt:message key='signup.error.success'/>";
        } else {
            result = "<fmt:message key='signup.error.fail'/>";
        }

        return client;
    }
    
    private void checkName(String name) throws Exception {
        if (name == null) {
            throw new Exception("<fmt:message key='login.error.name'/>");
        }
    }
    
    private void checkFirstname(String firstname) throws Exception {
        if (firstname == null) {
            throw new Exception("<fmt:message key='login.error.firstname'/>");
        }
    }

    private void checkLogin(String login) throws Exception {
	    if (login!=null) {
	    	if (login.trim().length()<3) {
	    		throw new Exception("<fmt:message key='login.error.login'/>");
	    	}
	    } else {
	        throw new Exception("<fmt:message key='login.error.login2'/>");
	    }
	}

	private void checkPasswd(String passwd) throws Exception{
		if (passwd!=null) {
	    	if (passwd.trim().length()<8) {
	    		throw new Exception("<fmt:message key='login.error.passwd'/>");
	    	}
	    } else {
	        throw new Exception("<fmt:message key='login.error.passwd2'/>");
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

