package gui;

import java.awt.GridLayout;
import javax.swing.*;
import db.DBController;
import hr.Employee;
import hr.CommissionEmployee.CommissionSalesEmployee;
import inventory.Manufacturer;
import inventory.Product;
import invoice.Invoice;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author anthony
 */
public class SearchInvoicePanel extends JPanel
{
    //components
    private final JLabel lblInvoiceId, lblInvoiceResults;
    
    private final JTextField txtInvoiceId;
    
    private final JTextArea araInvoiceResults;
    private final JScrollPane invoiceScrollPane;

    private final JButton searchButton, clearAllButton;
    
    public SearchInvoicePanel()
    {
        //set layout
        setLayout(new GridLayout(3,2));
        
        //initialize buttons
        //NOTE: NEED TO ATTACH ACTION LISTENERS RIGHT AFTER BUTTON CREATION
        searchButton = new JButton("Find Invoice");
        //SUBMIT BUTTON ACTION LISTENER
        SearchHandler searchHandler = new SearchHandler();
        searchButton.addActionListener(searchHandler);
        //CLEAR ALL ACTION LISTENER
        clearAllButton = new JButton("Clear All");
        ClearHandler clearHandler = new ClearHandler();
        clearAllButton.addActionListener(clearHandler);

        //area to hold buttpns so that we can size them properly
        JPanel buttonPane = new JPanel();
        buttonPane.add(searchButton);
        buttonPane.add(clearAllButton);
        
        //init labels
        lblInvoiceId = new JLabel("Invoice ID: ");
        lblInvoiceResults = new JLabel("Results: "); 
        
        //init text fields
        txtInvoiceId = new JTextField(15);
        txtInvoiceId.setName("Inovice ID");

        //init Text Area's
        araInvoiceResults = new JTextArea(3,3);
        invoiceScrollPane = new JScrollPane(araInvoiceResults);
        
        

        //set the border
        setBorder(BorderFactory.createTitledBorder("Search Invoice's"));
        
        //add components
        add(lblInvoiceId);
        add(txtInvoiceId);
        add(lblInvoiceResults);
        add(invoiceScrollPane);
        add(buttonPane);
        
    }
     /**
     * This private internal class implements ActionListener and controls events
     * relating to the Submit Button click.
     */
    Scanner keyboard = new Scanner(System.in);
    int mainInput, hrInput, productInput;

    private class SearchHandler implements ActionListener {

        //handle the button event
        @Override
        public void actionPerformed(ActionEvent e) {
            //declare user varaibles
            //used to check whether or not we should
            //display the confirm submission popup
            boolean checkValid = true;

            MyInputVerifier verifier = new MyInputVerifier();

            //set up a counter to count the number of returned true
            int counter = 0;
            //verify each single field
            if (verifier.verify(txtInvoiceId) == false) {
                checkValid = false;
            } else if (verifier.verify(txtInvoiceId) == true) {
                counter++;
            }
            if (checkValid == true) {
                displaySearchResult();
            }//end check valid

        }
    }//end private Submit Handler class

    //Create an array list to add employee object
    ArrayList<Invoice> invoiceList = new ArrayList<>();
    ArrayList<Product> productList = new ArrayList<>();
    ArrayList<Employee> employeeList = new ArrayList<>();
    //create a test Employee object
    Manufacturer manu = new Manufacturer("Georgian Manufacturer");
    Product p1 = new Product(1000, "Carrot", "Red", "Z1000",
                  "25", "30", true, manu);
    //create a test Employee object
    Employee e1 = new CommissionSalesEmployee(1000, "Donald", "Trump",
            "Boss", "Trump Tower", "55 Avenue",
            "666-222-0999", "ZZ333", 0.44);
    //ArrayList<Invoice>invoiceList = DBController.getInvoice();



