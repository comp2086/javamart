/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our product panel
 */
package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

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
        
                //set the name for commission rate text field
        txtName.setName("Name");
        txtDescription.setName("Description");
        txtSerialNumber.setName("Serial Number");
        txtCost.setName("Cost");
        txtPrice.setName("Price");
        
        
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
    
    private class SubmitHandler implements ActionListener
    {

        
        //handle the button event
        @Override
        public void actionPerformed(ActionEvent e)
        {
            MyInputVerifier verifier = new MyInputVerifier();
            
            //set up a counter to count the number of returned true
            int counter = 0;  
            //create a JText Arraylist used in a for loop for validation
            ArrayList<JTextField> jList = new ArrayList<JTextField>();
            jList.add(txtName);
            jList.add(txtDescription);
            jList.add(txtSerialNumber);
            jList.add(txtCost);
            jList.add(txtPrice);
            
         
            //verify each single field
            for(JTextField j : jList)
            {
                if(verifier.verify(j)==false)
                {
                    break;
                }
                else if (verifier.verify(j)==true)
                {
                    counter++;   
                }
             }
             //submit only when all the empty fields are filled
             if(counter == 5)
             //You replace the code below with a call to submit the data to the database
                 System.out.println("You won! Motherfucker!!");
                 //System.out.println(counter);
                 //System.out.println(jList.size());
            }
    }
    
    public class MyInputVerifier extends InputVerifier
    {
        @Override
        public boolean verify(JComponent input)
        {
            String name = input.getName();
            String text = ((JTextField)input).getText();
                 if (text.isEmpty())
                 {
                        JOptionPane.showMessageDialog(null, input.getName() + " cannot be empty");
                        return false;
                 }
                 else if (name =="Cost")
                 {  
                     try
                        {
                            Double.parseDouble(text);
                        }
                        catch (NumberFormatException e)
                        {
                            JOptionPane.showMessageDialog(null, 
                                    "This is not a valid cost. please try again");
                            return false;
                        }
                        return true;
                 }
                 else if (name =="Price")
                 {
                     try
                        {
                            Double.parseDouble(text);
                        }
                        catch (NumberFormatException e)
                        {
                            JOptionPane.showMessageDialog(null, 
                                    "This is not a valid price. please try again");
                            return false;
                        }
                       return true;
                 }
                 else
                 {
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
            txtName.setText("");
            txtDescription.setText("");
            txtSerialNumber.setText("");
            txtCost.setText("");
            txtPrice.setText("");
            
        }
    }
}
