
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class flappyBird extends JPanel implements ActionListener, KeyListener {

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

    ArrayList<pipe> pipes;

    public flappyBird() {

        setPreferredSize(new Dimension(wWidth, wHeight));
        setFocusable(true);
        addKeyListener(this);

// )____
        bird = new Bird(Character);
        pipes = new ArrayList<>();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();
        gameLoop = new Timer(1000 / 50, this);
        gameLoop.start();
        

    }

    public void placePipes() {
        Random rand = new Random();
        int pipeGapY = rand.nextInt(wHeight - gapHeight - 200) + 100;

        // Create top pipe
        pipe topPipe = new pipe(wWidth, pipeGapY - pipeHeight, TopPipeImg);
pipes.add(topPipe);

        // Create bottom pipe
        pipe bottomPipe = new pipe(wWidth, pipeGapY + gapHeight, BottomPipeImg);
        pipes.add(bottomPipe);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(background_img, 0, 0, wWidth, wHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (pipe p : pipes) {
            g.drawImage(p.img, p.x, p.y, p.width, p.height, null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("_GAME OVER_ : " + String.valueOf((int) score), 10, 35);
        } else {
            g.setColor(Color.white);

            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for (int i = 0; i < pipes.size(); i++) {
            pipe p = pipes.get(i);
            p.x += velocityX;

            if ((!p.passed) && (bird.x > p.x + p.width)) {
                p.passed = true;
                score += .5;
            }

            if (collision(bird, p)) {
                gameOver = true;
            }
        }

        if (bird.y > wHeight) {
            gameOver = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -12;
            if (gameOver) {
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean collision(Bird a, pipe b) {
        return a.x < b.x + b.width
                && a.x + a.width > b.x
                && a.y < b.y + b.height
                && a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();

        }
    }
}
