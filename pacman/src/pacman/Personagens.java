package pacman;

import java.awt.Graphics2D;

public abstract class Personagens {
    protected int posX, posY;   // Posições do personagem
    protected int speed;        // Velocidade do personagem
    protected boolean isSuper;  // Estado especial do personagem

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

    public abstract void update(KeyHandler keyH);

    // Método abstrato para desenhar o personagem
    public abstract void draw(Graphics2D g2, int blockSize);
}
