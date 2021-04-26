import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("comments")
public class Comments extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("application/json");
        try {
            CommentDao server = new CommentDao(SQLServer.getConnection());
            ArrayList<Comment> comments = server.retrieveI(new Comment(0, id, 0, null));
            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().print("{\"comments\": [");
            boolean first = true;
            for(Comment comment : comments){
                if(first){
                    resp.getWriter().print(mapper.writeValueAsString(comment));
                    first = false;
                }else
                    resp.getWriter().print("," + mapper.writeValueAsString(comment));
            }
            resp.getWriter().print("]}");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        
    }
    
}
