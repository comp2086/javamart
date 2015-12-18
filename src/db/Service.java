package db;

/**
 *
 * @author Dan Masci - 200299037
 */
public class Service {
    
    private static int empId, prodId, manuId, invId;
    
    public static void initializeIds(int emp, int prod, int manu, int inv) {
        empId = emp;
        prodId = prod;
        manuId = manu;
        invId = inv;
    }
    
    public static int getEmpId() {
        return ++empId;
    }
    
    public static int getProdId() {
        return ++prodId;
    }
    
    public static int getManuId() {
        return ++manuId;
    }    
}//class
