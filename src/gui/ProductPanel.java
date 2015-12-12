/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our product panel
 */
package gui;
import java.awt.*;
import javax.swing.*;

public class ProductPanel extends JPanel
{
    //components
    //labels
    private final JLabel lblName, lblDescription,lblSerialNumber,
                         lblCost, lblPrice,
                         lblAvailability, lblManufacturer;
    
    //text fields
    private final JTextField txtName, txtDescription, txtSerialNumber,
                             txtCost, txtPrice;
    
    //buttons
    private final JButton submitButton, clearAllButton;
    
    //comboboxes
    private final JComboBox<String> cmbAvailability, cmbManufacturer;
    
    private static final String[] availability = {"true","false"},
                                  manufacturer = {"Berlington Manufacuring",
                                                  "ISO Ltd.",
                                                  "Steele Inc."};
    
    public ProductPanel()
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
        lblName = new JLabel("Name:");
        lblDescription = new JLabel("Description:");
        lblSerialNumber = new JLabel("Serial Number:");
        lblCost = new JLabel("Cost:");
        lblPrice = new JLabel("Price:");
        lblAvailability = new JLabel("Availability:");
        lblManufacturer = new JLabel("Manufacuterer:");
        
        //initialize the text felds
        txtName = new JTextField(15);
        txtDescription = new JTextField(15);
        txtSerialNumber = new JTextField(25);
        txtCost = new JTextField(15);
        txtPrice = new JTextField(15);
        
        //combo boxes
        cmbAvailability = new JComboBox<String>(availability);
        cmbManufacturer = new JComboBox<String>(manufacturer);
        
        //set the border
        setBorder(BorderFactory.createTitledBorder("Product Information"));
        
        //add the labels
        add(lblName);
        add(txtName);
        add(lblDescription);
        add(txtDescription);
        add(lblSerialNumber);
        add(txtSerialNumber);
        add(lblCost);
        add(txtCost);
        add(lblPrice);
        add(txtPrice);
        add(lblAvailability);
        add(cmbAvailability);
        add(lblManufacturer);
        add(cmbManufacturer);
        add(buttonPane);
    }
}
