package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Account;

@WebServlet("/json")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JsonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Client client = ClientManager.getClientByID(1);
		
		List<Account> accounts = client.getAccounts();

		response.setContentType("application/json");
		
		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonWriterFactory writerFactory = Json.createWriterFactory(config);
		
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
				.add("clientID", client.getClientID())
				.add("name", client.getName())
				.add("firstname", client.getFirstname())
				.add("passwd", client.getPasswd())
				.add("login", client.getLogin());
		
		JsonObjectBuilder objectBuilderaccounts = Json.createObjectBuilder();
		for(Account account : accounts) { 
		    objectBuilderaccounts.add("number", account.getNumber())
		    					 .add("wording", account.getWording());
		}
		
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		
		arrayBuilder.add(objectBuilderaccounts);
		
		objectBuilder.add("accounts", arrayBuilder);
		
		PrintWriter writer = response.getWriter();
		JsonObject jsonObject = objectBuilder.build();
		writerFactory.createWriter(writer).write(jsonObject);
		String jsonString;
		jsonString = writer.toString();
		writer.println(jsonString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
