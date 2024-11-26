package pacman;

import java.util.PriorityQueue;
import java.util.Comparator;

public class Pathfinder {
    private int[][] map;

    public Pathfinder(int[][] map) {
        this.map = map;
    }

    public int[] findPath(int startX, int startY, int goalX, int goalY) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(Node::fCost));
        
        openList.add(new Node(startX, startY, null, 0, heuristic(startX, startY, goalX, goalY)));
        
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current.x == goalX && current.y == goalY) {
                return getDirection(current);
            }
            visited[current.y][current.x] = true;
            
            for (int[] direction : new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (isValidMove(newX, newY, current.x, current.y) && !visited[newY][newX]) {
                    int gCost = current.gCost + 1;
                    int hCost = heuristic(newX, newY, goalX, goalY);
                    openList.add(new Node(newX, newY, current, gCost, hCost));
                }
            }
        }
        return null;
    }

    private int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2); // Distância Manhattan
    }

    private boolean isValidMove(int x, int y, int currentX, int currentY) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length && map[y][x] == 0;
    }

    private int[] getDirection(Node node) {
        if (node.parent == null) {
            return null;
        }
        while (node.parent != null && node.parent.parent != null) {
            node = node.parent;
        }
        return new int[]{node.x - node.parent.x, node.y - node.parent.y};
    }

    private static class Node {
        int x, y;
        Node parent;
        int gCost, hCost;

        Node(int x, int y, Node parent, int gCost, int hCost) {
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.gCost = gCost;
            this.hCost = hCost;
        }

        int fCost() {
            return gCost + hCost;
        }
    }
}
