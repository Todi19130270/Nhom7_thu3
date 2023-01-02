package web.java.controller.User;

import web.java.dao.UserDAO;
import web.java.model.User;
import web.java.utils.Signatures;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@MultipartConfig()
@WebServlet(name = "change-key", value = "/change-key")
public class ChangeKeyServlet extends HttpServlet {
    Signatures signature = new Signatures();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginSession");
        String fileName = request.getParameter("fileName");

        UserDAO userDAO = new UserDAO();
        if (fileName != null) {
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
            User userTEmp = userDAO.getUserById(String.valueOf(user.getId()));
            userDAO.updateValidById(String.valueOf(userTEmp.getId()), false);

            user.setPublicKey(signature.base64Key(signature.getPublicKey().getEncoded()));
            userDAO.signUp(user.getUsername(), user.getPassword(), user.getEmail(), user.getFullname(), user.getPublicKey(), true);
            out.write(signature.base64Key(signature.getPrivateKey().getEncoded()));
            out.close();

        }
        request.getRequestDispatcher("/Page/ChangeKey.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadFolder = request.getServletContext().getRealPath("/uploads");
        Path pathUpload = Paths.get(uploadFolder);
        Part part = request.getPart("fileKey");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginSession");

        if(!Files.exists(pathUpload)) {
            Files.createDirectory(pathUpload);
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
        boolean check = signature.check(privateKey, user.getPublicKey());
        if (check) {
            response.sendRedirect("/change-key?fileName=privateKey.txt");
        } else {
            request.getRequestDispatcher("Page/web/login.jsp").forward(request, response);
        }
    }
}
