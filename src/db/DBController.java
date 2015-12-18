package db;
import java.sql.*;
import java.util.ArrayList;
import hr.CommissionEmployee.CommissionSalesEmployee;
import hr.Employee;
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
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Invoice> invoices = new ArrayList<>();
    private static final String DB_URL = "jdbc:mysql://localhost/javamart";
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
    
    // Data getters
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }
    
    public ArrayList<Invoice> getInvoice() {
        return invoices;
    }
    
    
    /***************************************
     *           DB READ Methods
     **************************************/    
    
    public static void populateEmployees() {
        
        try {
            openConnection();
            stat = conn.createStatement();
            QRY = "SELECT "
                    + "id, "
                    + "firstName, "
                    + "lastName, "
                    + "position, "
                    + "department, "
                    + "address, "
                    + "phone, "
                    + "sin, "
                    + "commissionRate\n"
                    + "FROM employees;";
            rs = stat.executeQuery(QRY);
            while (rs.next()) {
                employees.add(new CommissionSalesEmployee(    
                        rs.getInt(1),
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
            QRY = "SELECT "
                    + "prod.id \"product id\", "
                    + "prod.name, "
                    + "description, "
                    + "serialNumber, "
                    + "cost, "
                    + "price, "
                    + "availability, "
                    + "manu.name \"manufacturer\"\n"
                    + "FROM products prod\n"
                    + "INNER JOIN manufacturers manu\n"
                    + "ON prod.manId = manu.id;";
            rs = stat.executeQuery(QRY);
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        new Manufacturer(rs.getString(8))
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
