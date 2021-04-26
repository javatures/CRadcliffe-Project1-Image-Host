import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/processing")
public class Processing extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("user");
        String password = req.getParameter("password");
        User newuser = new User(0, userName, password, false, 0);
        resp.setContentType("text/html");
        try {
            UserDao server = new UserDao(SQLServer.getConnection());
            server.create(newuser);
            newuser = server.retrieveN(newuser);
            server.server.close();
            resp.addCookie(new Cookie("user" , userName));
            resp.addCookie(new Cookie("admin" , "false"));
            resp.getWriter().println("<h1>Signup Complete</h1>");
            resp.getWriter().println("<h2>Press Continue to return to the main page</h2>");
            resp.getWriter().println("<a href=\"standarduser.html\">Continue</a>");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            resp.getWriter().println("<h1>Problem with Database.</h1>");
            resp.getWriter().println("<h2>Please Try again Later</h2>");
            resp.getWriter().println("<a href=\"index.html?check=yes\">Home</a>");
            e.printStackTrace();

        }
        
       
    }
    
}
