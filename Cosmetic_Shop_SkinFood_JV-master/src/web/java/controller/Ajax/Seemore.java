package web.java.controller.Ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.EventDAO;
import web.java.model.Product;

/**
 * Servlet implementation class Seemore
 */
@WebServlet("/seemore")
public class Seemore extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Seemore() {
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
	PrintWriter out = response.getWriter();

	List<Product> products = new EventDAO().getProductInEvent(request.getParameter("start"));
	for (Product product : products) {
	    out.print("<div class=\"col-lg-2 mt-5 product_count_start\">\r\n"
	    	+ "					<a href=\"product_detail?id="+product.getId()+ "\"\r\n"
	    	+ "						class=\"product-sale__item\">\r\n"
	    	+ "						<div class=\"product-sale__item-img\"\r\n"
	    	+ "							style=\"padding-top: 4px; height: 220px\">\r\n"
	    	+ "							<img alt=\"\" src=\""+product.getFirstImage() +"\" style=\"width: 100%\" />\r\n"
	    	+ "						</div>\r\n"
	    	+ "						<p class=\"product-sale__item-name\">"+product.getTitle() +"</p>\r\n"
	    	+ "						<div class=\"product-sale__item-price\">\r\n"
	    	+ "							<span class=\"product-sale__item-price-curr\">"+ product.getFormatPriceStandard()+" đ</span>\r\n"
	    	+ "							<span class=\"product-sale__item-price-old\">"+ product.getFormatPriceDefault()+" đ</span>\r\n"
	    	+ "						</div>\r\n"
	    	+ "					</a>\r\n"
	    	+ "				</div>");
	}

    }

}
