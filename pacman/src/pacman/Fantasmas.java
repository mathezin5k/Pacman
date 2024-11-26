package pacman;

import java.awt.Graphics2D;

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
        }
    }

    public int runIsSuperY(Pacman pacman, int currentY){
        if(pacman.posY - currentY > 0){
            return 1;
        }else{
            return 26;
        }
    }
}
