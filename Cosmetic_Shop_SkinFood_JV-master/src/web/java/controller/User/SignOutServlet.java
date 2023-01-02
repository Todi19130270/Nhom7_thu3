package web.java.controller.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignOutServlet
 */
@WebServlet("/signout")
public class SignOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public SignOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        Cookie loginId = new Cookie("loginId", "1");
        Cookie loginName = new Cookie("loginName", "1");
        loginId.setMaxAge(0);
        loginName.setMaxAge(0);
        response.addCookie(loginId);
        response.addCookie(loginName);

        HttpSession session = request.getSession();
        session.removeAttribute("cart");
        request.getRequestDispatcher("/home").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
