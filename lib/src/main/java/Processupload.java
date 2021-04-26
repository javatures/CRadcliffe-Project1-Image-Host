import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("processupload")
@MultipartConfig
public class Processupload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream image = req.getPart("img").getInputStream();
        String name =  req.getPart("img").getSubmittedFileName();
        File dir = new File("C:/Users/t_tow/Revature/Projects/Image Host/CRadcliffe-Project1-Image-Host/lib/src/main/webapp/images");
        if(!dir.exists()){
            dir.mkdirs();
        }
        
        
        
        
            String url = "images/" + name;
            String description = req.getParameter("description");
            if(req.getCookies() == null){
                System.out.println("no cookies sent");
            }
            for(Cookie cookie : req.getCookies()){
                System.out.println("[" + cookie.getName() + "]");
                if(cookie.getName().equalsIgnoreCase("user")){
                    System.out.println("user set attempting upload");
                    boolean success = copyImage(dir, image, name , 0);
                    if(success){
                        System.out.println("upload set attempting database save");
                    try {
                        UserDao userver = new UserDao(SQLServer.getConnection());
                        User user = userver.retrieveN(new User(0, cookie.getValue() , null, false, 0));
                        ImageDao server = new ImageDao(userver.server); 
                        server.create(new Image(0, url, description, user.id, 0, 0));
                        resp.getWriter().println( "<h1>Picture saved successfully!!</h1>" +
                                            "<a href=\"standarduser.html\" >Return </a>");
                        return;

                    } catch (SQLException e) {  
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            

        }
        resp.getWriter().println("<h1>Problem saving picture to databas. Please try again later</h1>" +
                                        "<a href=\"standarduser.html\" >Return</a>");




        
        

    }

    boolean copyImage(File dir, InputStream image, String name, int retry) {
        try {
            File file = new File(dir, name);
            Files.copy(image, file.toPath());
        } catch (FileAlreadyExistsException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
