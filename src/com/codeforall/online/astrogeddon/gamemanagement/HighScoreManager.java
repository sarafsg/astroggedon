package com.codeforall.online.astrogeddon.gamemanagement;

import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String HIGHSCORE_FILE = Game.PREFIX + "highscores.txt";
    private ArrayList<Integer> highScores;

    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }

    private void loadHighScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                highScores.add(Integer.parseInt(line));
            }
            Collections.sort(highScores, Collections.reverseOrder());
        } catch (IOException e) {
            // If file does not exist, initialize with empty list
            highScores = new ArrayList<>();
        }
    }

    public void saveHighScores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            for (int score : highScores) {
                bw.write(String.valueOf(score));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addScore(int score) {
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());
        if (highScores.size() > 3) {
            highScores.remove(highScores.size() - 1); // Keep only top 3 scores
        }
        saveHighScores();
    }

    public List<Integer> getHighScores() {
        return highScores;
    }
}
