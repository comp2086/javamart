/**
 * Anthony Scinocco
 * December 12, 2015
 * Handles the manufacturer's details
 */
package inventory;

public class Manufacturer 
{
    private String name;
    private int id;
    
    /**
     * Default no argument constructor
     */
    public Manufacturer()
    {
    }
  
    /**
     * Fully detailed constructor
     * Excludes id as that is set in the DB
     * @param name 
     */
    public Manufacturer(String name)
    {
        setName(name);
    }
    
    /****Manufacturer toString*****/
    
    /**
     * @return - String representation of a manufacturer 
     */
    @Override
    public String toString()
    {
       String manufacturerDescription = "";
       
       manufacturerDescription += "\nManufacturer Id:\t" + getId();
       manufacturerDescription += "\nManufacturer Name:\t" + getName();
        
       return manufacturerDescription;
    }
    
    //returns the name of the manufacturer
    public String nameString()
    {
        return getName();
    }
    
    /****Mutator Methods***********/
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    /****Accesor Methods***********/
    
    public String getName()
    {
        return this.name;
    }
    
    public int getId()
    {
        return this.id;
    }
}
