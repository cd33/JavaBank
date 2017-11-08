package fr.ynovBank.javaBankDiallo.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.ynovBank.javaBankDiallo.model.Client;

@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
        String path = req.getRequestURI().substring( req.getContextPath().length() );
        if ( path.startsWith( "/CSS" ) ) {
            chain.doFilter( req, res );
            return;
        }
		
		HttpSession session = req.getSession(false);
		String loginURI = req.getContextPath() + "/login";
		String createAccount = req.getContextPath() + "/createAccount";
 
		boolean loggedIn = session != null && session.getAttribute("client") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
 
        if (loggedIn || loginRequest) {
        	if (loggedIn) {
	        	Client client = (Client) ((HttpServletRequest) request).getSession().getAttribute("client");
	            boolean account = !client.getAccounts().isEmpty();
	        	if (!account && !req.getServletPath().equals("/logout")) {
	        		if (req.getServletPath().equals("/createAccount")) {
	        			chain.doFilter(request, response);        			
	        		} else {
	        			res.sendRedirect(createAccount);
	        		}
	        	} else {
	            	chain.doFilter(request, response);
	            }
            } else {
            	chain.doFilter(request, response);
            }
        } else {
            res.sendRedirect(loginURI);
        }
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}