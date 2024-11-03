package MyMenu;

public class All_variables {

    public int wWidth = 360;
    public int wHeight = 640;

    int rectHeight = 70;
    int rectWidth = 180;
    int rectX = ((wWidth - rectWidth) / 2) - 10;
    int rectY = 150;

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
    int fps = 50;
    boolean firstTime=true;

}
