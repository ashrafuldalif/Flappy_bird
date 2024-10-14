
import java.awt.Image;
import java.util.Timer;
import javax.swing.ImageIcon;

public class All_info {
    
    int wWidth = 360;
    int wHeight = 640;

    // IMAGES_______________________________________
    Image background_img = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
    Image Character = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
    Image TopPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
    Image BottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

    // Bird _________________________________________
    int birdX = wWidth / 8;
    int birdY = wHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    // Pipes
    int pipeWidth = 64;
    int pipeHeight = 512;

    int gapHeight = 200;

    class Bird { // BIRD CLASS__________________

        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    class pipe {   // PIPE CLASS__________________

        int x;
        int y;
        int width = pipeWidth;
        int height = pipeHeight;
        boolean passed = false;
        Image img;

        pipe(int x, int y, Image img) {
            this.x = x;
            this.y = y;
            this.img = img;
        }
    }

    // game logic
    Bird bird;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;
    double score = 0;

    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    
}
