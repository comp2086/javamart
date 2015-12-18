/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * This class provides a more specific CommissionSales Employee
 */
package hr.CommissionEmployee;

//needed 
import hr.Employee;

public class CommissionSalesEmployee extends Employee 
{
    //variables to support commission employee
    private double commissionRate;//percentage of pay per sale
    private int sales;//number of sales 
    
    /**
     * Default no argument constructor
     */
    public CommissionSalesEmployee()
    {
    }
    
    public CommissionSalesEmployee(int id, String firstName, String lastName, 
                                   String position, String department, String address, 
                                   String phone, String sin, double commissionRate)
    {
        super(id, firstName, lastName, position,department, address, phone, sin);        
        setCommissionRate(commissionRate);        
    }
    
    /**********Override Earning calculation******************/
   
    /**
     * Calculates earnings by multiplying sales and commission rate
     * @return - the employee's earnings
     */
    @Override
    public double calculateEarnings()
    {
//        return (getSales() * getCommissionRate());
        return 0;
    }
    
    
    
    /**********CommissionSalesEmployee ToString ************/
    /**
     * 
     * @return full String representation of an employee object
     */
    @Override 
    public String toStringFull() 
    {
        return super.toStringFull() + " " + getCommissionRate();
    }
    /**
     * 
     * @return full name of an employee
     */
    @Override
    public String toString()
    {
        return super.toString();
    }
    
    
    /*****Mutator Methods***********/
    
    /**
     * Commission rate is set to string as all data taken from a gui is
     * a string until parsed.
     * @param commissionRate - the commission rate as a string
     */
    public void setCommissionRate(double commissionRate)
    {
        try
        {
            this.commissionRate = commissionRate;        
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    /**
     * sales is set to string as all data taken from a gui is
     * a string until parsed.
     * @param sales - the number of sales and employee has
     */
   /* public void setSales(String sales)
    {
        try
        {
            this.sales = Integer.parseInt(sales);
        
        }catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }*/
    
    /**********Accessor Methods***********/
    
    public double getCommissionRate()
    {
        return this.commissionRate;
    }
    
    /*public int getSales()
    {
        return this.sales;
    }*/
}
