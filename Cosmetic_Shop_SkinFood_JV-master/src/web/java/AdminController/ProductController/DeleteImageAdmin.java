package web.java.AdminController.ProductController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.ImageDAO;
import web.java.dao.ProductDAO;
import web.java.model.Product;

/**
 * Servlet implementation class DeleteImageAdmin
 */
@WebServlet("/admin/deleteImage")
public class DeleteImageAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImageAdmin() {
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
	
	String id = request.getParameter("id");
	ImageDAO imageDAO = new ImageDAO();
	String productId = imageDAO.findProductByImageId(id);
	Product product = new ProductDAO().getProductById(productId);
	imageDAO.deleteImageById(id);
	request.setAttribute("images", imageDAO.getAllImageById(productId));
	request.setAttribute("product", product);
	request.getRequestDispatcher("../Admin/template/management/AddImageAdmin.jsp").forward(request, response);
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
