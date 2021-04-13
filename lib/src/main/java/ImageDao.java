import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDao  implements Dao<Image>{
    Connection server;
    
    public ImageDao(Connection server) {
        this.server = server;
    }
    
    
    @Override
    public void create(Image e) {
        // TODO Auto-generated method stub
        String sql = "Insert into images (url , description , user , upvotes , downvotes) values (? , ? , ? , ? , ?)";
        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setString(1, e.url);
            statement.setString(2, e.description);
            statement.setInt(3, e.user);
            statement.setInt(4, e.upvotes);
            statement.setInt(5, e.downvotes);
            statement.execute();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }
        
    }

    @Override
    public Image retrieve(Image e) {
        // TODO Auto-generated method stub
        String sql = "Select * from images where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, e.id);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                Image image = new Image(results.getInt("id"), results.getString("url"), results.getString("description"), results.getInt("user"), results.getInt("upvotes") , results.getInt("downvotes"));

                return image;
            }
            

        } catch (SQLException e1) {
            //TODO: handle exception
        }
        return null;
    }

    @Override
    public void update(Image e) {
        String sql = "update images set (url = ? , description = ?, user = ? ,upvotes = ?, downvotes = ? ) where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setString(1, e.url);
            statement.setString(2, e.description);
            statement.setInt(3, e.user);
            statement.setInt(4, e.upvotes);
            statement.setInt(5, e.downvotes);
            statement.setInt(6 , e.id);
            statement.execute();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from comments where id = ?";
        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Finish to deal with user not found");

        }
        
    }

    @Override
    public List<Image> selectAll() {
        // TODO Auto-generated method stub
        try {
            PreparedStatement statement = server.prepareStatement("Select * from images");
            ResultSet results = statement.executeQuery();
            ArrayList<Image> images = new ArrayList<Image>();

            while(results.next()){
                images.add(new Image(results.getInt("id"), results.getString("url"), results.getString("description"), 
                                    results.getInt("user"), results.getInt("upvotes") , results.getInt("downvotes")));
            }
            return images;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;    
    }



}
