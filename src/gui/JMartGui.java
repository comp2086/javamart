/**
 * Anthony Scinocco
 * November 12, 2015
 * Handles our gui
 */
package gui;
import db.DBController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JMartGui extends JFrame
{
    private JPanel centerPanel, northPanel, southPanel;
    private JButton exitButton;
    
    public JMartGui()
    {
        super("JMart");
        setLayout(new BorderLayout());
        
        // Get all data from the database
        DBController.populateEmployees();
        DBController.populateProducts();
        
        //create the tab container
        JTabbedPane tabPane = new JTabbedPane();
        
        //commission sales employee tab
        JLabel lblPanelOne = new JLabel("",SwingConstants.CENTER);
        JPanel commissionSalesEmployeePanel = new CommissionSalesEmployeePanel();
        commissionSalesEmployeePanel.add(lblPanelOne);
        tabPane.addTab("Commission Sales Employee",null,commissionSalesEmployeePanel,"First Panel");
        
        //product tab
        JLabel lblPanelTwo = new JLabel("",SwingConstants.CENTER);
        JPanel productPanel = new ProductPanel();
        productPanel.add(lblPanelTwo);
        tabPane.addTab("Product",null,productPanel,"Second Panel");
        
        //search employeetab
        JLabel lblPanelThree = new JLabel("",SwingConstants.CENTER);
        JPanel searchEmployeePanel = new SearchEmployeePanel();
        searchEmployeePanel.add(lblPanelThree);
        tabPane.addTab("Search Employee's",null,searchEmployeePanel,"Third Panel");
        
        //search product tab
        JLabel lblPanelFour = new JLabel("",SwingConstants.CENTER);
        JPanel searchProductPanel = new SearchProductPanel();
        searchProductPanel.add(lblPanelFour);
        tabPane.addTab("Search Product's",null,searchProductPanel,"Fourth Panel");
        
        //invoice panel
        JLabel lblPanelFive = new JLabel("",SwingConstants.CENTER);
        JPanel invoicePanel = new InvoicePanel();
        invoicePanel.add(lblPanelFive);
        tabPane.addTab("Invoice",null,invoicePanel,"Fifth Panel");
        
        //invoice search panel
        JLabel lblPanelSix = new JLabel("",SwingConstants.CENTER);
        JPanel searchInvoicePanel = new SearchInvoicePanel();
        searchInvoicePanel.add(lblPanelSix);
        tabPane.addTab("Search Invoice's", null, searchInvoicePanel,"Sixth Panel");
        
        //build the panels
        northPanel = new GreetingPanel();
        buildButtonPanel();
        
        add(northPanel, BorderLayout.NORTH);
        add(tabPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        
        
        
        
    }//end of constructor
    
    /**
     * Sets up the panels and exit button
     */
    private void buildButtonPanel()
    {
        southPanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonHandler());
        
        southPanel.add(exitButton);
    }
    
    /**
     * Handles closes the application when the exit button is clicked
     */
    private class ExitButtonHandler implements ActionListener
    {
        @Override 
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0);
        }        
    }
    //main method
    public static void main(String[] args)
    {
        JMartGui gui = new JMartGui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
        
        //Read all data from DB and populate local 
        DBController.populateLocal();
    }
}

