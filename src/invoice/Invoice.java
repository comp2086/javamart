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
    private ArrayList<Product> products;
    private ArrayList<Employee> employees;
    
    // </editor-fold>
    
    // <editor-fold desc="Constructors">
    
    public Invoice() {};
    
    public Invoice(ArrayList<Product> products, ArrayList<Employee> employees) {
        
        // Add products included in the invoice
        for (Product p : products) {
            this.products.add(p);
        }
        
        // Add employees responsible for the invoice
        for (Employee e : employees) {
            this.employees.add(e);
        }
        
        // Calculate total price
        for (Product p : this.products) {
            totalCost += p.getCost();
        }
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
    
    public ArrayList<Product> getProducts() { return this.products; }
    public void setProducts(ArrayList<Product> products) { this.products = products; }
    
    public ArrayList<Employee> getEmployees() { return this.employees; }
    public void setEmployees(ArrayList<Employee> employees) { this.employees = employees; }
    
    // </editor-fold>
    
}
