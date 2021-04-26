import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServer {
    private static String url = "jdbc:postgresql://localhost:5432/imagehost";
    private static String user = "imagehost";
    private static String pass = "M3m3s";

    public static Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection(url, user, pass);
        
    }
    
}
