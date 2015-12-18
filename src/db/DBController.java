package db;
import java.sql.*;
import java.util.ArrayList;
import hr.CommissionEmployee.CommissionSalesEmployee;
import inventory.Manufacturer;
import inventory.Product;
import invoice.Invoice;

/**
 *
 * @author Dan Masci - 200299037
 */
public class DBController {
 
    /*
     * Module Level Variables / Objects / Arrays
     */
    private static ArrayList<CommissionSalesEmployee> employees = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Invoice> invoices = new ArrayList<>();
    private static String DB_URL = "jdbc:mysql://localhost/javamart";
    private static String userName = "root";
    private static String password = "root";
    private static String QRY = null;
    private static Connection conn = null;
    private static Statement stat = null;
    private static ResultSet rs = null;
    
    /**
     * DB Connection
     */
    public static void openConnection() {
        
        try {            
            conn = DriverManager.getConnection(DB_URL, userName, password);            
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();            
        }
    }
    
    /**
     * DB Disconnect
     */
    private static void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        }   
        catch (SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
    }
    
    
    /***************************************
     *           DB READ Methods
     **************************************/    
    
    public static void populateEmployees() {
        
        try {
            openConnection();
            stat = conn.createStatement();
            QRY = "SELECT * FROM Employees";
            rs = stat.executeQuery(QRY);
            while (rs.next()) {
                employees.add(new CommissionSalesEmployee(                        
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }           
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }
    
    public static void populateProducts() {

        try {
            openConnection();
            stat = conn.createStatement();
            QRY = "SELECT * FROM Products";
            rs = stat.executeQuery(QRY);
            while (rs.next()) {
                products.add(new Product(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        new Manufacturer(rs.getString(7))
                ));
            }            
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }
    
    public static void populateInvoices() {
        
        try {
            openConnection();
            stat = conn.createStatement();
            QRY = "SELECT * FROM Invoices";
            rs = stat.executeQuery(QRY);
            
            while(rs.next()) {
                
            }
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }
    
    
    
    /***************************************
     *           DB WRITE Methods
     **************************************/
    
    
    
}
