/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our search employee panel
 */
package gui;

import db.DBController;
import hr.Employee;
import hr.CommissionEmployee.CommissionSalesEmployee;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SearchEmployeePanel extends JPanel {

    //components
    //labels
    private final JLabel lblEmployeeSearch, lblEmployeeResult;

    //text fields
    private final JTextField txtEmployeeSearch;

    //text area
    private final JTextArea araEmployeeResult;
    private final JScrollPane employeeScrollPane;
    
    //buttons
    private final JButton searchButton, clearAllButton;

    public SearchEmployeePanel() {
        //set layout
        setLayout(new GridLayout(3, 2));

        //initialize buttons
        //NOTE: NEED TO ATTACH ACTION LISTENERS RIGHT AFTER BUTTON CREATION
        searchButton = new JButton("Search");
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

        //initialize the labels
        lblEmployeeSearch = new JLabel("Search for Employee's:");
        lblEmployeeResult = new JLabel("Employee Result:");

        //initialize the text felds
        txtEmployeeSearch = new JTextField(15);
        txtEmployeeSearch.setName("Employee ID");

        //initialize the text area's
        araEmployeeResult = new JTextArea(3, 3);
        employeeScrollPane = new JScrollPane(araEmployeeResult);

        //set the border
        setBorder(BorderFactory.createTitledBorder("Search Employee's"));

        add(lblEmployeeSearch);
        add(txtEmployeeSearch);
        add(lblEmployeeResult);
        add(employeeScrollPane);
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
            if (verifier.verify(txtEmployeeSearch) == false) {
                checkValid = false;
            } else if (verifier.verify(txtEmployeeSearch) == true) {
                counter++;
            }
            if (checkValid == true) {
                displaySearchResult();
            }//end check valid

        }
    }//end private Submit Handler class

    //Create an array list to add employee object
    ArrayList<Employee> employeeList = new ArrayList<>();
    //ArrayList<Employee>employeeList = DBController.getEmployees();

    //create a test Employee object
    Employee e1 = new CommissionSalesEmployee(1000, "Donald", "Trump",
            "Boss", "Trump Tower", "55 Avenue",
            "666-222-0999", "ZZ333", 0.44);

    /**
     * Search the input and display them on the textbox
     */
    public void displaySearchResult() {
        int id = 0;
        try {
            employeeList.add(e1);
            //Ask the user to type in the first and last name of the employee
            //System.out.println("Please Type In the First Name of the Employee");
            //String firstName = keyboard.nextLine();

            id = Integer.parseInt(txtEmployeeSearch.getText());

            //System.out.println(id);
            //System.out.println("Please Type In the Last Name of the Employee");
            //String lastName = keyboard.nextLine();

            //check if the employee list is empty. If yes, send a waring.
            if (employeeList.isEmpty()) {
                System.out.println("The Employee List Is Empty. Please Add An Employee");

            } //otherwise, loop through the employee list to search for employee
            else {
                //create an isMatch boolean, so when there is no a match within
                //the list, the warning message is only sent once
                boolean isMatch = true;
                if (isMatch) {
                    for (Employee employee : employeeList) {
                        System.out.println(employee.getLastName());
                        System.out.println(employee.getId());
                        if (id == employee.getId()) {
                            //System.out.println("\n" + e.toString());

                            araEmployeeResult.setText(employee.toStringFull());
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
            txtEmployeeSearch.setText("");
            araEmployeeResult.setText("");

        }//end action handler
    }//end clear handler
}