    /**
     * Search the input and display them on the textbox
     */
    public void displaySearchResult() {
        System.out.println("Flag1");
        int id = 0;
        try {
            System.out.println("Flag2");
            employeeList.add(e1);
            System.out.println("Flag3");
            productList.add(p1);
            System.out.println("Flag4");
            System.out.println(employeeList.get(0).toStringFull());
            System.out.println(productList.get(0).toStringFull());
            
            //create a test Employee object
            Invoice invoice1 = new Invoice(1000, p1, e1);
            System.out.println("Flag5");
            invoiceList.add(invoice1);
            System.out.println("Flag6" + invoiceList.toString());
            
            //Ask the user to type in the first and last name of the employee
            //System.out.println("Please Type In the First Name of the Employee");
            //String firstName = keyboard.nextLine();

            id = Integer.parseInt(txtInvoiceId.getText());

            //System.out.println(id);
            //System.out.println("Please Type In the Last Name of the Employee");
            //String lastName = keyboard.nextLine();

            //check if the employee list is empty. If yes, send a waring.
            if (invoiceList.isEmpty()) {
                System.out.println("The Invoice List Is Empty. Please Add An Invoice");

            } //otherwise, loop through the employee list to search for employee
            else {
                //create an isMatch boolean, so when there is no a match within
                //the list, the warning message is only sent once
                boolean isMatch = true;
                if (isMatch) {
                    for (Invoice invoice : invoiceList) {
                        System.out.println("Flag7" + invoice.getTotalCost());
                        System.out.println("Flag8" + invoice.getId());
                        if (id == invoice.getId()) {
                            //System.out.println("\n" + e.toString());

                            araInvoiceResults.setText(invoice.getEmployeess().toStringFull() + "\n"
                                   + invoice.getProducts().toStringFull() + "\n\n"
                                   + "Total Cost: " + invoice.getTotalCost() );
                            isMatch = true;
                            break;
                        } else {
                            isMatch = false;

                        }
                    }
                }
                if (isMatch == false) //System.out.println("There Is No Match In the System.");  
                {
                    JOptionPane.showMessageDialog(null, "There is no match in the system");
                }
            }
        } catch (NumberFormatException fe) {
            JOptionPane.showMessageDialog(null, "Id is a number. Please enter a new one");
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Something is not working");
        }
    }

    /**
     * This public internal class inherits from InputVerifier with modifications
     * specifically designed for the Product Panel.
     */
    public class MyInputVerifier extends InputVerifier {

        @Override
        public boolean verify(JComponent input) {
            String name = input.getName();
            String text = ((JTextField) input).getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, input.getName() + " cannot be empty");
                return false;
            } else if (name == "Commission Rate") {
                try {

                    double tempCommissionRate = Double.parseDouble(text);

                    if (tempCommissionRate < 0 || tempCommissionRate > 1) {
                        JOptionPane.showMessageDialog(null, "Commission rate must be a percent (enter value between 0 and 1).");
                        return false;
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "This is not a valid commission rate. Please try again.");
                    return false;
                }
                return true;
            }//end commission rate validator
            else if (name == "First Name" || name == "Last Name") {

                if (text.length() > 25) {
                    JOptionPane.showMessageDialog(null,
                            "Names must not exceed 25 characters.");
                    return false;
                }
                return true;

            }//end name validator
            else if (name == "Address") {

                if (text.length() > 100) {
                    JOptionPane.showMessageDialog(null,
                            "Address must not exceed 100 characters.");
                    return false;
                }
                return true;
            }//end address validator
            else if (name == "Sin") {

                if (!(text.length() == 9)) {
                    JOptionPane.showMessageDialog(null,
                            "SIN must be 9 characters.");
                    return false;
                }
                return true;
            }//end SIN validator
            else if (name == "Phone") {

                if (text.length() > 12) {
                    JOptionPane.showConfirmDialog(null,
                            "Phone number must not exceed 12 digits.");
                    return false;
                }
                return true;
            }//end phone validator
            else {
                return true;
            }

        }//verify method
    }//end verifier class

    /**
     * This private internal class implements ActionListener to control events
     * relating to the Clear Button click.
     */
    private class ClearHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtInvoiceId.setText("");
            araInvoiceResults.setText("");

        }//end action handler
    }//end clear handler
}
