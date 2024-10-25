package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int tamanhoBlocoOriginal = 16;
    final int escala = 2;
    final int blockSize = tamanhoBlocoOriginal * escala;
    
    final int tamColuna = 30;
    final int tamLinha = 20;
    final int larguraTela = blockSize * tamColuna;
    final int alturaTela = blockSize * tamLinha;
    
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
        ghost = new Fantasmas(200, 200, 1);
        ghost2 = new Fantasmas(150, 150, 1);
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
        pacman.update(keyH);
        ghost.update(keyH);
        ghost2.update(keyH);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        pacman.draw(g2, blockSize);
        ghost.draw(g2, blockSize);
        ghost2.draw(g2, blockSize);
        g2.dispose();
    }
}
