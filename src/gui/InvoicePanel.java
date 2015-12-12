/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our invoice panel
 */
package gui;
import java.awt.*;
import javax.swing.*;

public class InvoicePanel extends JPanel
{
    //components
    //labels
    private final JLabel lblTotalCost, lblEmployees, lblProducts;
    
    //text fields
    private final JTextField txtTotalCost;

    //buttons
    private final JButton calculateButton, clearAllButton;
    
    //comboboxes
    private final JComboBox<String> cmbEmployees, cmbProducts;
    
    //temp solution to fill out combo boxes
    private static final String[] employees = {"Fill from DB"},
                                  products = {"Fill from DB"};
    
    public InvoicePanel()
    {
        //set layout
        setLayout(new GridLayout(10,2));
        
        //initialize buttons
        //NOTE: NEED TO ATTACH ACTION LISTENERS RIGHT AFTER BUTTON CREATION
        calculateButton = new JButton("Calculate");
        //SUBMIT BUTTON ACTION LISTENER
        clearAllButton = new JButton("Clear All");
        //CLEAR ALL ACTION LISTENER
        
        //area to hold buttpns so that we can size them properly
        JPanel buttonPane = new JPanel();
        buttonPane.add(calculateButton);
        buttonPane.add(clearAllButton);
        
        //initialize the labels
        lblTotalCost = new JLabel("Total Cost:");
        lblEmployees = new JLabel("Employee:");
        lblProducts = new JLabel("Product:");
        
        //initialize the text felds
        txtTotalCost = new JTextField(15);
        
        //combo boxes
        cmbEmployees = new JComboBox<String>(employees);
        cmbProducts = new JComboBox<String>(products);
        
        //set the border
        setBorder(BorderFactory.createTitledBorder("Invoice Information"));
        
        //add the labels
        add(lblEmployees);
        add(cmbEmployees);
        add(lblProducts);
        add(cmbProducts);
        add(lblTotalCost);
        add(txtTotalCost);
        add(buttonPane);
        
    }
}
