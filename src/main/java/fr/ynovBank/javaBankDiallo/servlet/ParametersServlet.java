package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.dao.LoginManager;
import fr.ynovBank.javaBankDiallo.model.Client;

@WebServlet("/parameters")
public class ParametersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParametersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/parameters.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPasswd = request.getParameter("oldPasswd");
		String newPasswd = request.getParameter("newPasswd");
		LoginManager passwd = new LoginManager();
		passwd.checkUpdatePasswd(request);
		request.setAttribute("passwd", passwd);
		if (passwd.getErrors().isEmpty()) {
			Client client = (Client) request.getSession().getAttribute("client");
			ClientManager.updatePasswd(client.getClientID(), oldPasswd, newPasswd);
			response.sendRedirect(request.getContextPath()+"/accounts");
		}
		else {
			this.getServletContext().getRequestDispatcher("/parameters.jsp").forward(request, response);
		}
	}

}
