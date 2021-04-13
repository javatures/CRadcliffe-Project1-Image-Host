import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDao implements Dao<Report> {
    Connection server;
    
    public ReportDao(Connection server){
        this.server = server;
    }
    @Override
    public void create(Report e) {
        // TODO Auto-generated method stub
        String sql = "Insert into reports (type , reason , description , source , hid , response , appeal) values (? , ? , ? , ? , ? , ? , ?)";
        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setBoolean(1, e.type);
            statement.setInt(2, e.Reason);
            statement.setString(3, e.description);
            statement.setInt(4, e.source);
            statement.setInt(5, e.handler);
            statement.setString(6, e.response);
            statement.setBoolean(7, e.appeal);
            statement.execute();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }

        
    }

    @Override
    public Report retrieve(Report e) {
        // TODO Auto-generated method stub
        String sql = "Select * from reports where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, e.id);
            ResultSet results = statement.executeQuery();
            if(results.next()){
                Report report = new Report(results.getInt("id"), results.getBoolean("type"), results.getInt("reason"), 
                                            results.getString("description"), results.getInt("source") ,
                                            results.getInt("hid"), results.getString("response") , results.getBoolean("appeal"));
                return report;
            }
            

        } catch (SQLException e1) {
            //TODO: handle exception
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Report e) {
        // TODO Auto-generated method stub
        String sql = "update reports set (type = ? , reason = ?, description = ? , source = ? , hid = ? , response = ?, appeal) where id = ?";

        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setBoolean(1, e.type);
            statement.setInt(2, e.Reason);
            statement.setString(3, e.description);
            statement.setInt(4, e.source);
            statement.setInt(5, e.handler);
            statement.setString(6, e.response);
            statement.setBoolean(7, e.appeal);
            statement.setInt(8, e.id);
            statement.execute();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from reports where id = ?";
        try {
            PreparedStatement statement = server.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        
    }

    @Override
    public List<Report> selectAll() {
        // TODO Auto-generated method stub
        try {
            PreparedStatement statement = server.prepareStatement("Select * from reports");
            ResultSet results = statement.executeQuery();
            ArrayList<Report> reports = new ArrayList<Report>();

            while(results.next()){
                reports.add(new Report(results.getInt("id"), results.getBoolean("type"), results.getInt("reason"), 
                                        results.getString("description"), results.getInt("source"), results.getInt("hid"), 
                                        results.getString("response"), results.getBoolean("appeal")));
            }
            return reports;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    
}
