package web.java.controller.Ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.java.dao.CouponDAO;
import web.java.dao.TransportDAO;
import web.java.model.Cart;

/**
 * Servlet implementation class Coupon
 */
@WebServlet("/Coupon")
public class Coupon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Coupon() {
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
	response.setContentType("text/html;charset=UTF-8");
	Double transportAmount = new TransportDAO().getTransportFeeById(request.getParameter("transport"));
	Double discountAmount = new CouponDAO().getCouponDiscountAmount(request.getParameter("magiamgia"));
	
	
	HttpSession session = request.getSession();
	Cart cart = (Cart) session.getAttribute("cart");
	Double totalDiscount = cart.getTotalDiscount(transportAmount, discountAmount);

	String totalString = String.format("%1$,.0f", totalDiscount);

	PrintWriter out = response.getWriter();
	if(discountAmount == 0) {
	    out.println("" + totalString);
	}else {
	    //1000
	    out.println(totalString);	    
	}
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
