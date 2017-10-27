package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.model.Client;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String passwd = request.getParameter("passwd");
		
		Client client = ClientManager.loginClient(login, passwd);

		if (client!=null) {
			request.getSession().setAttribute("client", client);
			response.sendRedirect(request.getContextPath()+"/accounts");
		}
		else {
			request.getSession().setAttribute("client", null);
			request.setAttribute("error", "Echec de la connexion<br>Merci de saisir un login et mot de passe valide.");
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
