package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Fantasmas extends Personagens {
    private Random random = new Random();

    public Fantasmas(int startX, int startY, int speed) {
        super(startX, startY, speed);
    }
    
    @Override
    public void update(KeyHandler keyH) {
        int dx = random.nextInt(3) - 1; // -1, 0 ou 1
        int dy = random.nextInt(3) - 1;
        
        posX += dx * speed;
        posY += dy * speed;
    }

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.setColor(Color.red);
        g2.fillRect(posX, posY, blockSize, blockSize);
    }
}
