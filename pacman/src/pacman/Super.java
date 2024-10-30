package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Super {
    
    public Super(){
        getSuperImage();
    }

    BufferedImage superPil;
        public void getSuperImage(){
            try{
            superPil = ImageIO.read(getClass().getResource("/pacman-art/apple.png"));
            }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2, int posX, int posY, int blockSize){
        g2.drawImage(superPil, posX, posY, blockSize * 3/4, blockSize * 3/4, null);
    }
}
