package pacman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;

public class Sound {

    private long startSongTime = System.currentTimeMillis();
    private long timeLimitSongInMilliseconds;

    public void playSound(String soundFile, long time) {
        this.timeLimitSongInMilliseconds = time;

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                getClass().getResource("/sounds/" + soundFile))) {

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            long elapsedSongTime = System.currentTimeMillis() - startSongTime;

            if (elapsedSongTime >= timeLimitSongInMilliseconds) {
                startSongTime = System.currentTimeMillis();
                clip.start();
            }

        } catch (IOException e) {
            System.err.println("Erro ao carregar o som: " + soundFile);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
