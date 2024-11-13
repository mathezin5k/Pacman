package pacman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Comparator;
import javax.imageio.ImageIO;

public class Fantasmas extends Personagens implements Runnable {
    BufferedImage ghost1, ghost2, ghost3, ghost4;
    BufferedImage currentImage;
    public int color;
    private int[][] map ={ //28x31
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 4, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 4, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
};
    private int pacX, pacY; // Coordenadas do Pac-Man

    public Fantasmas(int startX, int startY, int speed, int color) {
        super(startX, startY, speed);
        this.color = color;
        getGhostImage();
        new Thread(this).start(); // Inicia a thread do fantasma
    }
    
    // Define a posição do Pac-Man para seguir
    public void setPacmanPosition(int pacX, int pacY) {
        this.pacX = pacX;
        this.pacY = pacY;
    }
    
    // Lógica de movimento dos fantasmas executada na thread
    @Override
    public void run() {
        while (true) {
            update(null, pacX, pacY); // Atualiza a posição do fantasma com base na posição do Pac-Man
            try {
                Thread.sleep(100); // Define o intervalo de atualização do movimento dos fantasmas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    // Atualiza a posição do fantasma com base na busca A*
    @Override
    public void update(KeyHandler keyH, int pacX, int pacY) {
        int[] nextMove = findPath(posX / 24, posY / 24, pacX / 24, pacY / 24); // Converte para coordenadas de grid
        
        if (nextMove != null) { // Se há um caminho disponível
            posX += nextMove[0] * speed;
            posY += nextMove[1] * speed;
        }
    }

    // Algoritmo A* para encontrar o caminho mais curto até o Pac-Man
    private int[] findPath(int startX, int startY, int goalX, int goalY) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));
        
        openList.add(new Node(startX, startY, null, 0, heuristic(startX, startY, goalX, goalY)));
        
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current.x == goalX && current.y == goalY) {
                return getDirection(current); // Retorna a direção do próximo passo
            }
            visited[current.y][current.x] = true;
            
            for (int[] dir : new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}) { // Cima, baixo, esquerda, direita
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];
                
                if (nx >= 0 && nx < map[0].length && ny >= 0 && ny < map.length &&
                    map[ny][nx] == 0 && !visited[ny][nx]) { // Verifica se é caminho livre e não visitado
                    int gCost = current.gCost + 1;
                    int hCost = heuristic(nx, ny, goalX, goalY);
                    openList.add(new Node(nx, ny, current, gCost, hCost));
                }
            }
        }
        return null; // Caminho não encontrado
    }

    private int[] getDirection(Node node) {
        while (node.parent != null && node.parent.parent != null) {
            node = node.parent;
        }
        return new int[] { node.x - posX / 24, node.y - posY / 24 }; // Retorna direção
    }

    // Heurística Manhattan para A*
    private int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    // Carrega imagens dos fantasmas
    public void getGhostImage(){
        try {
            ghost1 = ImageIO.read(getClass().getResource("/pacman-art/blinky.png"));
            ghost2 = ImageIO.read(getClass().getResource("/pacman-art/inky.png"));
            ghost3 = ImageIO.read(getClass().getResource("/pacman-art/clyde.png"));
            ghost4 = ImageIO.read(getClass().getResource("/pacman-art/pinky.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }    

    @Override
    public void draw(Graphics2D g2, int blockSize) {
        switch (this.color) {
            case 0 -> currentImage = ghost1;
            case 1 -> currentImage = ghost2;
            case 2 -> currentImage = ghost3;
            default -> currentImage = ghost4;
        }
        g2.drawImage(currentImage, posX, posY, blockSize, blockSize, null);
    }

    // Classe Node para armazenar informações do A*
    private static class Node {
        int x, y;
        Node parent;
        int gCost, hCost, fCost;

        Node(int x, int y, Node parent, int gCost, int hCost) {
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.gCost = gCost;
            this.hCost = hCost;
            this.fCost = gCost + hCost;
        }
    }
}
