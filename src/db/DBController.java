package db;
import java.sql.*;
import java.util.ArrayList;
import hr.CommissionEmployee.CommissionSalesEmployee;
import inventory.Manufacturer;
import inventory.Product;

/**
 *
 * @author Dan Masci - 200299037
 */
public class DBController {
 
    /**
     * Module Level Variables / Objects / Arrays
     */
    private ArrayList<CommissionSalesEmployee> employees = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private String DB_URL, userName, password, QRY;
    private Connection conn;
    private Statement stat;
    private ResultSet rs;
    
    public DBController() {       
        this.DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200299037";        
        this.userName = "gc200299037";
        this.password = "*6L9hwPA";
        this.conn = null;
        this.stat = null;
        this.rs = null;
    }//constructor
    public DBController(String userName, String password) {
        this.DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/" + userName;
        this.userName = userName;
        this.password = password;
        this.conn = null;
        this.stat = null;
        this.rs = null;        
    }//overloaded constructor#1
    public DBController(String DB_URL, String userName, String password) {
        this.DB_URL = DB_URL;
        this.userName = userName;
        this.password = password;
        this.conn = null;
        this.stat = null;
        this.rs = null;
    }//overloaded constructor#2
    
    /**
     * DB Connection
     */
    public void openConnection() throws SQLException {
        
        try {            
            this.conn = DriverManager.getConnection(this.DB_URL, this.userName, this.password);            
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();            
        }
    }//openConnection()
    /**
     * DB Disconnect
     */
    public void closeConnection() throws SQLException {
        try {
            if (this.conn != null)
                this.conn.close();
        }   
        catch (SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
    }//closeConnection()
    
    
    /***************************************
     *           DB READ Methods
     **************************************/    
    /**
     * 
     * @return 
     */
    public ArrayList<CommissionSalesEmployee> populateEmployees() {
        
        this.QRY = "SELECT * FROM JMEmployees";
        
        try {
            this.stat = this.conn.createStatement();
            this.rs = this.stat.executeQuery(QRY);
            while (rs.next()) {
                this.employees.add(new CommissionSalesEmployee(                        
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }//while            
        }//try
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
        return this.employees;
    }//populateEmployees()
    /**
     * 
     * @return
     */
    public ArrayList<Product> populateProducts() {
        
        this.QRY = "SELECT * FROM JMProducts";
        
        try {
            this.stat = this.conn.createStatement();
            this.rs = this.stat.executeQuery(QRY);
            while (rs.next()) {
                this.products.add(new Product(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        new Manufacturer(rs.getString(7))
                ));
            }//while            
        }//try
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
        return this.products;
    }//populateProducts()
    
    
    /***************************************
     *           DB WRITE Methods
     **************************************/
    
    
    
}//class DBController
