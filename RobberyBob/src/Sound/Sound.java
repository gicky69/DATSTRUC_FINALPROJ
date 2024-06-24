package Sound;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Sound {
    Clip clipFX, clipBGM;
    int gameBoardMusic;

    public void sound(String filepath) { //sound("link")
        try {

            Path path = Paths.get(filepath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path.toFile());

            clipFX = AudioSystem.getClip();
            clipFX.open(audioInputStream);

            // Set volume
            FloatControl setVolume = (FloatControl) clipFX.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume.setValue(-8.0f);

            clipFX.start();

            // Adjust the volume while the clip is playing

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error in playing sound/music.");
        }
    }
    public void bgMusic(String filepath) {
        try {
            Path path = Paths.get(filepath);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path.toFile());
            System.out.println(path);

            clipBGM = AudioSystem.getClip();
            clipBGM.open(audioInputStream);

            // Set volume
            FloatControl setVolume = (FloatControl) clipBGM.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume.setValue(-12.0f);

            clipBGM.start();
            clipBGM.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error in playing sound/music.");
        }
    }
    public void stopMusic() {

        if (clipBGM != null) {
            clipBGM.stop();
            clipBGM.close();

        } else {
            System.out.println("clipBGM is null. Unable to stop and close.");
        }
    }
    public int randomMusicGen() {
        Random rand = new Random();
        gameBoardMusic = rand.nextInt(6);

        return gameBoardMusic;
    }
}