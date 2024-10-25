package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pacman extends Personagens {
    
    public Pacman(int startX, int startY, int speed) {
        super(startX, startY, speed);
    }
    
    @Override
    public void update(KeyHandler keyH) {
        if (keyH.upPressed) {
            posY -= speed;
        } else if (keyH.downPressed) {
            posY += speed;
        } else if (keyH.leftPressed) {
            posX -= speed;
        } else if (keyH.rightPressed) {
            posX += speed;
        }
    }

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.setColor(Color.yellow);
        g2.fillRect(posX, posY, blockSize, blockSize);
    }
}
