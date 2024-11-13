package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int tamanhoBlocoOriginal = 16;
    final double escala = 1.5;
    final int blockSize = (int) (tamanhoBlocoOriginal * escala);
    
    final int tamColuna = 31;
    final int tamLinha = 28;
    final int larguraTela = (blockSize * tamColuna);
    final int alturaTela = (blockSize * tamLinha);
    
    int[][] labirinto = { //28x31
        {0, 10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 13, 0, 11, 0, 0, 0, 0, 10, 10, 10, 10, 0, 0, 10, 10, 10, 10, 0},
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
        {0, 12, 12, 12, 15, 2, 11, 0, 12, 12, 15, 0, 4, 0, 0, 0, 13, 0, 11, 0, 12, 12, 15, 2, 11, 0, 12, 12, 15, 2, 11},
        {0, 10, 10, 10, 14, 2, 11, 0, 10, 10, 14, 0, 4, 0, 0, 0, 13, 0, 11, 0, 10, 10, 14, 2, 11, 0, 10, 10, 14, 2, 11},
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
        {0, 12, 12, 12, 12, 12, 12, 12, 12, 0, 0, 0, 0, 13, 0, 11, 0, 0, 0, 0, 12, 12, 12, 12, 0, 0, 12, 12, 12, 12, 0},
};
    
    int FPS = 60;
    boolean isAlive = true;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Pacman pacman;
    Fantasmas ghost;
    Fantasmas ghost2;
    Wall wall;
    Pilula pilula;
    Super superPil;

    public GamePanel() {
        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        pacman = new Pacman(14 * blockSize, 21 * blockSize, 2, labirinto);
        ghost = new Fantasmas(200, 200, 2, 0);
        ghost2 = new Fantasmas(150, 150, 2, 1);
        wall = new Wall();
        pilula = new Pilula();
        superPil = new Super();
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (pacman.isAlive) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    
    public void update() {
        pacman.update(keyH, pacman.posX, pacman.posY);
        ghost.update(keyH, pacman.posX, pacman.posY);
        ghost2.update(keyH, pacman.posX, pacman.posY);
    }

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
        ghost.draw(g2, blockSize);
        ghost2.draw(g2, blockSize);
        g2.dispose();
    }
}
