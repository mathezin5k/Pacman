package pacman;

import java.awt.Graphics2D;

public abstract class Personagens {
    protected int posX, posY;   
    protected int speed;        
    protected boolean isSuper;  
    
    public Personagens(int startX, int startY, int speed) {
        this.posX = startX;
        this.posY = startY;
        this.speed = speed;
        this.isSuper = false;
    }

    public boolean getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    public abstract void update(KeyHandler keyH,  int pacX, int pacY, Blinky blinky, Clyde clyde, Pinky pinky);

    public abstract void draw(Graphics2D g2, int blockSize);
}
