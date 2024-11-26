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

    private int[][] map; // Mapa de 28 x 31
    private int animationCounter = 0; // Contador para controle de animação
    private int frameIndex = 0; // Índice do frame da animação

    // Valores que representam diferentes tipos de blocos no mapa
    private final int WALL_MIN = 8;
    private final int WALL_MAX = 21;
    private final int PILL = 2;
    private final int SUPER_PILL = 3;
    private final int EMPTY = 0;
    private int score = 0;
    private boolean isSuper = false; // Status de super pílula

    public Pacman(int startX, int startY, int speed, int[][] map) {
        super(startX, startY, speed);
        this.map = map;
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
        System.out.println("X "+ posX + "Y " + posY);

        int nextX = posX;
        int nextY = posY;

        // Define a próxima posição de acordo com a tecla pressionada
        if (keyH.upPressed) {
            nextY -= speed;
            currentImage = (frameIndex == 0) ? up1 : (frameIndex == 1) ? up2 : up3;
        }else if (keyH.downPressed) {
            nextY += speed;
            currentImage = (frameIndex == 0) ? down1 : (frameIndex == 1) ? down2 : down3;
        }else if (keyH.leftPressed) {
            nextX -= speed;
            currentImage = (frameIndex == 0) ? left1 : (frameIndex == 1) ? left2 : left3;
        }else if (keyH.rightPressed) {
            nextX += speed;
            currentImage = (frameIndex == 0) ? right1 : (frameIndex == 1) ? right2 : right3;
        }

        // Converte a próxima posição em pixels para a posição no grid
        
        int gridY = nextY / 24;
        int gridX = nextX / 24;
        if (keyH.upPressed) {
            
        }else if (keyH.downPressed) {
            if(nextY % 24 > 0){
                gridY ++;
            }
        }else if (keyH.leftPressed) {
            
        }else if (keyH.rightPressed) {
            if(nextX % 24 > 0){
            gridX ++;
            }
        }

        // Verifica se a posição está dentro dos limites e não é uma parede
        if (gridX >= 0 && gridX < map[0].length && gridY >= 0 && gridY < map.length && isNotWall(map[gridY][gridX])) {
            posX = nextX;
            posY = nextY;

            // Verifica o que há na célula atual
            if (map[gridY][gridX] == PILL) {
                map[gridY][gridX] = 0; // Marca a pílula como coletada
                score += 10;
            } else if (map[gridY][gridX] == SUPER_PILL) {
                map[gridY][gridX] = 0; // Marca a super pílula como coletada
                isSuper = true;
                score += 50;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        g2.drawImage(currentImage, posX, posY, blockSize, blockSize, null);
    }
    
    private boolean isNotWall(int block) {
        return block < WALL_MIN || block > WALL_MAX; // Retorna verdadeiro se não for uma parede
    }
    
    public void getPacImage(){
        try {
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
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isSuper() {
        return isSuper;
    }
}
