package MyMenu;

import java.awt.*;
import javax.swing.*;

public class testing extends JPanel {

    public testing() {
        // Set layout to null to allow absolute positioning
        setLayout(null);

        // Create a JButton
        JButton button = new JButton("Click Me");

        // Customize the button's size and position
        button.setBounds(100, 100, 200, 50);

        // Customize the button's appearance       
        button.setContentAreaFilled(false);  // Remove background fill
        button.setBorderPainted(false);      // Remove the border
        button.setOpaque(false); 
        button.setFocusPainted(false);            // Make it fully transparent
        button.setFont(new Font("Arial", Font.BOLD, 20));

        // Add the button to the panel
        add(button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the color for the rounded rectangle
        g2d.setColor(Color.GRAY);

        // Draw the rounded rectangle (x, y, width, height, arcWidth, arcHeight)
        g2d.fillRoundRect(90, 90, 220, 70, 30, 30);

        g2d.dispose();
    }

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Rounded Rectangle Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create an instance of the custom JPanel
        testing panel = new testing();

        // Add the panel to the frame
        frame.add(panel);
        // Set the frame visible
        frame.setVisible(true);
    }
}
