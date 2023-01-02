package web.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.CollectionDAO;
import web.java.dao.EventDAO;
import web.java.dao.Order;
import web.java.dao.TransportDAO;
import web.java.model.Cart;

/**
 * Servlet implementation class PreviewOrder
 */
@WebServlet("/preview")
public class PreviewOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewOrder() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("utf-8");
	
	Double transportFee = new TransportDAO().getDefaultTransport().getPrice();
	if(request.getParameter("transport") != null) {
	    transportFee = new TransportDAO().getTransportFeeById(request.getParameter("transport"));
	}
	
	HttpSession session = request.getSession();
	Cart cart = (Cart) session.getAttribute("cart");
	if(cart == null) {
	    response.sendRedirect("home");
	    return;
	}
	request.setAttribute("totalOrder", cart.getTotal() );
	request.setAttribute("totalFormat", String.format("%1$,.0f", cart.getTotal()+transportFee));
	
	
	request.setAttribute("getTotalFee", transportFee);
	request.setAttribute("saleMakeups", new CategoryDAO().getMakeupCate());
	request.setAttribute("saleProducts", new EventDAO().getProductInEvent("0"));
	request.setAttribute("events", new EventDAO().getAllEvent());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("collections", new CollectionDAO().getAllCollection());
	request.setAttribute("transports", new TransportDAO().getAllTransport());
	request.getRequestDispatcher("/Page/previewOrder.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}
