package web.java.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.UserDAO;
import web.java.model.User;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    /**
     * Default constructor.
     */
    public AdminFilter() {

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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	// TODO Auto-generated method stub
	// place your code here

	// pass the request along the filter chain
	HttpServletResponse response1 = (HttpServletResponse) response;

	HttpServletRequest request1 = (HttpServletRequest) request;
	Cookie[] cookies = request1.getCookies();

	User user = new User();
	if (cookies != null) {
	    for (Cookie ck : cookies) {
		if ("loginId".equals(ck.getName())) {
		    user = new UserDAO().getUserById(ck.getValue());
		    request.setAttribute("userLogin", user);
		}
	    }
	}
	if (user.getRole() == 1) {
	    chain.doFilter(request, response);
	}else {
	    request1.getRequestDispatcher("/Warning.jsp").forward(request1, response1);	    
	}
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
