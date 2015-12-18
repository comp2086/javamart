package gui;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author anthony
 */
public class SearchInvoicePanel extends JPanel
{
    //components
    private final JLabel lblInvoiceId, lblResults;
    
    private final JTextField txtInvoiceId;
    
    private final JTextArea araResults;

    private final JButton btnFind;
    
    public SearchInvoicePanel()
    {
        //set layout
        setLayout(new GridLayout(3,2));
        
        //build button pane
        btnFind = new JButton("Find Invoice");
        JPanel buttonPane = new JPanel();
        buttonPane.add(btnFind);
        
        //init labels
        lblInvoiceId = new JLabel("Invoice ID: ");
        lblResults = new JLabel("Results: ");
        
        //init Text Area's
        araResults = new JTextArea(3,3);
        
        //init text fields
        txtInvoiceId = new JTextField(15);
        
        //set the border
        setBorder(BorderFactory.createTitledBorder("Search Invoice's"));
        
        //add components
        add(lblInvoiceId);
        add(txtInvoiceId);
        add(lblResults);
        add(araResults);
        add(buttonPane);
        
    }
}
