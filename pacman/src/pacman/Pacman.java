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
    private int animationCounter = 0;
    private int frameIndex = 0;
    private final int WALL_MIN = 8;
    private final int WALL_MAX = 21;
    private final int PILL = 2;
    private final int SUPER_PILL = 3;
    private final int EMPTY = 0;
    private int score = 0;
    private boolean isSuper = false; // Status de super pílula
    public boolean isAlive = true;
    private int lifes = 3;
    private int direct;
    private boolean movingUp, movingDown, movingLeft, movingRight;
    long timeLimitInMilliseconds = 300;
    long startTime = System.currentTimeMillis();

    public Pacman(int startX, int startY, int speed, int[][] map) {
        super(startX, startY, speed);
        this.map = map;
        getPacImage();
        currentImage = right1; // Define a imagem inicial
    }

    @Override
    public void update(KeyHandler keyH, int pacX, int pacY, Blinky blinky, Clyde clyde, Pinky pinky) {
        animationCounter++;
        if (animationCounter > 10) { // A cada 10 frames, troca de imagem
            frameIndex = (frameIndex + 1) % 3;
            animationCounter = 0;
        }
        System.out.println(score);
        int nextX = posX;
        int nextY = posY;

        // Define direção, mas só altera se não for parede
        if (keyH.upPressed && isNotWall(map[(posY - speed) / 24][posX / 24])) {
            if(map[(posY / 24) + 1][(posX / 24)+1] != 16 && posX % 24 > 0){
                movingUp = false;
            }else if(posX % 24 == 0){
                direct = 0;
                movingUp = true;
                movingDown = movingLeft = movingRight = false;
            }
        } else if (keyH.downPressed && isNotWall(map[(posY / 24) + 1][posX / 24])) {
            if(map[(posY / 24) + 1][(posX / 24)+1] != 14 && posX % 24 > 0){
                movingDown = false;
            }else if(posX % 24 == 0){
                direct = 1;
                movingDown = true;
                movingUp = movingLeft = movingRight = false;
            }
        } else if (keyH.leftPressed && isNotWall(map[posY / 24][(posX - speed) / 24])) {
            if(map[(posY / 24) + 1][(posX / 24)+1] != 14 && posY % 24 > 0){
                movingLeft = false;
            }else if(posY % 24 == 0){
                direct = 2;
                movingLeft = true;
                movingUp = movingDown = movingRight = false;
            }
        } else if (keyH.rightPressed && isNotWall(map[posY / 24][(posX / 24) + 1])) {
            if(map[(posY / 24) + 1][(posX / 24)+1] != 16 && posY % 24 > 0){
                movingRight = false;
            }else if(posY % 24 == 0){
                direct = 3;
                movingRight = true;
                movingUp = movingDown = movingLeft = false;
            }
        }

        // Movimenta conforme a direção atual se não houver parede
        switch (direct) {
            case 0: // Up
                if (isNotWall(map[(posY - speed) / 24][posX / 24])) {
                    nextY -= speed;
                    currentImage = (frameIndex == 0) ? up1 : (frameIndex == 1) ? up2 : up3;
                }
                break;
            case 1: // Down
                if (isNotWall(map[(posY + 24) / 24][posX / 24])) {
                    nextY += speed;
                    currentImage = (frameIndex == 0) ? down1 : (frameIndex == 1) ? down2 : down3;
                }
                break;
            case 2: // Left
                if (isNotWall(map[posY / 24][(posX - speed) / 24])) {
                    nextX -= speed;
                    currentImage = (frameIndex == 0) ? left1 : (frameIndex == 1) ? left2 : left3;
                }
                break;
            case 3: // Right
                if (isNotWall(map[posY / 24][(posX + 24) / 24])) {
                    nextX += speed;
                    currentImage = (frameIndex == 0) ? right1 : (frameIndex == 1) ? right2 : right3;
                }
                break;
        }

        posX = nextX;
        posY = nextY;

        int gridY = nextY / 24;
        int gridX = nextX / 24;


        // Verifica e coleta pílulas e super pílulas
        if (isNotWall(map[gridY][gridX])) {
            if (map[gridY][gridX] == PILL) {
                map[gridY][gridX] = 0; // Marca a pílula como coletada
                score += 10;
            } else if (map[gridY][gridX] == SUPER_PILL) {
                map[gridY][gridX] = 0; // Marca a super pílula como coletada
                isSuper = true;
                score += 50;
            }
        }
        if(score > 0){
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= timeLimitInMilliseconds) {
                score -= 1;
                startTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        int pacmanSize = (int)(blockSize * 0.9);
        g2.drawImage(currentImage, posX + (int)(blockSize * 0.1), posY + (int)(blockSize * 0.1), pacmanSize, pacmanSize, null);
    }
    
    private boolean isNotWall(int block) {
        return block < WALL_MIN || block > WALL_MAX; // Retorna verdadeiro se não for uma parede
    }

    public void getPacImage() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return score;
    }
    
    public void setScore(int score){
        this.score = score;
    }

    public boolean isSuper() {
        return isSuper;
    }
    
    public int getLifes(){
        return lifes;
    }
    
    public void setLifes(int lifes){
        this.lifes = lifes;
    }
    
    public void setPosition(int pacX, int pacY){
        posX = pacX;
        posY = pacY;
    }
    
    public int getDirect(){
        return direct;
    }

    public boolean isIsSuper() {
        return isSuper;
    }
    
    public boolean checkMap(){
        for(int i = 0; i < 31; i++){
            for(int j = 0; j < 28; j++){
                if(map[i][j] == PILL){
                    return false;
                }
            }
        }
        return true;
    }
}


