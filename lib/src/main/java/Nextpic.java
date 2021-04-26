import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;



@WebServlet("nextpic")
public class Nextpic extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("application/json");
        System.out.println("in nextpic with id=" + id);
        ObjectMapper mapper = new ObjectMapper();



        try {
            ImageDao server = new ImageDao(SQLServer.getConnection());
            Image image = server.retrieve(new Image(id - 1, null , null , 0 , 0 , 0));
            System.out.println(image.description);
            System.out.println(image.url);
            System.out.println(mapper.writeValueAsString(image));
            resp.getWriter().print(mapper.writeValueAsString(image));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        


    }
    
}
