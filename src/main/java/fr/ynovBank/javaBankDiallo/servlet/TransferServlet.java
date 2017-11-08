package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.dao.TransferManager;
import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Account;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Client client = (Client) request.getSession().getAttribute("client");
		request.setAttribute("accountSender", client.getAccounts());
		/*List<Compte> accountReceiv = ClientManager.getComptes();
		accountReceiv.removeAll(comptesEme);
		request.setAttribute("accountReceiv", accountReceiv);
		List<Compte> comptesDes = ClientManager.getOtherAccount(clientID, account)*/
		//request.setAttribute("accountReceiv", ClientManager.getComptes());
		
		List<Client> clients = ClientManager.getClients();
		request.setAttribute("clients", clients);
		
		for (Client client2 : clients) {
			client2.getAccounts();
		}
		
		this.getServletContext().getRequestDispatcher("/transfer.jsp").forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountSenderID = Integer.parseInt(request.getParameter("accountsFormControlSelect"));
		int accountReceiverID = Integer.parseInt(request.getParameter("accountsFormControlSelect2"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String wording = request.getParameter("wording");

		TransferManager transfer = new TransferManager();
		transfer.checkTransfer(request);
		request.setAttribute("transfer", transfer);
		
		if (transfer.getErrors().isEmpty()) {
			ClientManager.createTransfer(accountSenderID, accountReceiverID, amount, wording);
			response.sendRedirect(request.getContextPath()+"/accounts");
		}
		else {
			this.getServletContext().getRequestDispatcher("/transfer.jsp").forward(request, response);
		}
	}

}
