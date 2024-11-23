package MyMenu;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class flappyBird extends JPanel implements ActionListener, KeyListener {

    Sob_variables V = new Sob_variables();
    int wWidth = V.wWidth;
    int wHeight = V.wHeight;
    JFrame frame;
    // IMAGES_______________________________________
    Image background_img = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
    Image Character = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
    Image TopPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
    Image BottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

    // Bird _________________________________________
    int birdX = V.birdX;
    int birdY = V.birdY;
    int birdWidth = V.birdWidth;
    int birdHeight = V.birdHeight;

    // Pipes _________________________________________
    int pipeWidth = V.pipeWidth;
    int pipeHeight = V.pipeHeight;
    int gapHeight = V.gapHeight;

    // Game Variables
    Bird bird;
    int velocityX = V.velocityX;
    int velocityY = V.velocityY;
    int gravity = V.gravity;
    double score = V.score;
    int fps = V.fps;

    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    boolean checkPaused = false;
    ArrayList<pipe> pipes;

        // Create buttons
    JButton resume = new JButton("Resume");
    JButton restart = new JButton("Restart");
    JButton quit = new JButton("Quit");


    public flappyBird( JFrame f) {
        frame=f;
        setPreferredSize(new Dimension(wWidth, wHeight));
        setFocusable(true);
        addKeyListener(this);

        bird = new Bird(Character);
        pipes = new ArrayList<>();

        // Place pipes every 1.5 seconds
        placePipesTimer = new Timer(V.pipedistance, e -> placePipes());

        gameLoop = new Timer(1000 / fps, this);
    }

    // Place pipes
    public void placePipes() {
        Random rand = new Random();
        int pipeGapY = rand.nextInt(wHeight - gapHeight - 200) + 100;
        // Top pipe
        pipe topPipe = new pipe(wWidth, pipeGapY - pipeHeight, TopPipeImg);
        pipes.add(topPipe);
        // Bottom pipe
        pipe bottomPipe = new pipe(wWidth, pipeGapY + gapHeight, BottomPipeImg);
        pipes.add(bottomPipe);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


    private void addStyledButton(JButton button, int yOffset) {
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBounds(wWidth / 2 - 75, yOffset, 150, 50); // Center the buttons
        add(button);
    }




    public void pauseMenu() {

        resume.addActionListener(e -> resumeGame());
        restart.addActionListener(e -> restartGame());
        quit.addActionListener(e ->quit());

        removeAll(); // Remove previous components
        setLayout(null); // Use null layout for custom positioning
        // Add action listeners
        // Style and position buttons
        addStyledButton(resume, 100);
        addStyledButton(restart, 200);
        addStyledButton(quit, 300);
        revalidate();
        repaint();
    }


    private void resumeGame() {


        checkPaused = false;
        placePipesTimer.start();
        gameLoop.start();
        removeAll(); // Remove pause menu buttons
        revalidate();
        repaint();
    }


    private void quit(){
        System.out.println("quit button clicked!");
        frame.getContentPane().removeAll();
        frame.dispose();

//        JFrame nframe = new JFrame("MIM The Pakhi");
//        nframe.setSize(wWidth, wHeight);
//        nframe.setLocationRelativeTo(null);
//        nframe.setResizable(false);
//        nframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        StartMenu game = new StartMenu(frame);
//        nframe.add(game);
//        nframe.pack();
//        nframe.setVisible(true);

//        new StartMenu(frame);
//        frame = nframe;

        StartMenu main_Menu = new StartMenu(frame);
        frame.add(main_Menu);
        frame.setVisible(true);
    }

    // Method to initialize/reset the game


    // Method to restart the game

    private void restartGame() {

        frame.getContentPane().removeAll();
        frame.dispose();
        flappyBird game = new flappyBird(frame);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);

    }


    public void draw(Graphics g) {
        // Draw background and bird
        g.drawImage(background_img, 0, 0, wWidth, wHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        // Draw pipes
        for (pipe p : pipes) {
            g.drawImage(p.img, p.x, p.y, p.width, p.height, null);
        }

        // Display score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            gameover(g);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }

        // Draw pause menu overlay if paused
        if (checkPaused) {
            g.setColor(new Color(238, 8, 8, 100));
            g.fillRect(0, 0, wWidth, wHeight);
        }

    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for (int i = 0; i < pipes.size(); i++) {
            pipe p = pipes.get(i);
            p.x += velocityX;

            if (!p.passed && bird.x > p.x + p.width) {
                p.passed = true;
                score += 0.5;
            }

            if (collision(bird, p)) {
                gameOver = true;
            }
        }

        if (bird.y > wHeight) {
            gameOver = true;
        }
    }

    public void gameover(Graphics g) {
        // Stop game logic
        gameLoop.stop();
        placePipesTimer.stop();
        checkPaused = true;


        removeAll();
        setLayout(null);

        // Display "Game Over" message
        JLabel gameOverLabel = new JLabel("_GAME OVER_ : " + (int) score);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds((wWidth - 260) / 2, 65, 300, 50);
        add(gameOverLabel);


        addStyledButton(restart, 200);
        addStyledButton(quit, 300);

        if (restart.getActionListeners().length == 0) {
            restart.addActionListener(e -> restartGame());
        }
        if (quit.getActionListeners().length == 0) {
            quit.addActionListener(e -> quit());
        }

        revalidate();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (!checkPaused) {
                checkPaused = true;
                placePipesTimer.stop();
                gameLoop.stop();
                pauseMenu();
            }else {
                resumeGame();
            }
        }


    if (e.getKeyCode() == KeyEvent.VK_SPACE) {

        if (gameOver) {

            restartGame();
            
        } else {

            if (!gameLoop.isRunning()) {

                gameLoop.start();

                placePipesTimer.start();


            } else {

                velocityY = -12;

            }

        }

    }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public boolean collision(Bird a, pipe b) {
        return a.x < b.x + b.width
                && a.x + a.width > b.x
                && a.y < b.y + b.height
                && a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !checkPaused) {
            move();
        }
        repaint();
    }

    // Bird and Pipe Classes
    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    class pipe {
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
}
