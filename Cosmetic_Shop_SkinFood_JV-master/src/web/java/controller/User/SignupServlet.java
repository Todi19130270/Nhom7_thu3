package web.java.controller.User;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.TransportDAO;
import web.java.dao.UserDAO;
import web.java.model.CartItem;
import web.java.model.Product;
import web.java.utils.Security;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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

        request.getRequestDispatcher("Page/web/signup.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("username").isEmpty() == true || request.getParameter("password").isEmpty() == true
                || request.getParameter("email").isEmpty() == true
                || request.getParameter("fullname").isEmpty() == true) {
            request.setAttribute("mess", "Please fill all field");
            request.getRequestDispatcher("Page/web/signup.jsp").forward(request, response);
            return;
        } else {
            if (new UserDAO().getUserByUsername(request.getParameter("username")).getId() != 0) {
                request.setAttribute("mess", "Username exist");
                request.getRequestDispatcher("Page/web/signup.jsp").forward(request, response);
                return;
            }
            new UserDAO().signUp(request.getParameter("username"), Security.encoderStringToMD5(request.getParameter("password")),
                    request.getParameter("email"), request.getParameter("fullname"),  " ", true);
            request.setAttribute("mess", "Sign up successfull");

            // begin send email
            final String username = "dangushopjava@gmail.com";
            final String password = "dangushop123";
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            javax.mail.Session sessionEmail = javax.mail.Session.getInstance(properties, new Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(sessionEmail);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getParameter("email")));
                message.setSubject("Welcome to our shop");
                message.setContent("<div style=\"display: flex;\">\r\n" + "      <p>Thank you,</p>"
                        + "      <p style=\"font-style: italic; color: red\"> " + request.getParameter("fullname")
                        + "</p>\r\n" + "      <p>. Your username in our shop is: </p>\r\n"
                        + "      <p style=\"font-style: italic; color: red\"> " + request.getParameter("username")
                        + "</p>\r\n" + "      <p>, hope you have good experience in our shop</p>\r\n" + "\r\n"
                        + "    </div>", "text/html;charset=UTF-8");
                Transport.send(message);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
            // end email

            request.getRequestDispatcher("Page/web/signup.jsp").forward(request, response);
        }

    }

}
