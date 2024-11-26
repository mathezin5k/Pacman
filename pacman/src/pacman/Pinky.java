package pacman;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pinky extends Fantasmas{
    Clyde clyde;
    Blinky blinky;
    private Image pinky;
    Pacman pacman;
     private int targetX;
     private int targetY;
    
    public Pinky(int startX, int startY, int speed, Pathfinder pathfinder, Pacman pacman) {
        super(startX, startY, speed, pathfinder);
        ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/pinky.png"));
        pinky = icon.getImage();
        this.pacman = pacman;
    }

    @Override
    public void update(KeyHandler keyH, int pacX, int pacY, Blinky blinky, Clyde clyde, Pinky pinky) {
        this.clyde = clyde;
        this.blinky = blinky;
        
        if(pacman.isIsSuper()){
            targetX = super.runIsSuperX(pacman, posX);
            targetY = super.runIsSuperY(pacman, posY);
            ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/blue_ghost.png"));
            this.pinky = icon.getImage();
            speed = 1;
        }else{
            movLogic();
            speed = 2;
            ImageIcon icon = new ImageIcon(getClass().getResource("/pacman-art/pinky.png"));
            this.pinky = icon.getImage();
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
                
                blinky.setPos();
                clyde.setPos();

                if (pacman.getLifes() == 0) {
                    pacman.isAlive = false;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.drawImage(pinky, posX, posY, blockSize, blockSize, null);
    }
    
    public void setPos(){
        posX = 14 * 24;
        posY = 13 * 24;
    }
    
    public void movLogic(){
        if(pacman.posX >= 372){
            if(pacman.posY >= 336){
                //quadrant 4;
                 switch (pacman.getDirect()) {
                case 0: // Up
                    this.targetX = 23;
                    this.targetY = 9;
                    break;
                case 1: // Down
                    this.targetX = 23;
                    this.targetY = 21;
                    break;
                case 2: // Left
                    this.targetX = 14;
                    this.targetY = 21;
                    break;
                case 3: // Right
                    this.targetX = 29;
                    this.targetY = 12;                    
                    break;
                }
            }else{
                //quadrant 2;
                 switch (pacman.getDirect()) {
                case 0: // Up
                    this.targetX = 26;
                    this.targetY = 3;
                    break;
                case 1: // Down
                    this.targetX = 23;
                    this.targetY = 18;
                    break;
                case 2: // Left
                    this.targetX = 14;
                    this.targetY =  6;
                    break;
                case 3: // Right
                    this.targetX = 29;
                    this.targetY = 15;                    
                    break;
                }
            }
        }else{
           if(pacman.posY >= 336){
               // quadrant 3;
                switch (pacman.getDirect()) {
                case 0: // Up
                    this.targetX = 5;
                    this.targetY = 13;
                    break;
                case 1: // Down
                    this.targetX = 10;
                    this.targetY = 15;
                    break;
                case 2: // Left
                    this.targetX = 5;
                    this.targetY = 18;
                    break;
                case 3: // Right
                    this.targetX = 14;
                    this.targetY = 21;                    
                    break;
                }
            }else{
                // quadrant 1;
                switch (pacman.getDirect()) {
                case 0: // Up
                    this.targetX = 8;
                    this.targetY = 6;
                    break;
                case 1: // Down
                    this.targetX = 3;
                    this.targetY = 12;
                    break;
                case 2: // Left
                    this.targetX = 5;
                    this.targetY = 6;
                    break;
                case 3: // Right
                    this.targetX = 13;
                    this.targetY = 6;                    
                    break;
                }
            }
        }
    }
}
