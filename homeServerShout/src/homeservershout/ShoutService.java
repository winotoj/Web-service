/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeservershout;
//rJ0REU9OhD9cdvQg

import homeintshout.ShoutInterface;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author minx1
 */
public class ShoutService implements ShoutInterface{
    private static final String DB_PASS = "rJ0REU9OhD9cdvQg";
    private static final String DB_USER = "rpcshout";
    private static final String DB_URL = "jdbc:mysql://localhost/rpcshout";

    private static ArrayList<String> shoutList = new ArrayList<>();

    Connection conn;
    
    public ShoutService() throws IOException {
        try {
            System.out.println("ShoutService constructor executed");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exceptions in ShoutSevice contructor: " + ex.getMessage());
            // exception chaining
            throw new IOException("ShoutService creation failed", ex);
        }
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("ShoutSevice about to be garbage collected");
    }
    
    @Override
    public int addShout(String msg) throws IOException {
       try {
            String insertTableSQL = "INSERT INTO shouts VALUES (NULL, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, msg);
            preparedStatement.executeUpdate();
            return 0;
        } catch (SQLException ex) {
            throw new IOException("Database error in addShout()", ex);
        } 
    }

    @Override
    public String[] getAllshouts() throws IOException {
        try {
            ArrayList<String> result = new ArrayList<>();
            String sql = "SELECT * FROM shouts";
            Statement stmt = conn.createStatement();
            // try with resources - do NOT use close();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String msg = rs.getString("msg");
                    result.add(msg);
                }
            }
            return result.toArray(new String[0]);
        } catch (SQLException ex) {
            throw new IOException("Database error in getAllShouts()", ex);
        }
    }
    
}
