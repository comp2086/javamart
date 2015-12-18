/**
 * Anthony Scinocco
 * December 12, 2015
 * Handles the product class
 */
package inventory;


public class Product 
{
    //initialize instance variables
    private String name, description, serialNumber;
    private int id, manId;
    private double cost, price;
    private boolean availability = false;
    
    //manufacturer object for composition with product
    private Manufacturer manu;
    
    /**
     * Default no argument constructor
     */
    public Product()
    {
    }
    
    /**
     * Fully detailed constructor.
     * @param id
     * @param name
     * @param description
     * @param serialNumber
     * @param cost
     * @param price
     * @param availablitiy
     * @manu
     */
    public Product(int id, String name, String description, String serialNumber,
                  String cost, String price, boolean availability, Manufacturer manu)
    {
        setId(id);
        setName(name);
        setDescription(description);
        setSerialNumber(serialNumber);
        setCost(cost);
        setPrice(price);
        setAvailability(availability);
        
        this.manu = manu;
    }
    
    /*******Product and manufacturer toString****/
    /**
     * 
     * @return name of a product
     */
    @Override
    public String toString()
    {
        return this.getName();
    }
    
    /**
     * @return - string representation of a product 
     */
    
    public String toStringFull()
    {
        String productDescription = "";
        
        productDescription += "\nProduct Name:\t" + getName();
        productDescription += "\nProduct ID:\t" + getId();
        productDescription += "\nProduct Serial Number:\t" + getSerialNumber();
        productDescription += "\nProduct Description:\t" + getDescription();
        productDescription += "\nProduct Cost to make:\t$" + getCost();
        productDescription += "\nProduct Price charged:\t$" + getPrice();
        productDescription += "\nProduct Available?:\t" + getAvailability();
        
        //add product manufacturer to the string representation
        productDescription += getManufacturerToString();
        
        return productDescription;
    }
    
    public String getManufacturerToString()
    {
        return this.manu.toString();
    }
    
    /*****Mutator Methods******/
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    } 
    
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    } 
    
    /**
     * I set id as an int because the value will be coming
     * directly from the database as an int
     * @param id - the product id
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * I set manId as an int because the value will be coming
     * directly from the database as an int
     * @param manId - the manufacturer id
     */
    public void setManId(int manId)
    {
        this.manId = manId;
    }
    
    /**
     * I set cost a string as it will be coming from the gui
     * which will pass in string data
     * @param cost - the cost to make the product
     */
    public void setCost(String cost)
    {
        try
        {
            this.cost = Double.parseDouble(cost);
            
        }catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    /**
     * I set price a string as it will be coming from the gui
     * which will pass in string data
     * @param price - the price to sell product at
     */
    public void setPrice(String price)
    {
        try
        {
            this.cost = Double.parseDouble(price);
            
        }catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    public void setAvailability(boolean availability)
    {
        this.availability = availability;
    }
    
    /*****Accessor Methods*****/
    
     public String getName()
    {
        return this.name;
    }
    
    public String getDescription()
    {
        return this.description;
    } 
    
    public String getSerialNumber()
    {
        return this.serialNumber;
    } 
    
    public int getId()
    {
        return this.id;
    }

    public int getManId()
    {
        return this.manId;
    }
    
    public double getCost()
    {
        return this.cost;
    }
    
    public double getPrice()
    {
       return this.price;
    }
    
    public boolean getAvailability()
    {
        return this.availability;
    }
    
}
