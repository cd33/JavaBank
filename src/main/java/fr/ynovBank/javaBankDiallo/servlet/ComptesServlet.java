package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.ynovBank.javaBankDiallo.InitJPA;
import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Compte;


@WebServlet("/accounts")
public class ComptesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(InitJPA.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComptesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PersistenceUtil util = Persistence.getPersistenceUtil();
		
		Client client = (Client) request.getSession().getAttribute("client");
		
		/*logger.debug("is client loaded ? "+util.isLoaded(client));
		logger.debug("is comptes loaded ? "+util.isLoaded(client.getComptes()));
		client.getComptes().size();
		logger.debug("N°2 : is comptes loaded ? "+util.isLoaded(client.getComptes()));*/
		
		request.setAttribute("client", client);
		request.setAttribute("comptes", client.getComptes());
		
		//int clientId = Integer.parseInt(request.getParameter("id"));
		
		//Client client = ClientManager.getClientByID(clientId);
		/*logger.info(client.toString());
		logger.debug("is client loaded ? "+util.isLoaded(client));
		
		List<Compte> Accounts = client.getComptes();
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String result = gson.toJson(client);
		out.println(result);*/
		
		this.getServletContext().getRequestDispatcher("/accounts.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
