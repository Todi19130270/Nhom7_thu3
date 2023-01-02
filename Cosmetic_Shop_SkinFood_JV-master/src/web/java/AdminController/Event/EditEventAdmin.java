package web.java.AdminController.Event;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.EventDAO;
import web.java.dao.ProductDAO;
import web.java.model.EventAndProduct;

/**
 * Servlet implementation class EditEventAdmin
 */
@WebServlet("/admin/eventEdit")
public class EditEventAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventAdmin() {
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
	request.setCharacterEncoding("utf-8");
	String idEvent = request.getParameter("id");
	
	//lay event and product tu id event
	List<EventAndProduct> EPs = new EventDAO().getEventProductByEventId(idEvent);

	//lay cai event do
	request.setAttribute("event", new EventDAO().getEventById(idEvent));
	request.setAttribute("EPs", EPs);
	//lay tat ca product
	request.setAttribute("productNotInEvents", new EventDAO().getProductNotInEvent(idEvent));
	request.setAttribute("products", new ProductDAO().getAllProduct());
	request.getRequestDispatcher("../Admin/template/management/EditEventAdmin.jsp").forward(request, response);
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
	
	String eventId = request.getParameter("eventId");
	String productId = request.getParameter("productId");
	EventDAO eventDAO = new EventDAO();
	eventDAO.addEventProduct(eventId, productId);
	
	List<EventAndProduct> EPs = new EventDAO().getEventProductByEventId(eventId);

	request.setAttribute("event", new EventDAO().getEventById(eventId));
	request.setAttribute("EPs", EPs);
	request.setAttribute("productNotInEvents", new EventDAO().getProductNotInEvent(eventId));
	request.setAttribute("products", new ProductDAO().getAllProduct());
	request.getRequestDispatcher("../Admin/template/management/EditEventAdmin.jsp").forward(request, response);
	
    }

}
