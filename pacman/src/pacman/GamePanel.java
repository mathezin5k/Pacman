package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int tamanhoBlocoOriginal = 16;
    final double escala = 1.5;
    final int blockSize = (int) (tamanhoBlocoOriginal * escala);
    
    final int tamColuna = 31;
    final int tamLinha = 28;
    final int larguraTela = (blockSize * tamColuna); //744
    final int alturaTela = (blockSize * tamLinha); //672
    
    private final int[][] ghostMap ={ //28x31
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    
    int[][] labirinto = { //28x31
        {0, 10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 13, 10, 11, 0, 0, 0, 0, 10, 10, 10, 10, 0, 0, 10, 10, 10, 10, 0},
        {13, 2, 2, 3, 2, 2, 2, 2, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 2, 2, 3, 11, 13, 2, 2, 2, 2, 11},
        {13, 2, 17, 12, 15, 2, 17, 15, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 17, 15, 2, 16, 14, 2, 17, 15, 2, 11},
        {13, 2, 11, 0, 13, 2, 11, 13, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 11, 13, 2, 2, 2, 2, 11, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 11, 13, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 11, 0, 12, 12, 15, 2, 11, 13, 2, 11},
        {13, 2, 16, 10, 14, 2, 16, 14, 2, 16, 10, 10, 10, 14, 0, 16, 10, 10, 10, 14, 2, 16, 10, 10, 10, 14, 2, 11, 13, 2, 11},
        {13, 2, 2, 2, 2, 2, 2, 2, 2, 2,  2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 11, 13, 2, 11},
        {13, 2, 17, 12, 15, 2, 17, 12, 12, 12, 12, 12, 12, 15, 0, 17, 12, 12, 12, 15, 2, 17, 15, 2, 17, 12, 12, 0, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 16, 10, 10, 0, 0, 10, 10, 14, 0, 16, 10, 10, 10, 14, 2, 11, 13, 2, 16, 10, 10, 0, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 2, 2, 2, 11, 13, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 11, 13, 2, 2, 2, 2, 11, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 17, 15, 2, 11, 13, 0, 17, 12, 12, 12, 15, 0, 17, 15, 2, 11, 13, 2, 17, 15, 2, 11, 13, 2, 11},
        {13, 2, 16, 10, 14, 2, 11, 13, 2, 16, 14, 0, 11, 0, 0, 0, 13, 0, 11, 13, 2, 16, 14, 2, 11, 13, 2, 16, 14, 2, 11},
        {13, 2, 2, 2, 2, 2, 11, 13, 2, 2, 0, 0, 11, 0, 0, 0, 13, 0, 11, 13, 2, 2, 2, 2, 11, 13, 2, 2, 2, 2, 11},
        {0, 12, 12, 12, 15, 2, 11, 0, 12, 12, 15, 0, 11, 0, 0, 0, 13, 0, 11, 0, 12, 12, 15, 2, 11, 0, 12, 12, 15, 2, 11},
        {0, 10, 10, 10, 14, 2, 11, 0, 10, 10, 14, 0, 11, 0, 0, 0, 13, 0, 11, 0, 10, 10, 14, 2, 11, 0, 10, 10, 14, 2, 11},
        {13, 2, 2, 2, 2, 2, 11, 13, 2, 2, 0, 0, 11, 0, 0, 0, 13, 0, 11, 13, 2, 2, 2, 2, 11, 13, 2, 2, 2, 2, 11},
        {13, 2, 17, 12, 15, 2, 11, 13, 2, 17, 15, 0, 11, 0, 0, 0, 13, 0, 11, 13, 2, 17, 15, 2, 11, 13, 2, 17, 15, 2, 11},
        {13, 2, 11, 0, 13, 2, 16, 14, 2, 11, 13, 0, 16, 10, 10, 10, 14, 0, 16, 14, 2, 11, 13, 2, 16, 14, 2, 11, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 2, 2, 2, 11, 13, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 11, 13, 2, 2, 2, 2, 11, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 17, 12, 12, 0, 0, 12, 12, 15, 0, 17, 12, 12, 12, 15, 2, 11, 13, 2, 17, 12, 12, 0, 13, 2, 11},
        {13, 2, 16, 10, 14, 2, 16, 10, 10, 10, 10, 10, 10, 14, 0, 16, 10, 10, 10, 14, 2, 16, 14, 2, 16, 10, 10, 0, 13, 2, 11},
        {13, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 11, 13, 2, 11},
        {13, 2, 17, 12, 15, 2, 17, 15, 2, 17, 12, 12, 12, 15, 0, 17, 12, 12, 12, 15, 2, 17, 12, 12, 12, 15, 2, 11, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 11, 13, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 11, 0, 10, 10, 14, 2, 11, 13, 2, 11},
        {13, 2, 11, 0, 13, 2, 11, 13, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 11, 13, 2, 2, 2, 2, 11, 13, 2, 11},
        {13, 2, 16, 10, 14, 2, 16, 14, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 16, 14, 2, 17, 15, 2, 16, 14, 2, 11},
        {13, 2, 2, 3, 2, 2, 2, 2, 2, 11, 0, 0, 0, 13, 0, 11, 0, 0, 0, 13, 2, 2, 2, 3, 11, 13, 2, 2, 2, 2, 11},
        {0, 12, 12, 12, 12, 12, 12, 12, 12, 0, 0, 0, 0, 13, 12, 11, 0, 0, 0, 0, 12, 12, 12, 12, 0, 0, 12, 12, 12, 12, 0},
};
    int FPS = 60;
    private boolean mazeClear = true;
    private String playerName;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Pacman pacman;
    HighscoreManager highscoreManager;
    Blinky blinky;
    Clyde clyde;
    Pinky pinky;
    Wall wall;
    Pilula pilula;
    Super superPil;
    Pathfinder pathfinder;
    Sound sound = new Sound();
    Scanner scanner = new Scanner(System.in);

    public GamePanel() {
        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        pacman = new Pacman(14 * blockSize, 21 * blockSize, 2, labirinto);
        pathfinder = new Pathfinder(ghostMap);
        pinky = new Pinky(14 * blockSize, 13 * blockSize, 2, pathfinder, pacman);
        blinky = new Blinky(14 * blockSize, 13 * blockSize, 2, pathfinder, pacman);
        clyde = new Clyde(14 * blockSize, 13 * blockSize, 2, pathfinder, pacman);
        highscoreManager = new HighscoreManager();
        wall = new Wall();
        pilula = new Pilula();
        superPil = new Super();
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void pauseGameThread(){
        try {
            gameThread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        String playerName = JOptionPane.showInputDialog(null, 
                "Digite seu nome para começar:", 
                "Pac-Man - Nome do Jogador", 
                JOptionPane.PLAIN_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Jogador";
        }
        sound.playSound("pacman_beginning.wav", 0);
        pauseGameThread();
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (pacman.isAlive || mazeClear) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
            mazeClear = pacman.checkMap();
        }
            highscoreManager.addHighscore(playerName, pacman.getScore());
            highscoreManager.sortHighscores();
            gameThread.interrupt();
            Menu menu = null;
            menu = new Menu();
            menu.setVisible(true);
    }
    
    public void update() {
        sound.playSound("Voicy_Ghost-Siren-sound.wav", 800);
        pacman.update(keyH, pacman.posX, pacman.posY, blinky, clyde, pinky);
        blinky.update(null, pacman.posX, pacman.posY, blinky, clyde,  pinky);
        clyde.update(null, pacman.posX, pacman.posY, blinky, clyde, pinky);
        pinky.update(null, pacman.posX, pacman.posY, blinky, clyde, pinky);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for(int i = 0; i < tamLinha; i++){
            for(int j = 0; j < tamColuna; j++){
                if(labirinto[i][j] >= 8 && labirinto[i][j] <=21){
                    //parede
                    wall.draw(g2, j * blockSize, i * blockSize, blockSize, labirinto[i][j]);
                }else if(labirinto[i][j] == 2){
                    //pilula
                    pilula.draw(g2, j * blockSize + blockSize/4, i * blockSize + blockSize/4, blockSize);
                }else if(labirinto[i][j] == 0){
                    //vazio 
                    g.setColor(Color.BLACK);
                    g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                }else if(labirinto[i][j] == 3){
                    //pilula super
                    superPil.draw(g2, j * blockSize + blockSize/8, i * blockSize + blockSize/8, blockSize);
                }
            }
        }
        pacman.draw(g2, blockSize);
        blinky.draw(g2, blockSize);
        clyde.draw(g2, blockSize);
        pinky.draw(g2, blockSize);
        drawLives(g2);
        drawScore(g2);
        g2.dispose();
    }
    private void drawLives(Graphics2D g2) {
            Image lifeIcon = new ImageIcon(getClass().getResource("/pacman-art/1-right.png")).getImage();

            int startX = 10;
            int startY = getHeight() - 40;
            int iconSize = 20;
            int lives = pacman.getLifes();

            for (int i = 0; i < lives; i++) {
                g2.drawImage(lifeIcon, startX + i * (iconSize + 5), startY, iconSize, iconSize, null);
            }
        }
    
    private void drawScore(Graphics2D g2){   
         Font font = new Font("Arial", Font.BOLD, 20);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        
        int score = pacman.getScore();
         int x = getWidth() - 120;
        int y = 20;
        String scoreText = "Score: " + score;
        g2.drawString(scoreText, x, y);
    }
}