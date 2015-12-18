/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles the CommissionSalesEmployee greetings panel
 */
package gui;

import db.*;
import hr.CommissionEmployee.CommissionSalesEmployee;
import hr.Employee;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CommissionSalesEmployeePanel extends JPanel 
{

    //components
    //labels
    private final JLabel lblFirstName, lblLastName, lblPosition,
            lblDepartment, lblAddress, lblPhone, lblSin,
            lblCommissionRate;

    //text fields
    private final JTextField txtFirstName, txtLastName,
            txtAddress, txtPhone, txtSin,
            txtCommissionRate;

    //buttons
    private final JButton submitButton, clearAllButton;

    //combo boxes
    private final JComboBox<String> cmbPosition, cmbDepartment;

    //arrays to hold positions and departments
    private static final String[] position = {"Manager", "Sales Associate", "Janitor",
        "Programmer", "Help Desk", "Financial Analyst",
        "Engineer", "Vice President", "Designer"},
            department = {"Human Resources", "Information Technology",
                "Engineering", "Maintainance", "Marketing"};

    /**
     * Constructor
     */
    public CommissionSalesEmployeePanel() {
        //set layout
        setLayout(new GridLayout(10, 2));

        //initialize buttons
        //NOTE: NEED TO ATTACH ACTION LISTENERS RIGHT AFTER BUTTON CREATION
        submitButton = new JButton("Submit");
        //SUBMIT BUTTON ACTION LISTENER
        SubmitHandler handler = new SubmitHandler();
        submitButton.addActionListener(handler);

        clearAllButton = new JButton("Clear All");
        //CLEAR ALL ACTION LISTENER
        ClearHandler clearHandler = new ClearHandler();
        clearAllButton.addActionListener(clearHandler);

        //area to hold buttpns so that we can size them properly
        JPanel buttonPane = new JPanel();
        buttonPane.add(submitButton);
        buttonPane.add(clearAllButton);

        //initialize the labels
        lblFirstName = new JLabel("First Name:");
        lblLastName = new JLabel("Last Name:");
        lblPosition = new JLabel("Position:");
        lblDepartment = new JLabel("Department:");
        lblSin = new JLabel("SIN:");
        lblPhone = new JLabel("Phone Number:");
        lblAddress = new JLabel("Address:");
        lblCommissionRate = new JLabel("Commission Rate:");

        //initialize the text felds
        txtFirstName = new JTextField(15);
        txtLastName = new JTextField(15);
        txtAddress = new JTextField(25);
        txtSin = new JTextField(15);
        txtPhone = new JTextField(15);
        txtCommissionRate = new JTextField(15);

        //set the name for commission rate text field
        txtFirstName.setName("First Name");
        txtLastName.setName("Last Name");
        txtAddress.setName("Address");
        txtSin.setName("Sin");
        txtPhone.setName("Phone");
        txtCommissionRate.setName("Commission Rate");

        //combo boxes
        cmbPosition = new JComboBox<String>(position);
        cmbDepartment = new JComboBox<String>(department);

        //set the border
        setBorder(BorderFactory.createTitledBorder("Employee Information"));

        //add the labels
        add(lblFirstName);
        add(txtFirstName);
        add(lblLastName);
        add(txtLastName);
        add(lblAddress);
        add(txtAddress);
        add(lblPhone);
        add(txtPhone);
        add(lblSin);
        add(txtSin);
        add(lblPosition);
        add(cmbPosition);
        add(lblDepartment);
        add(cmbDepartment);
        add(lblCommissionRate);
        add(txtCommissionRate);
        add(buttonPane);
    }//end constructor

    /**
     * Confirms the submission
     * @return - JOption pane will return 0 if the user selects ok
     * and it will return 2 if the user selects cancel. If the user
     * presses the x(exit) button it will return -1
     */
    public static int confirmSubmission()
    {
       return JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?",
                                    "Confirm Submission", JOptionPane.OK_CANCEL_OPTION);
    }
    
    /**
     * This private internal class implements ActionListener and controls events 
     * relating to the Submit Button click.
     */
    private class SubmitHandler implements ActionListener {

        //handle the button event
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //used to check whether or not we should
            //display the confirm submission popup
            boolean checkValid = true;
            
            MyInputVerifier verifier = new MyInputVerifier();

            //set up a counter to count the number of returned true
            int counter = 0;
            //create a JText Arraylist used in a for loop for validation
            ArrayList<JTextField> jList = new ArrayList<JTextField>();
            jList.add(txtFirstName);
            jList.add(txtLastName);
            jList.add(txtAddress);
            jList.add(txtSin);
            jList.add(txtPhone);
            jList.add(txtCommissionRate);

            //verify each single field
            for (JTextField j : jList) {
                if (verifier.verify(j) == false) {
                    checkValid = false;
                    break;
                } else if (verifier.verify(j) == true) {
                    counter++;
                }
            }
            
            if(checkValid == true)
            {
                //creates popup confirmation 
                int response = confirmSubmission();
                //if the user selects okay
                if(response == 0)
                {
                    //submit only when all the empty fields are filled
                    if (counter == 6) //You replace the code below with a call to submit the data to the database
                    {
                        //First, create our Employee based on User Inputs
                        CommissionSalesEmployee tempEmp = new CommissionSalesEmployee(
                                Service.getEmpId(),
                                txtFirstName.getText(),
                                txtLastName.getText(),
                                cmbPosition.getSelectedItem().toString(),
                                cmbDepartment.getSelectedItem().toString(),
                                txtAddress.getText(),
                                txtPhone.getText(),
                                txtSin.getText(),
                                Double.parseDouble(txtCommissionRate.getText())
                            );
                        //Second, add the employee to local memory
                        DBController.getEmployees().add(tempEmp);
                        //Third, add the employee to DB
                        DBController.createEmployee(tempEmp);
                    }
                    //System.out.println(counter);
                    //System.out.println(jList.size());
                }
            }//end check valid
        }
    }//end private Submit Handler class

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
                    
                    if(tempCommissionRate < 0 || tempCommissionRate > 1)
                    {
                        JOptionPane.showMessageDialog(null,"Commission rate must be a percent (enter value between 0 and 1).");
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
            /* More efficient method, but it can't setText to empty for now */
            //CommissionSalesEmployeePanel cEmployee = new CommissionSalesEmployeePanel();
            //System.out.print(cEmployee.getComponentCount());
            //for (Component c : cEmployee.getComponents()) {
                //if (c instanceof JTextField) {
                    //System.out.print(c);
                    //System.out.print(txtFirstName);
                    //System.out.print("Hey");
                    //JTextField jt = (JTextField)c;
                    //txtFirstName.setText("");
                    //jt.setEnabled(true);
                    //jt.setText("");
                    //((JTextField) c).setText("");
                //}
                
            //}
            
            /* Hardcoded Method */
            txtFirstName.setText("");
            txtLastName.setText("");
            txtAddress.setText("");
            txtSin.setText("");
            txtPhone.setText("");
            txtCommissionRate.setText("");
            
        }//end action handler
    }//end clear handler
}//end class
