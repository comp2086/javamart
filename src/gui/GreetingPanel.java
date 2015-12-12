/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import javax.swing.*;

public class GreetingPanel extends JPanel
{
    private final JLabel greeting;
    
    public GreetingPanel()
    {
        greeting = new JLabel("Welcome to JavaMart");
        greeting.setFont(new Font("Arial", Font.BOLD,24));
        add(greeting);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }
    
}