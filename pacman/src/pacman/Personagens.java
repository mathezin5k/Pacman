package pacman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Personagens {
    protected int posX, posY;   // Posições do personagem
    protected int speed;        // Velocidade do personagem
    protected boolean isSuper;  // Estado especial do personagem
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3, ghost1, ghost2, ghost3, ghost4;

    public Personagens(int startX, int startY, int speed) {
        this.posX = startX;
        this.posY = startY;
        this.speed = speed;
        this.isSuper = false;  // Estado inicial de isSuper é false
    }

    public boolean getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    public abstract void update(KeyHandler keyH,  int pacX, int pacY);

    // Método abstrato para desenhar o personagem
    public abstract void draw(Graphics2D g2, int blockSize);
}
