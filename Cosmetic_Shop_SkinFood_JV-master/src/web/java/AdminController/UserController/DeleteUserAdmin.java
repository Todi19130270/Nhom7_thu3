package web.java.AdminController.UserController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.UserDAO;
import web.java.model.User;

/**
 * Servlet implementation class DeleteUserAdmin
 */
@WebServlet("/admin/deleteUser")
public class DeleteUserAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserAdmin() {
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
	UserDAO userDAO = new UserDAO();

	String id = request.getParameter("id");
	userDAO.deleteUserById(id);
	String page = "";
	if (request.getParameter("page") == null) {
	    page = "1";
	} else {
	    page = request.getParameter("page");
	}

	List<User> users = userDAO.getAllUserInPage(Integer.parseInt(page));
	int numberOfUsers = userDAO.countUser();
	int numberOfPage = (int) Math.ceil(numberOfUsers / 10) + 1;

	request.setAttribute("currentPage", Integer.parseInt(page));
	request.setAttribute("users", users);
	request.setAttribute("numberOfPage", numberOfPage);

	request.getRequestDispatcher("../Admin/template/management/UserManagement.jsp").forward(request, response);

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
