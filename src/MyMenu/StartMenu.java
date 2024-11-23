package MyMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartMenu extends JPanel implements ActionListener {

    private JFrame frame;
    Sob_variables var = new Sob_variables();

    int wWidth = var.wWidth;
    int wHeight = var.wHeight;

    int rectHeight = var.rectHeight;
    int rectWidth = var.rectWidth;
    int rectX = var.rectX;
    int rectY = var.rectY;

    int buttonWidth = var.buttonWidth;
    int buttonHeight = var.buttonHeight;
    int BtnGap = var.BtnGap;

    int buttonX = var.buttonX;
    int buttonY = var.buttonY;

    JButton startBtn;
    JButton difficultyBtn;
    JButton guidBtn;
    JButton scoresBtn;



    public StartMenu(JFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(wWidth, wHeight));
        setBackground(Color.black);
        setLayout(null);
        startBtn = new JButton("Start");
        difficultyBtn = new JButton("Difficulty");
        scoresBtn = new JButton("Scores");
        guidBtn = new JButton("Guide");

        SameBtnStyle(startBtn);
        SameBtnStyle(difficultyBtn);
        SameBtnStyle(scoresBtn);
        SameBtnStyle(guidBtn);




    }

    public void Speed(int pow){

        Sob_variables sp = new Sob_variables();

        switch (pow){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;

            case 4:

                break;

                        default:

                            break;
        }

    }


    private void modesBtn() {
        // Clear the current content of the frame
//        frame.getContentPane().removeAll();

        // Set new layout and background for the frame
        setPreferredSize(new Dimension(wWidth, wHeight));
        setBackground(Color.BLACK);
        setLayout(null); // Ensure the layout is null for manual positioning

        frame.add(startBtn);
        frame.add(difficultyBtn);
        frame.add(scoresBtn);
        frame.add(guidBtn);


        JButton Classic = new JButton("Classic");
        JButton Easy = new JButton("Easy");
        JButton Medium = new JButton("Medium");
        JButton Hard = new JButton("Hard");



//        Classic.addActionListener(e->)


        BtnGap=0;
        SameBtnStyle(Classic);
        SameBtnStyle(Easy);
        SameBtnStyle(Medium);
        SameBtnStyle(Hard);





        // Refresh the frame
        frame.revalidate();
        frame.repaint();
    }


//    private void modesBtn() {
//        // Clear the current content of the frame
//        frame.getContentPane().removeAll();
//
//        // Set new layout and background for the frame
//        setPreferredSize(new Dimension(wWidth, wHeight));
//        setBackground(Color.black);
//        frame.setLayout(null);
//
//        // Create new buttons
//        JButton Classic = new JButton("Classic");
//        JButton Easy = new JButton("Easy");
//        JButton Medium = new JButton("Medium");
//        JButton Hard = new JButton("Hard");
//
//        // Apply the same button style
//        SameBtnStyle(Classic);
//        SameBtnStyle(Easy);
//        SameBtnStyle(Medium);
//        SameBtnStyle(Hard);
//
//        // Position buttons (example positions, adjust as needed)
////        Classic.setBounds(50, 50, 200, 50);
////        Easy.setBounds(50, 120, 200, 50);
////        Medium.setBounds(50, 190, 200, 50);
////        Hard.setBounds(50, 260, 200, 50);
//
//
//
//        // Repaint and revalidate to update the frame
//        frame.revalidate();
//        frame.repaint();
//    }





    private void SameBtnStyle(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBounds(buttonX, buttonY + BtnGap, buttonWidth, buttonHeight);
        button.addActionListener(this);

        add(button);
        BtnGap += 100;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            System.out.println("Start button clicked!");
            frame.getContentPane().removeAll();
            frame.dispose();
            JFrame nframe = new JFrame("MIM The Pakhi");
            nframe.setSize(wWidth, wHeight);
            nframe.setLocationRelativeTo(null);
            nframe.setResizable(false);
            nframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            flappyBird game = new flappyBird(nframe);
            nframe.add(game);
            nframe.pack();
            nframe.setVisible(true);

        } else if (e.getSource() == difficultyBtn) {
            System.out.println("Difficulty button clicked!");
            modesBtn();
        } else if (e.getSource() == scoresBtn) {
            System.out.println("Scores button clicked!");
        } else if (e.getSource() == guidBtn) {
            System.out.println("Guide button clicked!");
        }
    }
}
