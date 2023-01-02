package web.java.AdminController.ProductController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ProductDAO;
import web.java.model.Product;

/**
 * Servlet implementation class DeleteProductAdmin
 */
@WebServlet("/admin/deleteProduct")
public class DeleteProductAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductAdmin() {
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
	ProductDAO productDAO = new ProductDAO();
	productDAO.deleteProductById(id);

	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("utf-8");
	String page = "";
	if (request.getParameter("page") == null) {
	    page = "1";
	} else {
	    page = request.getParameter("page");
	}

	List<Product> products = productDAO.getAllProductInPage(Integer.parseInt(page));
	int numberOfProduct = productDAO.countProduct();
	int numberOfPage = (int) Math.ceil(numberOfProduct / 10) + 1;

	request.setAttribute("currentPage", Integer.parseInt(page));
	request.setAttribute("products", products);
	request.setAttribute("numberOfPage", numberOfPage);
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("brands", new BrandDAO().getAllBrand());

	request.getRequestDispatcher("../Admin/template/management/ProductManagement.jsp").forward(request, response);

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
