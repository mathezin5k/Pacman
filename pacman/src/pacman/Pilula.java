package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pilula {
    
    BufferedImage pilula;
    public Pilula(){
        getPilulaImage();
    }
    
    public void getPilulaImage(){
        try{
            pilula = ImageIO.read(getClass().getResource("/pacman-art/dot.png"));
            }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2, int posX, int posY, int blockSize){
        g2.drawImage(pilula, posX, posY, blockSize/2, blockSize/2, null);
    }
}
