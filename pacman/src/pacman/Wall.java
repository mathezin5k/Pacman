package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Wall {
    
    BufferedImage wall_V, wall_H, wall_I_down, wall_I_left, wall_I_right, wall_I_top, wall_L_downRight, wall_L_downLeft, wall_L_topRight, wall_L_topLeft, wall_U_down, wall_U_left, wall_U_right, wall_U_upper;
    
    public Wall(){
        getWallImage();
    }
    public void getWallImage()  {
        try{
            wall_V = ImageIO.read(getClass().getResource("/pacman-art/wall_vertical.png"));
            wall_H = ImageIO.read(getClass().getResource("/pacman-art/wall_horizontal.png"));
            wall_I_down = ImageIO.read(getClass().getResource("/pacman-art/wall_I_down.png"));
            wall_I_left = ImageIO.read(getClass().getResource("/pacman-art/wall_I_left.png"));
            wall_I_right = ImageIO.read(getClass().getResource("/pacman-art/wall_I_right.png"));
            wall_I_top = ImageIO.read(getClass().getResource("/pacman-art/wall_I_top.png"));
            wall_L_downLeft = ImageIO.read(getClass().getResource("/pacman-art/wall_L_down_left.png"));
            wall_L_downRight = ImageIO.read(getClass().getResource("/pacman-art/wall_L_down_right.png"));
            wall_L_topRight = ImageIO.read(getClass().getResource("/pacman-art/wall_L_top_right.png"));
            wall_L_topLeft = ImageIO.read(getClass().getResource("/pacman-art/wall_L_top_left.png"));
            wall_U_left = ImageIO.read(getClass().getResource("/pacman-art/wall_U_left.png"));
            wall_U_right = ImageIO.read(getClass().getResource("/pacman-art/wall_U_right.png"));
            wall_U_upper = ImageIO.read(getClass().getResource("/pacman-art/wall_U_upper.png"));
            wall_U_down = ImageIO.read(getClass().getResource("/pacman-art/wall_U_down.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    
    }
    
    public void draw(Graphics2D g2, int posX, int posY, int blockSize, int type){
        switch (type) {
            case 8:
                g2.drawImage(wall_H, posX, posY, blockSize, blockSize, null);
                break;
            case 9:
                g2.drawImage(wall_V, posX, posY, blockSize, blockSize, null);
                break;
            case 10:
                g2.drawImage(wall_I_down, posX, posY, blockSize, blockSize, null);
                break;
            case 11:
                g2.drawImage(wall_I_left, posX, posY, blockSize, blockSize, null);
                break;
            case 12:
                g2.drawImage(wall_I_top, posX, posY, blockSize, blockSize, null);
                break;
            case 13:
                g2.drawImage(wall_I_right, posX, posY, blockSize, blockSize, null);
                break;
            case 14:
                g2.drawImage(wall_L_downRight, posX, posY, blockSize, blockSize, null);
                break;
            case 15:
                g2.drawImage(wall_L_topRight, posX, posY, blockSize, blockSize, null);
                break;
            case 16:
                g2.drawImage(wall_L_downLeft, posX, posY, blockSize, blockSize, null);
                break;
            case 17:
                g2.drawImage(wall_L_topLeft, posX, posY, blockSize, blockSize, null);
                break;
            case 18:
                g2.drawImage(wall_U_left, posX, posY, blockSize, blockSize, null);
                break;
            case 19:
                g2.drawImage(wall_U_right, posX, posY, blockSize, blockSize, null);
                break;
            case 20:
                g2.drawImage(wall_U_upper, posX, posY, blockSize, blockSize, null);
                break;
            case 21:
                g2.drawImage(wall_U_down, posX, posY, blockSize, blockSize, null);
                break;
        }
    }
}
