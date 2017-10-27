package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.model.Client;
import fr.ynovBank.javaBankDiallo.model.Compte;

@WebServlet("/transfer")
public class VirementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VirementsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Client client = (Client) request.getSession().getAttribute("client");
		request.setAttribute("client", client);
		request.setAttribute("accountSender", client.getComptes());
		/*List<Compte> accountReceiv = ClientManager.getComptes();
		accountReceiv.removeAll(comptesEme);
		request.setAttribute("accountReceiv", accountReceiv);
		List<Compte> comptesDes = ClientManager.getOtherAccount(clientID, account)*/
		//request.setAttribute("accountReceiv", ClientManager.getComptes());
		
		List<Client> clients = ClientManager.getClients();
		request.setAttribute("clients", clients);
		
		for (Client client2 : clients) {
			client2.getComptes();
		}
		
		this.getServletContext().getRequestDispatcher("/virements.jsp").forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
