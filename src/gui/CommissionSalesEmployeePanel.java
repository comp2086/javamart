/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles the CommissionSalesEmployee greetings panel
 */
package gui;

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
    }

    /**
     * Confirms the submission
     */
    public static void confirmSubmission()
    {
       JOptionPane.showMessageDialog(null, "Are you sure you want to submit?");
    }
    
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
                confirmSubmission();
            }
            
            //submit only when all the empty fields are filled
            if (counter == 6) //You replace the code below with a call to submit the data to the database
            {
                System.out.println(".");
            }
            //System.out.println(counter);
            //System.out.println(jList.size());
        }
    }

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
                    
                    if(!(tempCommissionRate > 0))
                    {
                        JOptionPane.showMessageDialog(null,"Commission Rate must be greater than zero");
                        return false;
                    }
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "This is not a valid cost. please try again");
                    return false;
                }
                return true;
            } else {
                return true;
            }
        }
    }

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
            
        }
    }
}
