package pacman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class HighscoreManager {
        private ArrayList<HighScore> highscores;
        private final String filePath = "highscores.txt";

        public HighscoreManager() {
            highscores = new ArrayList<>();
            loadHighscores();
        }

        public void addHighscore(String playerName, int score) {
            highscores.add(new HighScore(playerName, score));
            saveHighscores();
        }

        public ArrayList<HighScore> getHighscores() {
            return highscores;
        }

        private void saveHighscores() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (HighScore hs : highscores) {
                    writer.write(hs.getPlayerName() + "," + hs.getScore());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void loadHighscores() {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    highscores.add(new HighScore(name, score));
                }
            } catch (IOException e) {
                highscores = new ArrayList<>();
            }
        }

        public void sortHighscores() {
            highscores.sort(Comparator.comparingInt(HighScore::getScore).reversed());
        }
    }