/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our search product panel
 */
package gui;
import java.awt.*;
import javax.swing.*;

public class SearchProductPanel extends JPanel
{
    //components
    //labels
    private final JLabel lblProductSearch, lblProductResult;
    
    //text fields
    private final JTextField txtProductSearch;
    
    //text area
    private final JTextArea araProductResult;
    
    //buttons
    private final JButton searchButton, clearAllButton;
    
    public SearchProductPanel()
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
        lblProductSearch = new JLabel("Search for Product's:");
        lblProductResult = new JLabel("Product Result:");
         
        //initialize the text felds
        txtProductSearch = new JTextField(15);
        
        //initialize the text area's
        araProductResult = new JTextArea(3,3);
        
        //set the border
        setBorder(BorderFactory.createTitledBorder("Search Product's"));
        
        add(lblProductSearch);
        add(txtProductSearch);
        add(lblProductResult);
        add(araProductResult);
        add(buttonPane);
    }
}
