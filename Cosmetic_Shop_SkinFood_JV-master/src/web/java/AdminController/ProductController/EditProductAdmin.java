package web.java.AdminController.ProductController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.CollectionDAO;
import web.java.dao.ProductDAO;
import web.java.model.Product;

/**
 * Servlet implementation class EditProductAdmin
 */
@WebServlet("/admin/editProduct")
public class EditProductAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductAdmin() {
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
	Product product = new ProductDAO().getProductById(id);

	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("utf-8");

	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("collections", new CollectionDAO().getAllCollection());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	request.setAttribute("product", product);

	request.getRequestDispatcher("../Admin/template/management/EditProductAdmin.jsp").forward(request, response);
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
	String message = "";
	String id = request.getParameter("id");
	String title = request.getParameter("title");
	String description = request.getParameter("description");
	String price = request.getParameter("price");
	String category = request.getParameter("category");
	String brand = request.getParameter("brand");
	String discount = request.getParameter("discount");
	String collection = (new CollectionDAO().getCollectionByCategory(category));
	if (title.isEmpty() == true || description.isEmpty() == true || price.isEmpty() == true) {
	    message = "Please fill all field !!!";
	    response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("utf-8");

	    request.setAttribute("product", new ProductDAO().getProductById(id));
	    request.setAttribute("categories", new CategoryDAO().getAllCategory());
	    request.setAttribute("collections", new CollectionDAO().getAllCollection());
	    request.setAttribute("brands", new BrandDAO().getAllBrand());
	    request.setAttribute("mess", message);
	    request.getRequestDispatcher("../Admin/template/management/EditProductAdmin.jsp").forward(request,
		    response);
	} else {
	    if (!price.matches("-?\\d+(\\.\\d+)?")) {

		message = "Price is number !!!";
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		request.setAttribute("product", new ProductDAO().getProductById(id));
		request.setAttribute("categories", new CategoryDAO().getAllCategory());
		request.setAttribute("collections", new CollectionDAO().getAllCollection());
		request.setAttribute("brands", new BrandDAO().getAllBrand());
		request.setAttribute("mess", message);
		request.getRequestDispatcher("../Admin/template/management/EditProductAdmin.jsp").forward(request,
			response);
	    } else {
		ProductDAO productDAO = new ProductDAO();
		productDAO.editProductById(id, title, description, price, category, collection, brand, discount);
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		message = "Succesfully!";

		request.setAttribute("product", new ProductDAO().getProductById(id));
		request.setAttribute("categories", new CategoryDAO().getAllCategory());
		request.setAttribute("collections", new CollectionDAO().getAllCollection());
		request.setAttribute("brands", new BrandDAO().getAllBrand());
		request.setAttribute("mess", message);
		request.getRequestDispatcher("../Admin/template/management/EditProductAdmin.jsp").forward(request,
			response);
	    }
	}
    }

}
