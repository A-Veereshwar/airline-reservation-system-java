package airlinemanagementsystem;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Conn {
    
    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/airlinemanagementsystem";
    private static final String USER = "postgres";
    private static final String PASSWORD = "veeresh124";
    
    public Conn() {
       try{
           Class.forName("org.postgresql.Driver");
           connection = DriverManager.getConnection(URL, USER, PASSWORD);
       } catch (ClassNotFoundException e){
           System.out.println(e.getMessage());
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }
    
    public PreparedStatement prepareStatement(String query) throws SQLException{
        if(connection == null){
            throw new SQLException("Database Failed to connect");
        }
        return connection.prepareStatement(query);
    }
}
