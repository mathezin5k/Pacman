package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends JFrame {
    private Font pacmanFont;

    public Menu() {
        setTitle("Pac-Man Menu");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        pacmanFont = loadFont("src/fonts/Pacfont-ZEBZ.ttf", 36f);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1));
        menuPanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Pac-Man", SwingConstants.CENTER);
        titleLabel.setFont(pacmanFont);
        titleLabel.setForeground(Color.YELLOW);
        menuPanel.add(titleLabel);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(pacmanFont.deriveFont(18f)); // Fonte menor para os botões
        customizeButton(startButton);
        startButton.addActionListener(e -> startGame());
        menuPanel.add(startButton);

        JButton highscoresButton = new JButton("Highscores");
        highscoresButton.setFont(pacmanFont.deriveFont(18f));
        customizeButton(highscoresButton);
        highscoresButton.addActionListener(e -> showHighscores());
        menuPanel.add(highscoresButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(pacmanFont.deriveFont(18f));
        customizeButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        menuPanel.add(exitButton);

        add(menuPanel, BorderLayout.CENTER);
    }

    private Font loadFont(String filePath, float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size); // Fallback para Arial
        }
    }

    private void customizeButton(JButton button) {
        button.setForeground(Color.YELLOW);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }

    private void startGame() {
         JFrame gameFrame = new JFrame("Pac-Man Game");
        GamePanel gamePanel = new GamePanel();

        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        gamePanel.startGameThread();

        this.dispose();

    }

    private void showHighscores() {
        HighscoreManager highscoreManager = new HighscoreManager();
        highscoreManager.sortHighscores();

        ArrayList<HighScore> highscores = highscoreManager.getHighscores();
        StringBuilder sb = new StringBuilder("<html><h1 style='color:yellow;'>Highscores</h1>");
        int rank = 1;

        for (HighScore hs : highscores) {
            sb.append("<p style='color:white;'>").append(rank).append(". ").append(hs.getPlayerName())
              .append(": ").append(hs.getScore()).append("</p>");
            rank++;
            if (rank > 10) break; // Apenas o top 10
        }
        sb.append("</html>");

        JFrame highscoreFrame = new JFrame("Highscores");
        highscoreFrame.setSize(400, 300);
        highscoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        highscoreFrame.setLocationRelativeTo(null);

        JPanel highscorePanel = new JPanel();
        highscorePanel.setBackground(Color.BLACK);
        highscorePanel.setLayout(new BorderLayout());

        JLabel highscoresLabel = new JLabel(sb.toString());
        highscoresLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highscoresLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        highscorePanel.add(highscoresLabel, BorderLayout.CENTER);

        highscoreFrame.add(highscorePanel);
        highscoreFrame.setVisible(true);
    }
}
