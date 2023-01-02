package web.java.controller;

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

import web.java.dao.Order;
import web.java.dao.UserDAO;
import web.java.model.User;

/**
 * Servlet implementation class Test
 */
@WebServlet("/testemail")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String orderName = request.getParameter("orderName");
		String orderAddress = request.getParameter("orderAddress");
		String orderPhone = request.getParameter("orderPhone");
		String transport = request.getParameter("hidden_transport");
		String magiamgia = request.getParameter("hidden_coupon");
		String orderNote = request.getParameter("orderNote");
		String total = request.getParameter("hidden_total");
		String userId = request.getParameter("userLogin");
		User userLogin = new UserDAO().getUserById(userId);
		Order order = new Order();

		// start send email
		
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
		}
		);

		try {
		    MimeMessage message = new MimeMessage(sessionEmail);
		    message.setFrom(new InternetAddress(username));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userLogin.getEmail()));
		    message.setSubject("Dangu shop");
		    message.setText("Thank for your order, this is detail about your order");
		    Transport.send(message);
		} catch (MessagingException mex) {
		    mex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
