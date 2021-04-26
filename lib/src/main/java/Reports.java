import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("reports")
public class Reports extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application.json");
        try {
            ReportDao server = new ReportDao(SQLServer.getConnection());
            List<Report> reports = server.selectAll();
            boolean first = true;
            ObjectMapper mapper = new ObjectMapper();
            boolean type = Boolean.parseBoolean(req.getParameter("type"));
            resp.getWriter().print("{\"reports\": [");
            for(Report report : reports){
                if(report.type == type){
                    if(report.response == "" | report.appeal){
                        if(first)
                            resp.getWriter().print(mapper.writeValueAsString(report));
                        else    
                            resp.getWriter().print("," + mapper.writeValueAsString(report));
                    }
                }
            }
            resp.getWriter().print("]}");
            

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    
    
    }
        
}
