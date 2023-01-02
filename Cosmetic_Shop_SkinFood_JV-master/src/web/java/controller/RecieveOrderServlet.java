package web.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.CollectionDAO;
import web.java.dao.EventDAO;
import web.java.dao.Order;

/**
 * Servlet implementation class RecieveOrderServlet
 */
@WebServlet("/recieveOrder")
public class RecieveOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecieveOrderServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("utf-8");

	int loginId = -1;
	Cookie[] cookies = request.getCookies();
	for (int i = 0; i < cookies.length; i++) {
	    if ((cookies[i].getName()).compareTo("loginId") == 0) {
		loginId = Integer.parseInt(cookies[i].getValue());
	    }
	}
	System.out.print(loginId);
	new Order().changeStatusOrder(request.getParameter("recieve"), -1);

	request.setAttribute("historyTransactions", new Order().getAllHistoryTransactionById(loginId));
	request.setAttribute("historySingleTransactions", new Order().getAllOrderSingleById(loginId));
	request.setAttribute("saleMakeups", new CategoryDAO().getMakeupCate());
	request.setAttribute("events", new EventDAO().getAllEvent());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("collections", new CollectionDAO().getAllCollection());
	
	
	response.sendRedirect("history");
    }

}
