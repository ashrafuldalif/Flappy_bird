package MyMenu;
import java.awt.Graphics2D;

import java.awt.Graphics;

public class Sob_variables {

    private static final String Color = null;
    private static final String Font = null;
    public int wWidth = 360;
    public int wHeight = 640;


    int rectHeight = 70;
    int rectWidth = 180;
    
    
    int rectX = ((wWidth - rectWidth) / 2) ;
    int rectY = 150;
    int border_radius=50;
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

    int gapHeight = 200;

    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;
    double score = 0;
    int fps = 60;
    int pipedistance = 1500;

    int chose = 1;

    boolean firstTime=true;
    public void createRects(int times, int gaps, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int Gap = 0;
        for (int i = 0; i < times; i++) {
            g2d.fillRoundRect(rectX, rectY + Gap, rectWidth, rectHeight, 50, 50);
            Gap += gaps;
        }
    }

}
