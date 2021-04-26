import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("upload")
@MultipartConfig
public class Upload extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<form action=\"processupload\" method=\"post\"  enctype=\"multipart/form-data\" ><br/>"+
                                    "<label for=\"img\">Select an image to upload:</label>" +
                                    "<input type=\"file\" id=\"img\" name=\"img\" accept=\"image/*\">"+
                                    "Description:<input type=\"text\" name=\"description\" />" +
                                    "<input type=\"submit\"></form>");      
    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    
}
