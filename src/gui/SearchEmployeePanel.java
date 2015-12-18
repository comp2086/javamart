/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our search employee panel
 */
package gui;
import java.awt.*;
import javax.swing.*;

public class SearchEmployeePanel extends JPanel
{
    //components
    //labels
    private final JLabel lblEmployeeSearch, lblEmployeeResult;
    
    //text fields
    private final JTextField txtEmployeeSearch;
    
    //text area
    private final JTextArea araEmployeeResult;
    
    //buttons
    private final JButton searchButton, clearAllButton;
    
    public SearchEmployeePanel()
    {
        //set layout
        setLayout(new GridLayout(3,2));
        
        //initialize buttons
        //NOTE: NEED TO ATTACH ACTION LISTENERS RIGHT AFTER BUTTON CREATION
        searchButton = new JButton("Search");
        //SUBMIT BUTTON ACTION LISTENER
        clearAllButton = new JButton("Clear All");
        //CLEAR ALL ACTION LISTENER
        
        //area to hold buttpns so that we can size them properly
        JPanel buttonPane = new JPanel();
        buttonPane.add(searchButton);
        buttonPane.add(clearAllButton);
        
        //initialize the labels
        lblEmployeeSearch = new JLabel("Search for Employee's:");
        lblEmployeeResult = new JLabel("Employee Result:");
         
        //initialize the text felds
        txtEmployeeSearch = new JTextField(15);
        
        //initialize the text area's
        araEmployeeResult = new JTextArea(3,3);
        
        //set the border
        setBorder(BorderFactory.createTitledBorder("Search Employee's"));
        
        add(lblEmployeeSearch);
        add(txtEmployeeSearch);
        add(lblEmployeeResult);
        add(araEmployeeResult);
        add(buttonPane);
    }
}
