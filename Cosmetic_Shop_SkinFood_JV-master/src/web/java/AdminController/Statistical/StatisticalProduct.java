package web.java.AdminController.Statistical;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ImageDAO;
import web.java.dao.ProductDAO;

/**
 * Servlet implementation class StatisticalProduct
 */
@WebServlet("/admin/stcalProduct")
public class StatisticalProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticalProduct() {
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
	
	request.setAttribute("images", new ImageDAO().getAllImage());
	request.setAttribute("products", new ProductDAO().getAllProductBestSeller());
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	request.getRequestDispatcher("../Admin/template/statistical/ProductStatistical.jsp").forward(request, response);
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
