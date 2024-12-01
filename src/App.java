import MyMenu.Sob_variables;
import MyMenu.StartMenu;
import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {

        JFrame frame = new JFrame("MIM The Pakhi");
        Sob_variables var = new Sob_variables();
        int wWidth = var.wWidth;
        int wHeight = var.wHeight;
        frame.setSize(wWidth, wHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartMenu main_Menu = new StartMenu(frame);
        frame.add(main_Menu);

        frame.setVisible(true);
    }
}
