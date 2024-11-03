package MyMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import org.w3c.dom.css.RGBColor;

public class flappyBird extends JPanel implements ActionListener, KeyListener {

    All_variables var = new All_variables();
    int wWidth = var.wWidth;
    int wHeight = var.wHeight;

    // IMAGES_______________________________________
    Image background_img = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
    Image Character = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
    Image TopPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
    Image BottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

    // Bird _________________________________________
    int birdX = var.birdX;
    int birdY = var.birdY;
    int birdWidth = var.birdWidth;
    int birdHeight = var.birdHeight;

    // Pipes
    int pipeWidth = var.pipeWidth;
    int pipeHeight = var.pipeHeight;

    int gapHeight = var.gapHeight;

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
    int velocityX = var.velocityX;
    int velocityY = var.velocityY;
    int gravity = var.gravity;
    double score = var.score;
    int fps = var.fps;

    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    boolean checkSmallMenu = false;
    ArrayList<pipe> pipes;

    public flappyBird() {

        setPreferredSize(new Dimension(wWidth, wHeight));
        setFocusable(true);
        addKeyListener(this);

        bird = new Bird(Character);
        pipes = new ArrayList<>();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        // placePipesTimer.start();
        gameLoop = new Timer(1000 / fps, this);
        // gameLoop.start();

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
        if (checkSmallMenu == true) {
            pauseMenu(g);
        }

    }

    public void pauseMenu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 50, 50, 100));
        g2d.fillRoundRect(80, 120, 200, 400,50, 50);
        

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
    boolean first = true;

    @Override

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (first == true) {
                checkSmallMenu = true;
                placePipesTimer.stop();
                gameLoop.stop();

                first = false;
            } else {
                placePipesTimer.start();
                gameLoop.start();
                checkSmallMenu = false;
                first = true;
            }
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (var.firstTime == true) {
                placePipesTimer.start();
                // gameLoop = new Timer(1000 / 50, this);
                gameLoop.start();
                var.firstTime = false;
            }
            if (gameLoop.isRunning()) {
                velocityY = -12;
            }
            if (gameOver) {
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                var.firstTime = true;
                // gameLoop.start();
                // placePipesTimer.start();
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
