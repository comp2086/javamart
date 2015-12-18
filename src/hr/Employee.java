/**
 * Anthony Scinocco 200271982  
 * December 12, 2015
 * This is our abstract employee class which all other potential employee's
 * can inherit from. It provides a baseline makeup of an employee
 */
package hr;

public abstract class Employee 
{
    //employee fields
    private String firstName, lastName, position,
                   department, address, phone, sin;
    private int id;
    
    
    /**
     * Default no argument constructor
     */
    public Employee()
    {
    }
    
    /**
     * Fully detailed constructor
     * id is not included as it is populated by the MySQL
     * @param id
     * @param firstName
     * @param lastName
     * @param position
     * @param department
     * @param address
     * @param phone
     * @param sin 
     */
    public Employee(int id, String firstName, String lastName, String position,
                   String department, String address, String phone, String sin)
    {
       this.id          = id;
       this.firstName   = firstName;
       this.lastName    = lastName;
       this.position    = position;
       this.department  = department;
       this.address     = address;
       this.phone       = phone;
       this.sin         = sin;
    }
    
    /*******Employee toString*********/
    
    @Override
    public String toString()
    {
        String employeeDescription = "";
        employeeDescription += "\nName:\t" + getFullName();
        employeeDescription += "\nID:\t" + getId();
        employeeDescription += "\nDepartment:\t" + getDepartment();
        employeeDescription += "\nPosition:\t" + getPosition();
        employeeDescription += "\nAddress:\t" + getAddress();
        employeeDescription += "\nPhone Number:\t" + getPhone();
        employeeDescription += "\nSIN:\t" + getSin();
        
        return employeeDescription;
    }
    
    //get just the name
    public String nameString()
    {
        return getFullName();
    }
    
    /*******Abstract Methods**********/
    
    /**
     * Allows employee earnings to be calculated 
     * as necessary
     * @return - employee earnings
     */
    public abstract double calculateEarnings();
    
    /*******Accessor Methods**********/
    
    public String getFullName()
    {
        return getFirstName() + " " + getLastName();
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    
    public String getPosition()
    {
        return this.position;
    }
    
    public String getDepartment()
    {
        return this.department;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public String getPhone()
    {
        return this.phone;
    }
    
    public String getSin()
    {
        return this.sin;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    /*******Mutator Methods***********/
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public void setPosition(String position)
    {
        this.position = position;
    }
    
    public void setDepartment(String department)
    {
        this.department = department;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public void setSin(String sin)
    {
        this.sin = sin;
    }
}
