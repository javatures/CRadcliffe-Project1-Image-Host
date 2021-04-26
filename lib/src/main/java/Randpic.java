import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;



@WebServlet("randpic")
public class Randpic extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Random rand = new Random();
        


        try {
            ImageDao server = new ImageDao(SQLServer.getConnection());
            ArrayList<Image> images = (ArrayList<Image>) server.selectAll();
            Image image = images.get(rand.nextInt(images.size()));

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
