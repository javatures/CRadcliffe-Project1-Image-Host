import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Authenticating");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDao dao = new UserDao(SQLServer.getConnection());
            String user = req.getParameter("user");
            String password = req.getParameter("password");

            User check = dao.retrieveN(new User(0, user, password, false, 0));
            if (check != null) {
                if (check.userName.equals(user)) {
                    if (check.password.equals(password)) {
                        resp.setContentType("text/html");
                        resp.getWriter()
                                .println("<h1>Login Successful!</h1>" + "<h2>Press Continue to complete login.</h2>"
                                        + "<a href=\"standarduser.html\" >Continue</a>");
                                        resp.addCookie(new Cookie("user" , check.userName));
                                        resp.addCookie(new Cookie("admin" , (String.valueOf(check.pLevel))));
                        return;
                    }

                }
            }
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Login Unsuccessful<h1>");
            resp.getWriter()
                    .println("<form action=\"login\" > " + "<input type=\"submit\" value=\"Try Again\" /> </form>");
            resp.getWriter()
                    .println("<form action=\"signup\">" + "<input type=\"submit\" value=\"Sign Up\" />" + "</form>");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            resp.getWriter().println("<h1>Problem Connecting to Database. Please try again</h1>");
            e.printStackTrace();
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Login Unsuccessful<h1>");
            resp.getWriter()
                    .println("<form action=\"login\" > " + "<input type=\"submit\" value=\"Try Again\" /> </form>");
            resp.getWriter()
                    .println("<form action=\"signup\">" + "<input type=\"submit\" value=\"Sign Up\" />" + "</form>" +
                    "<form action=\".\" method=\"post\" >"
                    + "<input type=\"submit\" value=\"Main Page\" ></form>");

        }

    }

}
