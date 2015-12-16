package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InvoicePanel extends JPanel
{
    //components
    //labels
    private final JLabel lblTotalCost, lblEmployees, lblProducts;
    
    //text fields
    private final JTextField txtTotalCost;

    //buttons
    private final JButton btnCalc, btnClear, btnAddEmployee, btnAddProduct;
    
    //comboboxes
    private final JList<String> lstEmployees, lstProducts, lstSelectedEmps, lstSelectedProds;
    
    //temp solution to fill out combo boxes
    private static final String[] employees = {"Employee 1", "Employee 2"},
                                  products = {"Product 1", "Product 2"};
    
    public InvoicePanel()
    {
        // Layout
        setLayout(new GridLayout(3,3));
        GridBagConstraints gbc = new GridBagConstraints();
        
        btnCalc = new JButton("Calculate");
        btnClear = new JButton("Clear All");
        
        btnAddEmployee = new JButton("Add >>");
        btnAddEmployee.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        lstSelectedEmps.setListData(lstEmployees.getSelectedValuesList().toArray(new String[0]));
                    } 
                }
        );
        
        btnAddProduct = new JButton("Add >>");
        btnAddProduct.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        lstSelectedProds.setListData(lstProducts.getSelectedValuesList().toArray(new String[0]));
                    } 
                }
        );
        
        JPanel btnPaneMain = new JPanel();
        btnPaneMain.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 3;
        btnPaneMain.add(btnCalc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        btnPaneMain.add(btnClear, gbc);
        
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
        
        // Multiple selection lists
        lstEmployees = new JList(employees);
        lstSelectedEmps = new JList();
        lstProducts = new JList(products);
        lstSelectedProds = new JList();
        
        lstEmployees.setVisibleRowCount(5);
        lstSelectedEmps.setVisibleRowCount(5);
        lstEmployees.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstSelectedEmps.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        lstProducts.setVisibleRowCount(5);
        lstSelectedProds.setVisibleRowCount(5);
        lstProducts.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstSelectedProds.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
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
        add(btnPaneMain);
        
    }
}
