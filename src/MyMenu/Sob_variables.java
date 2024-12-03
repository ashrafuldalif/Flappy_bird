package MyMenu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Sob_variables {

    Image[] background_img = {new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage(),
        new ImageIcon(getClass().getResource("flappybirdbg2.jpeg")).getImage(),
        new ImageIcon(getClass().getResource("flappybirdbg3.jpeg")).getImage()};

    Image[] TopPipeImg = {new ImageIcon(getClass().getResource("toppipe.png")).getImage(),
        new ImageIcon(getClass().getResource("topPipe2.png")).getImage(),
        new ImageIcon(getClass().getResource("topPipe3.png")).getImage()};

    Image[] BottomPipeImg = {new ImageIcon(getClass().getResource("bottompipe.png")).getImage(),
        new ImageIcon(getClass().getResource("bottomPipe2.png")).getImage(),
        new ImageIcon(getClass().getResource("bottomPipe3.png")).getImage()};

    private static final String Color = null;
    private static final String Font = null;
    public int wWidth = 360;
    public int wHeight = 640;
    int rectHeight = 70;
    int rectWidth = 180;

    int rectX = ((wWidth - rectWidth) / 2);
    int rectY = 150;
    int border_radius = 50;
    int buttonWidth = 220;
    int buttonHeight = 60;
    int BtnGap = 0;

    int buttonX = rectX + (rectWidth - buttonWidth) / 2;
    int buttonY = rectY + (rectHeight - buttonHeight) / 2;

    int btnFontSize = 25;
    int birdX = wWidth / 8;
    int birdY = wHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    int pipeWidth = 64;
    int pipeHeight = 512;

    int velocityY = 0;
    int gravity = 1;
    double score = 0;
    static int velocityX = -4;
    static int fps = 55;
    static int gapHeight = 220;
    static int pipedistance = 1600;
    static String clasicTxt = "";
    static String levelText = "_EASY";
    static boolean ClassicTF = false;
    static int chose= 0;
    boolean firstTime = true;

    public void createRects(int times, int gaps, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int Gap = 0;
        for (int i = 0; i < times; i++) {
            g2d.fillRoundRect(rectX, rectY + Gap, rectWidth, rectHeight, 50, 50);
            Gap += gaps;
        }
    
}
public static void ToEasy(){

    chose = 0;
    velocityX = -5;
    gapHeight = 230;
    pipedistance = 1600;
    levelText = "_EASY";
}
public static void ToMedium() {
    chose = 1;
    velocityX = -7;
    gapHeight = 220;
    pipedistance = 1400;
    levelText = "_MEDIUM";
}
public static void ToHard() {
    chose = 2;
    velocityX = -8;
    gapHeight = 210;
    pipedistance = 1200;
    levelText = "_HARD";

    }
}