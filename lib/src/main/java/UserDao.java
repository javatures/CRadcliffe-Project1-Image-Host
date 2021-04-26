import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {
    Connection server;
    
    public UserDao(Connection server){
        this.server = server;
    }

    @Override
    public void create(User e) {
        String sql = "Insert into users (userName , password , pLevel , restrictions) values (? , ? , ? , ?)";
        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setString(1, e.userName);
            statement.setString(2, e.password);
            statement.setBoolean(3, e.pLevel);
            statement.setInt(4, e.restrictions);
            statement.execute();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println("Set up User Name already Exists");

        }

        // TODO Auto-generated method stub
        
    }

    @Override
    public User retrieve(User e) {
        // TODO Auto-generated method stub
        String sql = "Select * from users where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, e.id);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                User user = new User(results.getInt("id"), results.getString("userName"), results.getString("password"), results.getBoolean("pLevel"), results.getInt("restrictions"));
                return user;
            }
            

        } catch (SQLException e1) {
            //TODO: handle exception
        }
        return null;

    }

    @Override
    public void update(User e) {
        // TODO Auto-generated method stub
        String sql = "update users set (userName = ? , password = ?, pLevel = ? , restrictions = ?) where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setString(1, e.userName);
            statement.setString(2, e.password);
            statement.setBoolean(3, e.pLevel);
            statement.setInt(4, e.restrictions);
            statement.setInt(5, e.id);
            statement.execute();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        

        
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from users where id = ?";
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
    public List<User> selectAll() {
        // TODO Auto-generated method stub
        
        try {
            PreparedStatement statement = server.prepareStatement("Select * from users");
            ResultSet results = statement.executeQuery();
            ArrayList<User> users = new ArrayList<User>();

            while(results.next()){
                users.add(new User(results.getInt("id"), results.getString("userName"), results.getString("password"), 
                                    results.getBoolean("pLevel"), results.getInt("restrictions")));
            }
            return users;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

        
    }

    public User retrieveN(User e) {
         // TODO Auto-generated method stub
         String sql = "Select * from users where userName = ?";

         try {
             PreparedStatement statement = server.prepareStatement(sql);
             statement.setString(1, e.userName);
             ResultSet results = statement.executeQuery();
             if(results.next()){
                 User user = new User(results.getInt("id"), results.getString("userName"), results.getString("password"), results.getBoolean("pLevel"), results.getInt("restrictions"));
                 return user;
             }
             
 
         } catch (SQLException e1) {
             //TODO: handle exception
         }
 
        return null;
    }


    
}
