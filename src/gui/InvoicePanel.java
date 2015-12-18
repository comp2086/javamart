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
    //components
    //labels
    private final JLabel lblTotalCost, lblEmployees, lblProducts;
    
    //text fields
    private final JTextField txtTotalCost;

    //buttons
    private final JButton btnCalc, btnClear, btnCreate, btnAddEmployee, btnAddProduct;
    
    //comboboxes
    private final JList<String> lstEmployees, lstProducts, lstSelectedEmps, lstSelectedProds;
    
    private DefaultListModel<Employee> modelEmps = new DefaultListModel();
    private DefaultListModel<Product> modelProds = new DefaultListModel();
    private DefaultListModel<Employee> modelSelectedEmps = new DefaultListModel();
    private DefaultListModel<Product> modelSelectedProds = new DefaultListModel();
    
    public InvoicePanel()
    {
        // Layout
        setLayout(new GridLayout(3,3));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Get all data from the database
        DBController.populateEmployees();
        DBController.populateProducts();
        
        // Employee and product selection lists
        for(int i = 0; i < DBController.getEmployees().size(); i++) {
            modelEmps.add(i, DBController.getEmployees().get(i));
        }
        lstEmployees = new JList(modelEmps);
                
        for(int i = 0; i < DBController.getProducts().size(); i++) {
            modelProds.add(i, DBController.getProducts().get(i));
        }
        lstProducts = new JList(modelProds);
        
        lstSelectedProds = new JList();
        lstSelectedEmps = new JList();
        
        lstEmployees.setVisibleRowCount(5);
        lstSelectedEmps.setVisibleRowCount(5);
        lstEmployees.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstSelectedEmps.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        lstProducts.setVisibleRowCount(5);
        lstSelectedProds.setVisibleRowCount(5);
        lstProducts.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstSelectedProds.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        btnCalc = new JButton("Calculate Cost");
        btnClear = new JButton("Clear All");
        btnClear.addActionListener(
            e -> { ; }
        );
        
        btnCreate = new JButton("Create Invoice");
        
        btnAddEmployee = new JButton("Add >>");
        /*btnAddEmployee.addActionListener(
                e -> {
                for(int i = 0; i < employees.length; i++) {
                    modelEmps.add(i, employees[i]);
                }
            }
        );*/
        
        btnAddProduct = new JButton("Add >>");
        btnAddProduct.addActionListener(
            e -> {lstSelectedProds.setListData(lstProducts.getSelectedValuesList().toArray(new String[0]));}
        );
        
        JPanel btnPaneMain = new JPanel();
        btnPaneMain.setLayout(new BorderLayout());
        
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
        
        // Labels
        lblTotalCost = new JLabel("Total Cost:");
        lblEmployees = new JLabel("Employee:");
        lblProducts = new JLabel("Product:");
        
        // Text felds
        txtTotalCost = new JTextField(15);

        // Border
        setBorder(BorderFactory.createTitledBorder("Invoice Information"));
        
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
