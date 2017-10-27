package fr.ynovBank.javaBankDiallo.dao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.ynovBank.javaBankDiallo.model.Client;

public final class LoginManager {
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResults() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Client verifClient(HttpServletRequest request) {
        String login = getValueField(request, "login");
        String passwd = getValueField(request, "passwd");

        Client client = new Client();

        try {
            validationLogin(login);
        } catch (Exception e) {
            setError("login", e.getMessage());
        }
        client.setLogin(login);

        try {
            validationPasswd(passwd);
        } catch (Exception e) {
            setError("passwd", e.getMessage());
        }
        client.setPasswd( passwd );

        if (errors.isEmpty()) {
            result = "Succes de la connexion.";
        } else {
            result = "Echec de la connexion";
        }

        return client;
    }

    private void validationLogin(String login) throws Exception {
        if (login != null) {
            throw new Exception("Merci de saisir un login valide.");
        }
    }

    private void validationPasswd(String passwd) throws Exception {
        if (passwd == null) {
        	throw new Exception("Merci de saisir votre mot de passe.");
        }
    }

    private void setError(String field, String message) {
        errors.put(field, message);
    }

    private static String getValueField(HttpServletRequest request, String nameField) {
        String value = request.getParameter(nameField);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
}