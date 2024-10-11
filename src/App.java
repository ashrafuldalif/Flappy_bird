
import javax.swing.JFrame;

public class App {

    public static void main(String[] args) throws Exception {
        int wWidth = 360;
        int wHeight = 640;
        JFrame frame = new JFrame("MIM The Pakhi");
        // frame.setVisible(true);
        frame.setSize(wWidth, wHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        flappyBird flappy_Bird = new flappyBird();
        frame.add(flappy_Bird);
        frame.pack();
        flappy_Bird.requestFocus();
        frame.setVisible(true);
    }
}
