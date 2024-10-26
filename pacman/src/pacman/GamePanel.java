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
    final int larguraTela = blockSize * tamColuna;
    final int alturaTela = blockSize * tamLinha;
    
    int[][] labirinto = { // 1=parede 2-pilulas 2-caminhos livres  28x31
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 1, 1, 2, 2, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 4, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
        {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 4, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 1, 1, 2, 2, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
};


    
    int FPS = 60;
    boolean isAlive = true;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Pacman pacman;
    Fantasmas ghost;
    Fantasmas ghost2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        pacman = new Pacman(100, 100, 4);
        ghost = new Fantasmas(200, 200, 2);
        ghost2 = new Fantasmas(150, 150, 2);
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

        while (isAlive) {
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
        
        for(int i = 0; i < tamLinha; i++){
            for(int j = 0; j < tamColuna; j++){
                if(labirinto[i][j] == 1){
                    //parede
                    g.setColor(Color.BLUE);
                    g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                }else if(labirinto[i][j] == 2){
                    //pilula
                    g.setColor(Color.WHITE);
                    g.fillOval(j * blockSize + blockSize/4, i * blockSize + blockSize/4, blockSize/2, blockSize/2);
                }else if(labirinto[i][j] == 0){
                    //vazio 
                    g.setColor(Color.BLACK);
                    g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                }         
            }
        }
        
        Graphics2D g2 = (Graphics2D) g;
        pacman.draw(g2, blockSize);
        ghost.draw(g2, blockSize);
        ghost2.draw(g2, blockSize);
        g2.dispose();
    }
}
