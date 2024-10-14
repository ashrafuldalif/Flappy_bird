package MyMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Visibility;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenu extends JPanel implements ActionListener, KeyListener {

    int wWidth = 360;
    int wHeight = 640;

    public StartMenu() {
        setPreferredSize(new Dimension(wWidth, wHeight));
        setBackground(Color.black);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call super method first to clear the screen

        draw(g); // Call your draw function to render the menu
    }

    public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Font font0 = new Font("arial", Font.BOLD, 22);
        g2d.setFont(font0);
        g2d.setColor(Color.white);
        String txt = " Mim the Pakhi Menu";
        FontMetrics fm = g2d.getFontMetrics(font0);
        int x = (wWidth - fm.stringWidth(txt) / 2);
        int y = (wHeight - fm.getHeight(txt) / 2);
        g2d.drawString(txt, x, y);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}



         
            
                                
        
            
                                
        
            
                                
        
            
                    