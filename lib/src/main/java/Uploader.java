import java.io.IOException;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;



@WebServlet("uploader")
public class Uploader extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("uid"));
        
        try {
            UserDao server = new UserDao(SQLServer.getConnection());
            User user = server.retrieve(new User(id, null, null, false, 0));
            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(user));


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    
    
    
    }
    
    
}
