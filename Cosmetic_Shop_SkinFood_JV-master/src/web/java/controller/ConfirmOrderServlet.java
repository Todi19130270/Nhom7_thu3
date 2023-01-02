package web.java.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mysql.cj.Session;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.CollectionDAO;
import web.java.dao.CouponDAO;
import web.java.dao.EventDAO;
import web.java.dao.Order;
import web.java.dao.TransportDAO;
import web.java.dao.UserDAO;
import web.java.model.Cart;
import web.java.model.CartItem;
import web.java.model.Product;
import web.java.model.User;
import web.java.utils.Signatures;

/**
 * Servlet implementation class ConfirmOrderServlet
 */
@MultipartConfig()
@WebServlet("/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrderServlet() {
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
        response.getWriter().append("Served at: ").append(request.getContextPath());
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
        String orderName = request.getParameter("orderName");
        String orderAddress = request.getParameter("orderAddress");
        String orderPhone = request.getParameter("orderPhone");
        String transport = request.getParameter("hidden_transport");
        String magiamgia = request.getParameter("hidden_coupon");
        String orderNote = request.getParameter("orderNote");
        String oldtotal = request.getParameter("oldtotal");
        String total = request.getParameter("hidden_total");
        String userId = request.getParameter("userLogin");
        User userLogin = new UserDAO().getUserById(userId);
        Order order = new Order();
        String uploadFolder = request.getServletContext().getRealPath("/uploads");
        Path pathUpload = Paths.get(uploadFolder);
        Part part = request.getPart("fileKey");
        Signatures signature = new Signatures();
        if (orderPhone.isEmpty() == true || orderAddress.isEmpty() == true || orderPhone.compareTo("Enter your phone") == 0) {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");

            Double transportFee = new TransportDAO().getDefaultTransport().getPrice();
            if (request.getParameter("transport") != null) {
                transportFee = new TransportDAO().getTransportFeeById(request.getParameter("transport"));
            }

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                response.sendRedirect("home");
                return;
            }

            request.setAttribute("message", "Please fill all information");
            request.setAttribute("totalOrder", cart.getTotal());
            request.setAttribute("totalFormat", String.format("%1$,.0f", cart.getTotal() + transportFee));

            request.setAttribute("getTotalFee", transportFee);
            request.setAttribute("saleMakeups", new CategoryDAO().getMakeupCate());
            request.setAttribute("saleProducts", new EventDAO().getProductInEvent("0"));
            request.setAttribute("events", new EventDAO().getAllEvent());
            request.setAttribute("brands", new BrandDAO().getAllBrand());
            request.setAttribute("categories", new CategoryDAO().getAllCategory());
            request.setAttribute("collections", new CollectionDAO().getAllCollection());
            request.setAttribute("transports", new TransportDAO().getAllTransport());
            request.getRequestDispatcher("/Page/previewOrder.jsp").forward(request, response);
            return;
        }

        String docFileKey = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String pathFileUpload = Paths.get(pathUpload.toString(), docFileKey).toString();
        part.write(pathFileUpload);
        FileInputStream inputStream = new FileInputStream(pathFileUpload);
        int ch;
        String privateKey = "";
        while ((ch = inputStream.read()) != -1) {
            privateKey += ((char) ch);
        }
        inputStream.close();
        boolean check = signature.check(privateKey, userLogin.getPublicKey());

        if (check) {

            // Get cart items
            ArrayList<CartItem> cartItems = ((Cart) request.getSession(false).getAttribute("cart")).getCartItems();

            Double transportFee = new TransportDAO().getTransportFeeById(transport);
            Double discountAmount = new CouponDAO().getCouponDiscountAmount(magiamgia);
            Double totalDeal = Double.parseDouble(total);
            Double totalOrder = (totalDeal + transportFee) * (100 - discountAmount) / 100;
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
            });

            // handle list product
            String StringProduct = "";

            for (CartItem x : cartItems) {
                StringProduct += " \r\n <tr style=\"border: 1px solid red\">\r\n"
                        + "        <td style=\"width: 20%; text-align: center; border: 1px solid red\">\r\n"
                        + "          <img style=\"width: 150px; border: 1px solid red\" src=\" "
                        + x.getProduct().getFirstImage() + " \" alt=\"\">\r\n" + "        </td>\r\n"
                        + "        <td style=\"width: 20%; text-align: center; border: 1px solid red\"> "
                        + x.getProduct().getTitle() + " </td>\r\n"
                        + "        <td style=\"width: 10%; text-align: center; border: 1px solid red\"> "
                        + x.getProduct().getFormatPriceDefault() + " </td>\r\n"
                        + "        <td style=\"width: 5%; text-align: center; border: 1px solid red\"> " + x.getNumber()
                        + " </td>\r\n" + "        <td style=\"width: 20%; text-align: center; border: 1px solid red\"> "
                        + x.getTotalSingleFormat() + "</td>\r\n" + "      </tr>\r\n";
            }

            // handle price

            String stringprice = "";

            if (magiamgia.isEmpty() == true) {
                stringprice += "\r\n\r\n <tr>\r\n"
                        + "        <td style=\"width: 20%; text-align: center;\">Information</td>\r\n"
                        + "        <td style=\"width: 20%; text-align: center; border: 1px solid brow\">"
                        + "          <p>Address: " + orderAddress + " </p>" + "          <p>Phone:" + orderPhone + " </p>"
                        + "          <p>Transport Unit: " + new TransportDAO().getTransportNameById(transport) + " </p>\r\n"
                        + "        </td></td>\r\n" + "        <td style=\"width: 20%; text-align: center;\"></td>\r\n"
                        + "        <td style=\"width: 10%; text-align: center;\"></td>\r\n"
                        + "        <td style=\"width: 30%; text-align: center; font-size: 20px; color: red;\">Total: "
                        + Product.getPriceStaticFormat(totalOrder.toString()) + " vnd </td>\r\n" + "\r\n"
                        + "      </tr> \r\n";
            } else {
                stringprice += "<tr>\r\n" + "        <td style=\"width: 20%; text-align: center;\">Information</td>\r\n"
                        + "        <td style=\"width: 20%; text-align: center; border: 1px solid brow\"> "
                        + "          <p>Address: " + orderAddress + " </p>" + "          <p>Phone: " + orderPhone + " </p>"
                        + "          <p>Transport Unit: " + new TransportDAO().getTransportNameById(transport) + " </p>"
                        + "        </td>" + "        <td style=\"width: 20%; text-align: center;\"></td>\r\n"
                        + "        <td style=\"width: 10%; text-align: center;\"></td>\r\n"
                        + "        <td style=\"width: 30%; text-align: center; font-size: 20px; color: red;\"><del>"
                        + Product.getPriceStaticFormat(oldtotal.toString()) + "</del> </td>\r\n" + "\r\n" + "      </tr>\r\n" + "      <tr>\r\n"
                        + "        <td style=\"width: 20%; text-align: center;\"></td>\r\n"
                        + "        <td style=\"width: 20%; text-align: center; border: 1px solid brow\"></td>"
                        + "        <td style=\"width: 20%; text-align: center;\"></td>\r\n"
                        + "        <td style=\"width: 10%; text-align: center;\"></td>\r\n"
                        + "        <td style=\"width: 30%; text-align: center; font-size: 20px; color: red;\">Total: "
                        + Product.getPriceStaticFormat(totalOrder.toString()) + " </td>\r\n" + "\r\n" + "      </tr>";
            }

            try {

                String emailDefault = request.getParameter("emailOrder").isEmpty() ? "thesoonafu@gmail.com" : request.getParameter("emailOrder");
                MimeMessage message = new MimeMessage(sessionEmail);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDefault));
                message.setSubject("Thank for your order");
                message.setContent("<h1 style=\"text-align: center; color: red\">This is detail about your deal</h1>\r\n"
                                + "    <table style=\"margin: auto; width: 80%; border: 1px solid red\">\r\n"
                                + "      <tr style=\"border: 1px solid red\">\r\n"
                                + "        <th style=\"width: 20%; text-align: center;\">Image</th>\r\n"
                                + "        <th style=\"width: 30%; text-align: center;\">Name</th>\r\n"
                                + "        <th style=\"width: 10%; text-align: center;\">Unit Price(VND)</th>\r\n"
                                + "        <th style=\"width: 5%; text-align: center;\">Number</th>\r\n"
                                + "        <th style=\"width: 20%; text-align: center;\">Price</th>\r\n" + "      </tr>\r\n"
                                + StringProduct + stringprice
                                + "    </table> \r\n <h1 style=\"text-align: center; color: red\">One again, thank for your order. Hope you have good experience with our shop</h1>",
                        "text/html;charset=UTF-8");
                Transport.send(message);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }

            // end send email

            order.addOrderTotal(cartItems, orderName, orderAddress, orderPhone, transport, magiamgia, orderNote, totalOrder,
                    userId);

            HttpSession session = request.getSession();
            session.removeAttribute("cart");

            response.sendRedirect("history");
        } else {
            request.setAttribute("message", "Sai khóa");
            request.getRequestDispatcher("/Page/previewOrder.jsp").forward(request, response);
            return;
        }
    }

}
