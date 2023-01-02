package web.java.AdminController.ProductController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.ImageDAO;
import web.java.dao.ProductDAO;

/**
 * Servlet implementation class AddImageAdmin
 */
@WebServlet("/admin/addImageProduct")
public class AddImageAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddImageAdmin() {
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
	request.setAttribute("images", new ImageDAO().getAllImageById(id));
	request.setAttribute("product", new ProductDAO().getProductById(id));
	request.getRequestDispatcher("../Admin/template/management/AddImageAdmin.jsp").forward(request, response);
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
	String id = request.getParameter("id");
	String image = request.getParameter("image");
	ImageDAO imageDAO = new ImageDAO();
	imageDAO.addImageById(id, image);

	request.setAttribute("images", new ImageDAO().getAllImageById(id));
	request.setAttribute("product", new ProductDAO().getProductById(id));
	request.getRequestDispatcher("../Admin/template/management/AddImageAdmin.jsp").forward(request, response);
	;

    }

}
