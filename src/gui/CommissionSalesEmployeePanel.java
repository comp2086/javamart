/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles the CommissionSalesEmployee greetings panel
 */
package gui;
import java.awt.*;
import javax.swing.*;

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
    private static final String[] position = {"Manager","Sales Associate", "Janitor",
                                              "Programmer", "Help Desk","Financial Analyst",
                                              "Engineer", "Vice President","Designer"},
                                  department = {"Human Resources", "Information Technology",
                                                "Engineering", "Maintainance", "Marketing"};
    
     /**
     * Constructor
     */
    public CommissionSalesEmployeePanel()
    {
        //set layout
        setLayout(new GridLayout(10,2));
        
        //initialize buttons
        //NOTE: NEED TO ATTACH ACTION LISTENERS RIGHT AFTER BUTTON CREATION
        submitButton = new JButton("Submit");
        //SUBMIT BUTTON ACTION LISTENER
        clearAllButton = new JButton("Clear All");
        //CLEAR ALL ACTION LISTENER
        
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
}
