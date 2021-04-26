import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signup")
public class Signup extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        resp.getWriter().println("<h1>Welcome to the club</h1>" +
                                "<h2>We just need to have you fill this out</h2>"+
                                "<form action=\"processing\" method=\"post\" >" +
                                "User Name: <input id=\"1\" type=\"text\" name=\"user\" required /> <br/>"+
                                "Confirm User Name: <input id=\"2\" oninput=\"verifyName(this)\" type=\"text\" name=\"confirmname\" required/> <br/>" +
                                "Password: <input id=\"3\" type=\"password\" name=\"password\" required /> <br/>" +
                                "Confirm Password: <input id=\"4\" oninput=\"verifyPass(this)\" type=\"password\" name=\"confirmpassword\" required /><br/>"+
                                "<input type=\"submit\" value=\"Submit\" />"+
                                "<script src=\"signup.js\"></script>");
                                                  
    }
    
}
