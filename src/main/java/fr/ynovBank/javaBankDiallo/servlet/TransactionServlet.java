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

@WebServlet("/transactions/*")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURI();
		int index = Integer.parseInt(url.substring(url.lastIndexOf("/")+1));
		/*String url = request.getParameter( "id" );
		int index = Integer.parseInt(url);*/
		
		Client client = (Client) request.getSession().getAttribute("client");
		request.setAttribute("client", client);
		List<Compte> listeComptes = client.getComptes();
		request.setAttribute("Compte", listeComptes.get(index));
		request.setAttribute("listeTransactions", listeComptes.get(index).getTransactions());
		request.setAttribute("Solde", ClientManager.getBalance(index+1));
		
		/*PersistenceUtil util = Persistence.getPersistenceUtil();
		logger.debug("is client loaded ? "+util.isLoaded(client));
		logger.debug("is comptes loaded ? "+util.isLoaded(client.getComptes()));
		client.getComptes().size();
		logger.debug("N°2 : is comptes loaded ? "+util.isLoaded(client.getComptes()));*/
		this.getServletContext().getRequestDispatcher("/transactions.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
