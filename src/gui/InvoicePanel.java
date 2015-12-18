package gui;
import db.DBController;
import java.awt.*;
import javax.swing.*;
import hr.Employee;
import inventory.Product;


/**
 *
 * @author Alex Andriishyn, 200296533
 */
public class InvoicePanel extends JPanel
{
    //labels
    private final JLabel lblTotalCost, lblEmployees, lblProducts;
    
    //text fields
    private final JTextField txtTotalCost;

    //buttons
    private final JButton btnCalc, btnClear, btnCreate, btnAddEmployee, btnAddProduct;
    
    //Lists
    private JList<Employee> lstEmployees;
    private JList<Product>  lstProducts;
    private JList<Employee> lstSelectedEmps;
    private JList<Product> lstSelectedProds;
    
    //Models for lists
    private DefaultListModel<Employee> modelEmps;
    private DefaultListModel<Product> modelProds;
    private DefaultListModel<Employee> modelSelectedEmps;
    private DefaultListModel<Product> modelSelectedProds;
    
    public InvoicePanel()
    {
        // Border and layout
        setLayout(new GridLayout(3,3));
        setBorder(BorderFactory.createTitledBorder("Invoice Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Labels
        lblTotalCost = new JLabel("Total Cost:");
        lblEmployees = new JLabel("Employee:");
        lblProducts = new JLabel("Product:");
        
        // Text felds
        txtTotalCost = new JTextField(15);
        
        // Initialize List models
        modelEmps = new DefaultListModel();
        modelProds = new DefaultListModel();
        modelSelectedEmps = new DefaultListModel();
        modelSelectedProds = new DefaultListModel();
        
        // Employee and product selection lists
        for(int i = 0; i < DBController.getEmployees().size(); i++) {
            modelEmps.add(i, DBController.getEmployees().get(i));
        }
        lstEmployees = new JList(modelEmps);
                
        for(int i = 0; i < DBController.getProducts().size(); i++) {
            modelProds.add(i, DBController.getProducts().get(i));
        }
        lstProducts = new JList(modelProds);
        
        // Lists for employees and products that were selected from the Employee and Product selection lists
        lstSelectedEmps = new JList(modelSelectedEmps);
        lstSelectedProds = new JList(modelSelectedProds);
        
        // Customize list display areas
        lstEmployees.setVisibleRowCount(5);
        lstSelectedEmps.setVisibleRowCount(5);
        lstEmployees.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstSelectedEmps.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lstProducts.setVisibleRowCount(5);
        lstSelectedProds.setVisibleRowCount(5);
        lstProducts.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstSelectedProds.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        // Calculate invoice cost
        btnCalc = new JButton("Calculate Cost");
        btnCalc.addActionListener(
            event -> {
                if(modelSelectedEmps.getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "At least one employee is required to create an invoice");
                } else if(modelSelectedProds.getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "At least one product is required to create an invoice");
                } else {
                    double totalCost = 0;

                    // Get the price of all products included in the invoice
                    for (int i = 0; i < modelSelectedProds.getSize(); i ++) {
                        totalCost += modelSelectedProds.getElementAt(i).getCost();
                    }    

                    txtTotalCost.setText(Double.toString(totalCost));
                }
            }
        );
        
        // Create invoice and store it in the DB
        btnCreate = new JButton("Create Invoice");
       
        
        // Clear the form
        btnClear = new JButton("Clear All");
        btnClear.addActionListener(
            // Clear all models for lists that hold selected employees/products
            event -> { 
                txtTotalCost.setText("");
                modelSelectedEmps.clear();
                modelSelectedProds.clear();
            }
        );
        
        // Add employee to a new invoice
        btnAddEmployee = new JButton("Add >>");
        btnAddEmployee.addActionListener(
                event -> {
                // Clear the previously existing model    
                modelSelectedEmps.clear();
                
                // Add all selected items to the model of selected employees list
                for(Employee e : lstEmployees.getSelectedValuesList()) {
                    modelSelectedEmps.addElement(e);
                }
            }
        );
        
        // Add product to a new invoice
        btnAddProduct = new JButton("Add >>");
        btnAddProduct.addActionListener(
                event -> {
                // Clear the previously existing model    
                modelSelectedProds.clear();
                
                // Add all selected items to the model of selected products list
                for(Product p : lstProducts.getSelectedValuesList()) {
                    modelSelectedProds.addElement(p);
                }
            }
        );
        
        JPanel btnPaneMain = new JPanel();
        btnPaneMain.setLayout(new GridBagLayout());
        
        btnPaneMain.add(btnCalc);
        btnPaneMain.add(btnClear);
        
        JPanel addEmpPane = new JPanel();
        addEmpPane.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 3;
        addEmpPane.add(btnAddEmployee, gbc);
        
        JPanel addProdPane = new JPanel();
        addProdPane.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 3;
        addProdPane.add(btnAddProduct, gbc);
        
        add(lblEmployees);
        add(new JScrollPane(lstEmployees));
        add(addEmpPane);
        add(new JScrollPane(lstSelectedEmps));
        add(lblProducts);
        add(new JScrollPane(lstProducts));
        add(addProdPane);
        add(new JScrollPane(lstSelectedProds));
        add(lblTotalCost);
        add(txtTotalCost);       
        add(btnPaneMain, BorderLayout.SOUTH);
    }
}
