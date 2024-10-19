package MyMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import MyMenu.flappyBird;

public class StartMenu extends JPanel implements ActionListener {

    int wWidth = 360;
    int wHeight = 640;
    flappyBird f;
    // Rectangle 1
    int rectHeight = 70;
    int rectWidth = 180;
    // x 30 y 150
    int rectX = ((wWidth - rectWidth) / 2) - 10;
    int rectY = 150;

    int buttonWidth = 220;
    int buttonHeight = 60;
    int BtnGap = 0;
    // Calculate button position for Rectangle 1
    int buttonX = rectX + (rectWidth - buttonWidth) / 2;
    int buttonY = rectY + (rectHeight - buttonHeight) / 2;

    JButton startBtn;
    JButton difficultyBtn;
    JButton guidBtn;
    JButton scoresBtn;
    JFrame frame = new JFrame("MIM The Pakhi");

    public StartMenu() {

        setPreferredSize(new Dimension(wWidth, wHeight));
        setBackground(Color.black);

        // Disable layout manager (absolute positioning)
        setLayout(null);

        // Initialize buttons
        startBtn = new JButton("Start");
        difficultyBtn = new JButton("Difficulty");
        scoresBtn = new JButton("Scores");
        guidBtn = new JButton("Guide");
        // Customize buttons
        SameBtnStyle(startBtn);
        SameBtnStyle(difficultyBtn);
        SameBtnStyle(scoresBtn);
        SameBtnStyle(guidBtn);
        // Create JFrame
        frame.setSize(wWidth, wHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this); // Add JPanel to the frame

        frame.setVisible(true);
    }

    private void SameBtnStyle(JButton button) {
        button.setContentAreaFilled(false);  // Remove background fill
        button.setBorderPainted(false);      // Remove the border
        button.setOpaque(false);             // Make it fully transparent
        button.setFocusPainted(false);       // Remove focus outline
        button.setForeground(Color.WHITE);   // Set text color to white
        button.setFont(new Font("Arial", Font.BOLD, 25)); // Set font
        button.setBounds(buttonX, buttonY + BtnGap, buttonWidth, buttonHeight);
        button.addActionListener(this);
        add(button);
        BtnGap += 100;
    }

    // Custom painting for the menu title
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call super method first to clear the screen

        draw(g); // Call your draw function to render the menu
    }

    public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Font font0 = new Font("Arial", Font.BOLD, 28);
        g2d.setFont(font0);
        g2d.setColor(Color.WHITE);
        String txt = "Mim the Pakhi Menu";
        FontMetrics fm = g2d.getFontMetrics(font0);
        int x = ((wWidth - fm.stringWidth(txt)) / 2);
        g2d.drawString(txt, x, 80);
        g2d.setColor(new Color(182, 200, 223));
        createRects(4, 100, g);
    }

    public void createRects(int times, int gaps, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int Gap = 0;
        for (int i = 0; i < times; i++) {
            g2d.fillRoundRect(rectX, rectY + Gap, rectWidth, rectHeight, 50, 50);
            Gap += gaps;
        }

    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {

            System.out.println("Start button clicked!");
            frame.getContentPane().removeAll();
            frame.repaint(); // Repaint to clear the old components
            flappyBird f = new flappyBird(); // Add the new panel
            frame.add(f);
            frame.revalidate(); //
        } else if (e.getSource() == difficultyBtn) {
            System.out.println("Difficulty button clicked!");
            // Handle difficulty button action
        } else if (e.getSource() == scoresBtn) {
            System.out.println("Scores button clicked!");
            // Handle scores button action
        } else if (e.getSource() == guidBtn) {
            System.out.println("Guide button clicked!");
        }
    }

}
