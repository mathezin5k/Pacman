package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Fantasmas extends Personagens {
    private Random random = new Random();

    public Fantasmas(int startX, int startY, int speed) {
        super(startX, startY, speed);
    }
    
    public void update(KeyHandler keyH, int pacX, int pacY) {
        int dx = pacX - posX;
        int dy = pacY - posY;
        
            // Movimenta-se em direção ao Pac-Man
            if (Math.abs(dx) > Math.abs(dy)) {
                posX += (dx > 0) ? speed : -speed; // Movimenta horizontalmente
            } else {
                posY += (dy > 0) ? speed : -speed; // Movimenta verticalmente
            }
        
    }

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.setColor(Color.RED);
        g2.fillRect(posX, posY, blockSize, blockSize);
    }
}
