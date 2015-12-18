/**
 * Anthony Scinocco 200271982
 * December 12, 2015
 * Handles our greeting panel
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