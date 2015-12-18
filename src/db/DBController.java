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
    private static String password = "chaoss";
    private static String QRY = null;
    private static Connection conn = null;
    private static Statement stat = null;
    private static ResultSet rs = null;
    
    
    public static void main (String[] args) {
        
        try {
            openConnection();
            
            populateEmployees();
            populateProducts();
            
            System.out.println(employees.get(0).getFullName());
            System.out.println(products.get(0).getName());
        }
        catch(Exception e) {
            System.out.println("Exception : ");
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        
        
        closeConnection();
        
    }//main
    
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
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDouble(9)
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
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        new Manufacturer("Manu")
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
    
    public static void createInvoice(Invoice invoice) {
        
        try {
            openConnection();
            stat = conn.createStatement();
            
            // Insert into invoice table
            QRY = "INSERT INTO INVOICE(COST) VALUES(" + invoice.getTotalCost() + ")";
            stat.executeUpdate(QRY);
            
            // Insert employeeId and productId into invoice junction table
            
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
