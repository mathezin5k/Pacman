package pacman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pacman extends Personagens {
    
    BufferedImage currentImage;
    BufferedImage up1, up2, up3;
    BufferedImage down1, down2, down3;
    BufferedImage right1, right2, right3;
    BufferedImage left1, left2, left3;

    private int animationCounter = 0; // Contador para controle de animação
    private int frameIndex = 0; // Índice do frame da animação

    public Pacman(int startX, int startY, int speed) {
        super(startX, startY, speed);
        getPacImage();
        currentImage = right1; // Define a imagem inicial
    }
    
    @Override
    public void update(KeyHandler keyH, int pacX, int pacY) {
        animationCounter++;
        if (animationCounter > 10) { // A cada 10 frames, troca de imagem
            frameIndex = (frameIndex + 1) % 3; // Alterna entre 0, 1, 2
            animationCounter = 0;
        }

        if (keyH.upPressed) {
            posY -= speed;
            currentImage = (frameIndex == 0) ? up1 : (frameIndex == 1) ? up2 : up3;
        } else if (keyH.downPressed) {
            posY += speed;
            currentImage = (frameIndex == 0) ? down1 : (frameIndex == 1) ? down2 : down3;
        } else if (keyH.leftPressed) {
            posX -= speed;
            currentImage = (frameIndex == 0) ? left1 : (frameIndex == 1) ? left2 : left3;
        } else if (keyH.rightPressed) {
            posX += speed;
            currentImage = (frameIndex == 0) ? right1 : (frameIndex == 1) ? right2 : right3;
        }
    }

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.drawImage(currentImage, posX, posY, blockSize, blockSize, null);
    }
    
    public void getPacImage(){
        try{
            up1 = ImageIO.read(getClass().getResource("/pacman-art/1-up.png"));
            up2 = ImageIO.read(getClass().getResource("/pacman-art/2-up.png"));
            up3 = ImageIO.read(getClass().getResource("/pacman-art/3-up.png"));
            down1 = ImageIO.read(getClass().getResource("/pacman-art/1-down.png"));
            down2 = ImageIO.read(getClass().getResource("/pacman-art/2-down.png"));
            down3 = ImageIO.read(getClass().getResource("/pacman-art/3-down.png"));
            right1 = ImageIO.read(getClass().getResource("/pacman-art/1-right.png"));
            right2 = ImageIO.read(getClass().getResource("/pacman-art/2-right.png"));
            right3 = ImageIO.read(getClass().getResource("/pacman-art/3-right.png"));
            left1 = ImageIO.read(getClass().getResource("/pacman-art/1-left.png"));
            left2 = ImageIO.read(getClass().getResource("/pacman-art/2-left.png"));
            left3 = ImageIO.read(getClass().getResource("/pacman-art/3-left.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
