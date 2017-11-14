package fr.ynovBank.javaBankDiallo.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ynovBank.javaBankDiallo.dao.ClientManager;
import fr.ynovBank.javaBankDiallo.dao.LoginManager;
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
    	long timestamp = new Date().getTime();
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("sessionExpired")) {
				if (timestamp > Long.parseLong(cookie.getValue())) {
					request.setAttribute("expiredMessage", "true");
				}
			}
		}
    	
    	this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginManager login = new LoginManager();
		Client clientTest = login.checkLogin(request);
		request.setAttribute("login", login);
		
		if (login.getErrors().isEmpty()) {
			Client client;
			try {
				client = ClientManager.loginClient(clientTest);
				long cookieDatecreated = request.getSession().getCreationTime()
						+ request.getSession().getMaxInactiveInterval() * 1000;
				String cookieDatecreatedSting = Long.toString(cookieDatecreated);

				Cookie cookie = new Cookie("sessionExpired", cookieDatecreatedSting);
				cookie.setMaxAge((request.getSession().getMaxInactiveInterval() * 2));
				response.addCookie(cookie);
				
				request.getSession().setAttribute("client", client);
				response.sendRedirect(request.getContextPath()+"/accounts");
			} catch (Exception e) {
				request.setAttribute("accountFail", e.getMessage());
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		else {
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

		/*if (client!=null) {
			long cookieDatecreated = request.getSession().getCreationTime()
					+ request.getSession().getMaxInactiveInterval() * 1000;
			String cookieDatecreatedSting = Long.toString(cookieDatecreated);

			Cookie cookie = new Cookie("sessionExpired", cookieDatecreatedSting);
			cookie.setMaxAge((request.getSession().getMaxInactiveInterval() * 2));
			response.addCookie(cookie);
			
			request.getSession().setAttribute("client", client);
			response.sendRedirect(request.getContextPath()+"/accounts");
		}
		else {			
			request.setAttribute("error", "true");
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}*/
	}

}
