import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("addcomment")
public class AddComment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equalsIgnoreCase("user")){
                String username = cookie.getValue();

                try {
                    UserDao userver = new UserDao(SQLServer.getConnection());
                    CommentDao cserver = new CommentDao(userver.server);
                    User user = userver.retrieveN(new User(0, username, null, false, 0));
                    cserver.create(new Comment(0,Integer.parseInt(req.getParameter("id")), user.id, req.getParameter("comment")));
                    resp.sendRedirect("standarduser.html");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                

            }
        }

    }
    
    
}
