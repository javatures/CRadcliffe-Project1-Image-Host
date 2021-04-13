import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements Dao<Comment>{

    Connection server;
    
    public CommentDao(Connection server){
        this.server = server;
    }

    @Override
    public void create(Comment e) {
        // TODO Auto-generated method stub
        String sql = "Insert into comments ( iid , uid , comment) values (? , ? , ?)";
        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, e.iID);
            statement.setInt(2, e.uID);
            statement.setString(3, e.comment);
            statement.execute();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println("Set up User Name already Exists");

        }

    }

    @Override
    public Comment retrieve(Comment e) {
        String sql = "Select * from comments where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, e.id);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                Comment comment  = new Comment(results.getInt("id"), results.getInt("iid"), results.getInt("uid") , results.getString("comment"));
                return comment;
            }
            

        } catch (SQLException e1) {
            //TODO: handle exception
        }
        return null;
    }

    @Override
    public void update(Comment e) {
        // TODO Auto-generated method stub
        String sql = "update comments set (iid = ? , uid = ?, comment = ? ) where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, e.iID);
            statement.setInt(2, e.uID);
            statement.setString(3, e.comment);
            statement.setInt(4, e.id);
            statement.execute();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
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
            System.out.println("Finish to deal with comment not found");

        }
        
    }

    @Override
    public List<Comment> selectAll() {
        // TODO Auto-generated method stub
        try {
            PreparedStatement statement = server.prepareStatement("Select * from comments");
            ResultSet results = statement.executeQuery();
            ArrayList<Comment> comments = new ArrayList<Comment>();

            while(results.next()){
                comments.add(new Comment(results.getInt("id"), results.getInt("iid"), results.getInt("uid"), results.getString("comment")));
            }
            return comments;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
}
