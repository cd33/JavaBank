package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Account;


@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static Logger logger = LogManager.getLogger(InitJPA.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Client client = (Client) request.getSession().getAttribute("client");
		
		request.setAttribute("client", client);
		HashMap<Integer, Double> balanceAccount = new HashMap<Integer, Double>();
		
		if (client.getAccounts()!=null) {
			for (Account compte : client.getAccounts()) {
				double balance = ClientManager.getBalance(compte.getNumber());
				balanceAccount.put(compte.getNumber(), balance);
			}
			
			request.setAttribute("balanceAccount", balanceAccount);
			request.setAttribute("balanceAccountAvailable", ClientManager.getBalanceAvailable(client.getClientID()));
			this.getServletContext().getRequestDispatcher("/accounts.jsp").forward(request, response);
		}
		else {
			this.getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
		}
			
		/*PersistenceUtil util = Persistence.getPersistenceUtil();
		
		logger.debug("is client loaded ? "+util.isLoaded(client));
		logger.debug("is accounts loaded ? "+util.isLoaded(client.getAccounts()));
		client.getAccounts().size();
		logger.debug("N°2 : is accounts loaded ? "+util.isLoaded(client.getAccounts()));
		
		Client client = ClientManager.getClientByID(clientId);
		logger.info(client.toString());
		logger.debug("is client loaded ? "+util.isLoaded(client));
		
		List<Account> Accounts = client.getAccounts();*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
