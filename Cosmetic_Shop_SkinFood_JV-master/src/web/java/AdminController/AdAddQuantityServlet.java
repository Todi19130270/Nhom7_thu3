package web.java.AdminController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ImageDAO;
import web.java.dao.ProductDAO;
import web.java.model.Product;

/**
 * Servlet implementation class AdAddQuantityServlet
 */
@WebServlet("/admin/updateQtt")
public class AdAddQuantityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdAddQuantityServlet() {
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
	ProductDAO productDAO = new ProductDAO();
	String page = "";
	if(request.getParameter("page") == null) {
		page = "1";
	}else {
		page = request.getParameter("page");
	}
	
	List<Product> products = productDAO.getAllProductInPageHaveQtt(Integer.parseInt(page));
	int numberOfProduct = productDAO.countProduct();
	int numberOfPage = (int)Math.ceil(numberOfProduct/10) + 1;
	
	request.setAttribute("images", new ImageDAO().getAllImage());
	request.setAttribute("currentPage", Integer.parseInt(page));
	request.setAttribute("products", products);
	request.setAttribute("numberOfPage", numberOfPage);
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	
	request.getRequestDispatcher("../Admin/template/statistical/AddQtt.jsp").forward(request, response);
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
	ProductDAO productDAO = new ProductDAO();
	
	productDAO.updateQtt(request.getParameter("product"), request.getParameter("qtt"));
	
	String page = "";
	if(request.getParameter("page") == null) {
		page = "1";
	}else {
		page = request.getParameter("page");
	}
	
	int numberOfProduct = productDAO.countProduct();
	int numberOfPage = (int)Math.ceil(numberOfProduct/10) + 1;
	
	request.setAttribute("images", new ImageDAO().getAllImage());
	request.setAttribute("currentPage", Integer.parseInt(page));
	request.setAttribute("products", productDAO.getAllProductInPageHaveQtt(Integer.parseInt(page)));
	request.setAttribute("numberOfPage", numberOfPage);
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	
	request.getRequestDispatcher("../Admin/template/statistical/AddQtt.jsp").forward(request, response);
	
    }

}
