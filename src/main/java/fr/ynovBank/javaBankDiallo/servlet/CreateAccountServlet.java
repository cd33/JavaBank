package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.dao.TransferManager;
import fr.ynovBank.javaBankDiallo.model.Client;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wording = request.getParameter("wording");
		TransferManager account = new TransferManager();
		account.checkCreateAccount(request);
		request.setAttribute("account", account);
		if (account.getErrors().isEmpty()) {
			Client client = (Client) request.getSession().getAttribute("client");
			ClientManager.createAccount(client.getClientID(), wording);
			Client newclient;
			try {
				newclient = ClientManager.loginClient(client);
				request.getSession().setAttribute("client", newclient);
				response.sendRedirect(request.getContextPath()+"/accounts");
			} catch (Exception e) {
				request.setAttribute("accountFail", e.getMessage());
				this.getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
			}
		}
		else {
			this.getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
		}	
	}
}