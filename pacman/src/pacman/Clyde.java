package pacman;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Clyde extends Fantasmas {
    private final int escapeDistance = 5 * 24;
    Pacman pacman;
    Blinky blinky;
    Pinky pinky;
    private Image clyde;
    private long startTime;
    private boolean scape = false;
    
    public Clyde(int startX, int startY, int speed, Pathfinder pathfinder, Pacman pacman) {
        super(startX, startY, speed, pathfinder);
        ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/clyde.png"));
        clyde = icon.getImage();
        this.pacman = pacman;
    }

    @Override
    public void update(KeyHandler keyH, int pacX, int pacY, Blinky blinky, Clyde clyde, Pinky pinky) {
        int distance = (int) Math.sqrt(Math.pow(pacX - posX, 2) + Math.pow(pacY - posY, 2));
        int targetX;
        int targetY;
        
        if(pacman.isIsSuper()){
            targetX = super.runIsSuperX(pacman, posX);
            targetY = super.runIsSuperY(pacman, posY);
            ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/blue_ghost.png"));
            this.clyde = icon.getImage();
            speed = 1;
        }else if (distance <= escapeDistance) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/clyde.png"));
            this.clyde = icon.getImage();
            speed = 1;
            targetX = 1;
            targetY = 1;
            startTime = System.currentTimeMillis();
            timerClyde();
        }else{
            speed = 2;
            ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/clyde.png"));
            this.clyde = icon.getImage();
            if(scape == false){
                targetX = pacX / 24;
                targetY = pacY / 24;
            }else{
                targetX = 1;
                targetY = 1;
                if (!scape) {
                    startTime = System.currentTimeMillis();
                    scape = true;
                }
            timerClyde();
            }
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
                blinky.setPos();

                if (pacman.getLifes() == 0) {
                    pacman.isAlive = false;
                }
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.drawImage(clyde, posX, posY, blockSize, blockSize, null);
    }
    
    public void setPos(){
        posX = 13 * 24;
        posY = 14 * 24;
    }
    
    public void timerClyde() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= 300) {
            scape = false;
            startTime = 0;
        }
    }
}