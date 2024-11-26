package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
<<<<<<< Updated upstream
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fantasmas extends Personagens {
    BufferedImage ghost1, ghost2, ghost3, ghost4;
    BufferedImage currentImage;
    public int color;
    
    public Fantasmas(int startX, int startY, int speed, int color) {
        super(startX, startY, speed);
        this.color = color;
        getGhostImage();
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
    
    public void getGhostImage(){
        try{
            ghost1 = ImageIO.read(getClass().getResource("/pacman-art/blinky.png"));
            ghost2 = ImageIO.read(getClass().getResource("/pacman-art/blue_ghost.png"));
            ghost3 = ImageIO.read(getClass().getResource("/pacman-art/clyde.png"));
            ghost4 = ImageIO.read(getClass().getResource("/pacman-art/pinky.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }    

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        if(this.color == 0){
            currentImage = ghost1;
        }else if(this.color == 1){
            currentImage = ghost2;
        }else if(this.color == 2){
            currentImage = ghost3;
        }else{
            currentImage = ghost4;
=======

public abstract class Fantasmas extends Personagens {
    protected Pathfinder pathfinder;

    public Fantasmas(int startX, int startY, int speed, Pathfinder pathfinder) {
        super(startX, startY, speed);
        this.pathfinder = pathfinder;
    }

    @Override
    public abstract void update(KeyHandler keyH, int pacX, int pacY, Blinky blinky, Clyde clyde, Pinky pinky);

    @Override
    public abstract void draw(Graphics2D g2, int blockSize);
    
    public int runIsSuperX(Pacman pacman, int currentX){
        if(pacman.posX - currentX > 0){
            return 1;
        }else{
            return 29;
>>>>>>> Stashed changes
        }
    }
<<<<<<< Updated upstream
=======
    public int runIsSuperY(Pacman pacman, int currentY){
        if(pacman.posY - currentY > 0){
            return 1;
        }else{
            return 26;
        }
    }
>>>>>>> Stashed changes
}
