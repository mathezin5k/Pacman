package pacman;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Blinky extends Fantasmas {
    Clyde clyde;
    Pinky pinky;
    private Image blinky;
    Pacman pacman;
    
    public Blinky(int startX, int startY, int speed, Pathfinder pathfinder, Pacman pacman) {
        super(startX, startY, speed, pathfinder);
        ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/blinky.png"));
        blinky = icon.getImage();
        this.pacman = pacman;
    }

    @Override
    public void update(KeyHandler keyH, int pacX, int pacY, Blinky blinky, Clyde clyde, Pinky pinky) {
        this.clyde = clyde;
        this.pinky = pinky;
        
        int targetX;
        int targetY;
        
        if(pacman.isIsSuper()){
            targetX = super.runIsSuperX(pacman, posX);
            targetY = super.runIsSuperY(pacman, posY);
            ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/blue_ghost.png"));
            this.blinky = icon.getImage();
            speed = 1;
        }else{
            speed = 2;
            ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/blinky.png"));
            this.blinky = icon.getImage();
            targetX = pacX/24;
            targetY = pacY/24;
        }
        int nextX = posX / 24;
        int nextY = posY / 24;
        
        if(posY % 24 > 0){
            if(posX % 24 > 0){
                nextY++;
            }
        }else if(posX % 24 > 0){
            nextX ++;
        }

    int[] nextMove = pathfinder.findPath(nextX, nextY, targetX, targetY);
        if (nextMove != null) {
            posX += nextMove[0] * speed;
            posY += nextMove[1] * speed;
        }
        if (posY / 24 == pacY / 24 && posX / 24 == pacX / 24) {
            if(pacman.isIsSuper()){
                posX = 13 * 24;
                posY = 14 * 24;
                pacman.setScore(pacman.getScore() + 100);
            }else{
                pacman.setLifes(pacman.getLifes() - 1);
                pacman.setPosition(14 * 24, 21 * 24);

                posX = 13 * 24;
                posY = 14 * 24;
                
                pinky.setPos();
                clyde.setPos();

                if (pacman.getLifes() == 0) {
                    pacman.isAlive = false;
                }
            }
        }
    }
    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.drawImage(blinky, posX, posY, blockSize, blockSize, null);
    }
    
    public void setPos(){
        posX = 14 * 24;
        posY = 13 * 24;
    }
}
