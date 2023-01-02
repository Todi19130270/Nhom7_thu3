package web.java.controller.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import web.java.dao.UserDAO;
import web.java.model.User;
import web.java.utils.Security;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("Page/web/login.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.isEmpty() == true || password.isEmpty() == true) {
            request.setAttribute("mess", "Username and password must not empty");
            request.getRequestDispatcher("Page/web/login.jsp").forward(request, response);
        } else {
            if (new UserDAO().login(username, Security.encoderStringToMD5(password)) == true) {
                Cookie userId = new Cookie("loginId", Integer.toString(new UserDAO().getUserByUsername(username).getId()));
                Cookie userName = new Cookie("loginName", new UserDAO().getUserByUsername(username).getUsername());
                userId.setMaxAge(60 * 60 * 24);
                userName.setMaxAge(60 * 60 * 24);
                response.addCookie(userId);
                response.addCookie(userName);

                HttpSession session = request.getSession();
                session.setAttribute("loginSession", new UserDAO().getUserById(Integer.toString(new UserDAO().getUserByUsername(username).getId())));
                response.sendRedirect("home");
            } else {
                request.setAttribute("mess", "Wrong username and password, please check again");
                request.getRequestDispatcher("Page/web/login.jsp").forward(request, response);
            }
        }
    }

}
