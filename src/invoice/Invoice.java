package invoice;

import inventory.Product;
import hr.Employee;
import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Invoice {
    
    // <editor-fold desc="Fields">
    
    private int id;
    private double totalCost = 0;
    //private ArrayList<Product> products;
    //private ArrayList<Employee> employees;
    Product product;
    Employee employee;
    
    // </editor-fold>
    
    // <editor-fold desc="Constructors">
    
    public Invoice() {};
    /*
    public Invoice(int id, ArrayList<Product> products, ArrayList<Employee> employees) {
        System.out.println("Flag9");
        this.id = id;
        // Add products included in the invoice
        for (Product p : products) {
            this.products.add(p);
            System.out.println("Flag7");
        }
        
        // Add employees responsible for the invoice
        for (Employee e : employees) {
            this.employees.add(e);
            System.out.println("Flag8");
        }
        
        // Calculate total price
        for (Product p : this.products) {
            totalCost += p.getCost();
        }
    }
*/
    public Invoice(int id, Product product, Employee employee) {
        System.out.println("Flag9");
        this.id = id;
        
        this.product = product;
        this.employee = employee;
        
        // Calculate total price
 
            totalCost += product.getCost();
        System.out.println("Flag10");
  
    }
    //return id as a string
    public String nameString()
    {
        return "" + getId();
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Methods">
    
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    
    public double getTotalCost() { return this.totalCost; }
    public void setTotalCost(double cost) { this.totalCost = cost; }
    
   //public ArrayList<Product> getProducts() { return this.products; }
    //public void setProducts(ArrayList<Product> products) { this.products = products; }
    public Product getProducts() { return this.product; }
    public void setProducts(Product product) { this.product = product; }
    
    public Employee getEmployeess() { return this.employee; }
    public void setEmployees(Employee employees) { this.employee = employees; }
    
    // </editor-fold>
    
}
