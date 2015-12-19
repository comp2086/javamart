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
    private static ArrayList<Manufacturer> manufacturers = new ArrayList<>();
    private static ArrayList<Invoice> invoices = new ArrayList<>();
    private static final String DB_URL = "jdbc:mysql://localhost/javamart";
    private static final String userName = "root";
    private static final String password = "chaoss";
    private static String QRY = null;
    private static Connection conn = null;
    private static Statement stat = null;
    private static ResultSet rs = null;
    
    /**
     * DB Connection
     */
    private static void openConnection() {        
        try {            
            if (conn == null || conn.isClosed())
                conn = DriverManager.getConnection(DB_URL, userName, password);    
            System.out.println("Connection has been opened");
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
            System.out.println("Connection has been closed");
        }   
        catch (SQLException error) {
            error.printStackTrace();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
    }
    
    // Data getters
    public static ArrayList<Employee> getEmployees() {
        return employees;
    }
    
    public static ArrayList<Product> getProducts() {
        return products;
    }
    
    public static ArrayList<Manufacturer> getManufacturers() {
        return manufacturers;
    }
    
    public static ArrayList<Invoice> getInvoice() {
        return invoices;
    }
    
    public static void populateLocal() {
        openConnection();
        populateEmployees();
        populateProducts();
        populateManufacturers();
        populateIds();
        closeConnection();
    }//populateLocal
    
    /***************************************
     *           DB READ Methods
     **************************************/    
    /**
     * This method is responsible for grabbing the latest id values
     * from our 3 tables
     */
    public static void populateIds() {
        int[] ids = new int[4];
        try {
            stat = conn.createStatement();
            QRY = "SELECT MAX(id) FROM employees UNION ALL "
                  + "SELECT MAX(id) FROM products UNION ALL "
                  + "SELECT MAX(id) FROM manufacturers UNION ALL "
                  + "SELECT MAX(id) FROM invoices";
            rs = stat.executeQuery(QRY);
            for (int i = 0; i < ids.length; i++) {
                rs.next();
                ids[i] = rs.getInt(1);
            }
            Service.initializeIds(ids[0], ids[1], ids[2], ids[3]);
            System.out.println("Latest IDs have been set for Service Class");
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(NullPointerException error) {            
            System.out.println("The records you are trying to access don't exist");
        }
        catch(Exception error) {
            error.printStackTrace();
        }        
    }
    
    public static void populateEmployees() {
        
        try {
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
                        rs.getInt(1),//id
                        rs.getString(2),//firstName
                        rs.getString(3),//lastName
                        rs.getString(4),//position
                        rs.getString(5),//department
                        rs.getString(6),//address
                        rs.getString(7),//phone
                        rs.getString(8),//sin
                        rs.getDouble(9)//commissionRate
                ));
            }        
            System.out.println("Employees ArayList has been populated");         
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(NullPointerException error) {            
            System.out.println("No Employees exist in the DB");
        }
        catch(Exception error) {
            error.printStackTrace();
        }
    }//populateEmployees
    
    public static void populateProducts() {

        try {
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
                        rs.getInt(1),//id
                        rs.getString(2),//name
                        rs.getString(3),//description
                        rs.getString(4),//serialNumber
                        rs.getString(5),//cost
                        rs.getString(6),//price
                        rs.getBoolean(7),//availability
                        new Manufacturer(rs.getString(8))//manufacturer
                ));
            }      
            System.out.println("Products ArayList has been populated");      
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(NullPointerException error) {
            System.out.println("No Products exist in the DB");
        }
        catch(Exception error) {
            error.printStackTrace();
        }
    }//populateProducts
    
    public static void populateManufacturers() {
        
        try {
            stat = conn.createStatement();
            QRY = "SELECT "
                    + "name "
                    + "FROM manufacturers";
            rs = stat.executeQuery(QRY);
            while (rs.next()) {
                manufacturers.add(new Manufacturer(
                        rs.getString(1)//name
                ));
            }
            System.out.println("Manufacturers ArrayList has been populated");
        }
        catch(SQLException error) {
            error.printStackTrace();
        }
        catch(NullPointerException error) {
            System.out.println("No Manufacturers exist in the DB");
        }
        catch(Exception error) {
            error.printStackTrace();
        }  
    }//populateManufacturers
    
    /***************************************
     *           DB WRITE Methods
     **************************************/
    
    public static void createEmployee(CommissionSalesEmployee employee) {
        
        try {
            openConnection();
            stat = conn.createStatement();            
            QRY = "INSERT INTO employees ("
                    + "firstName, "
                    + "lastName, "
                    + "position, "
                    + "department, "
                    + "address, "
                    + "phone, "
                    + "sin, "
                    + "commissionRate"
                    + ") VALUES ("
                    + employee.getFirstName() + ", "
                    + employee.getLastName() + ", "
                    + employee.getPosition() + ", "
                    + employee.getDepartment() + ", "
                    + employee.getAddress() + ", "
                    + employee.getPhone() + ", "
                    + employee.getSin() + ", "
                    + employee.getCommissionRate() + ")";                    
            stat.executeUpdate(QRY);    
            System.out.println("Employee has been created");        
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
    
    public static void createProduct(Product product) {
        
        try {
            openConnection();
            stat = conn.createStatement();            
            QRY = "INSERT INTO products "
                    + "VALUES("
                    + product.getName() + ", "
                    + product.getDescription() + ", "
                    + product.getSerialNumber() + ", "
                    + product.getCost() + ", "
                    + product.getPrice() + ", "
                    + product.getAvailability() 
                    + ");";
            stat.executeUpdate(QRY);   
            System.out.println("Product has been created");
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
    
    public static void createManufacturer(Manufacturer manufacturer) {
        
        try {
            openConnection();
            stat = conn.createStatement();            
            QRY = "INSERT INTO manufacturers " 
                    + "VALUES(" 
                    + manufacturer.getName() 
                    + ");";
            stat.executeUpdate(QRY);            
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
            QRY = "INSERT INTO invoices(cost) VALUES(" + invoice.getTotalCost() + ")";
            
            // Insert employeeId, productId and invoiceId into invoice junction table
            QRY = "INSERT INTO invoicesJunction(invoiceId, employeeId) VALUES(";     
            for(int i = 0; i < invoice.getEmployees().size(); i++) {
                
                QRY += invoice.getId() + ", ";
                QRY += invoice.getEmployees().get(i).getId() + ")";
                
                if(i < invoice.getEmployees().size() - 1) {
                    QRY += ",(";
                }
            }
            stat.executeUpdate(QRY);
            
            QRY = "INSERT INTO invoicesJunction(productId) VALUES("; 
            for(int i = 0; i < invoice.getProducts().size(); i++) {
                
                QRY += invoice.getProducts().get(i).getId() + ")";
                
                if(i < invoice.getProducts().size() - 1) {
                    QRY += ",(";
                }
                
            }
            QRY += " WHERE invoiceId = " + invoice.getId();
            stat.executeUpdate(QRY); 
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
    
    
    
    
}
