/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our search product panel
 */
package gui;

import db.DBController;
import hr.Employee;
import hr.CommissionEmployee.CommissionSalesEmployee;
import inventory.Manufacturer;
import inventory.Product;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SearchProductPanel extends JPanel
{
    //components
    //labels
    private final JLabel lblProductSearch, lblProductResult;
    
    //text fields
    private final JTextField txtProductSearch;
    
    //text area
    private final JTextArea araProductResult;
    private final JScrollPane productScrollPane;
    
    //buttons
    private final JButton searchButton, clearAllButton;
    
    public SearchProductPanel()
    {
        //set layout
        setLayout(new GridLayout(3,2));
        
        //initialize buttons
        //NOTE: NEED TO ATTACH ACTION LISTENERS RIGHT AFTER BUTTON CREATION
        searchButton = new JButton("Search");
        SearchHandler searchHandler = new SearchHandler();
        searchButton.addActionListener(searchHandler);
        //Search BUTTON ACTION LISTENER
                
        //CLEAR ALL ACTION LISTENER
        clearAllButton = new JButton("Clear All");
        ClearHandler clearHandler = new ClearHandler();
        clearAllButton.addActionListener(clearHandler);
        
        //area to hold buttpns so that we can size them properly
        JPanel buttonPane = new JPanel();
        buttonPane.add(searchButton);
        buttonPane.add(clearAllButton);
        
        //initialize the labels
        lblProductSearch = new JLabel("Search for Product's:");
        lblProductResult = new JLabel("Product Result:");
         
        //initialize the text felds
        txtProductSearch = new JTextField(15);
        txtProductSearch.setName("Product ID");
        
        //initialize the text area's
        araProductResult = new JTextArea(3,3);
        productScrollPane = new JScrollPane(araProductResult);
        
        //set the border
        setBorder(BorderFactory.createTitledBorder("Search Product's"));
        
        add(lblProductSearch);
        add(txtProductSearch);
        add(lblProductResult);
        add(productScrollPane);
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
            if (verifier.verify(txtProductSearch) == false) {
                checkValid = false;
            } else if (verifier.verify(txtProductSearch) == true) {
                counter++;
            }
            if (checkValid == true) {
                displaySearchResult();
            }//end check valid

        }
    }//end private Submit Handler class

    //Create an array list to add employee object
    ArrayList<Product> productList = new ArrayList<>();
    //ArrayList<Product>productList = DBController.getProducts();

    //create a test Employee object
    Manufacturer manu = new Manufacturer("Georgian Manufacturer");
    Product p1 = new Product(1000, "Carrot", "Red", "Z1000",
                  "25", "30", true, manu);

    /**
     * Search the input and display them on the textbox
     */
    public void displaySearchResult() {
        int id = 0;
        try {
            productList.add(p1);
            //Ask the user to type in the first and last name of the employee
            //System.out.println("Please Type In the First Name of the Employee");
            //String firstName = keyboard.nextLine();

            id = Integer.parseInt(txtProductSearch.getText());

            //System.out.println(id);
            //System.out.println("Please Type In the Last Name of the Employee");
            //String lastName = keyboard.nextLine();

            //check if the employee list is empty. If yes, send a waring.
            if (productList.isEmpty()) {
                System.out.println("The Product List Is Empty. Please Add A Product");

            } //otherwise, loop through the employee list to search for employee
            else {
                //create an isMatch boolean, so when there is no a match within
                //the list, the warning message is only sent once
                boolean isMatch = true;
                if (isMatch) {
                    for (Product product : productList) {
                        System.out.println(product.getName());
                        System.out.println(product.getId());
                        if (id == product.getId()) {
                            //System.out.println("\n" + e.toString());

                            araProductResult.setText(product.toStringFull());
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
            txtProductSearch.setText("");
            araProductResult.setText("");

        }//end action handler
    }//end clear handler
}
