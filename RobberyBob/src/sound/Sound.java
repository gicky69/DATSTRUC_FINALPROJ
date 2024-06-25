package sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Sound {
    Clip clipFX, clipBGM;
    int gameBoardMusic;


    public Sound() {



    }

    public void fx(String filepath) { //sound("link")
        try {

            Path path = Paths.get(filepath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path.toFile());

            clipFX = AudioSystem.getClip();
            clipFX.open(audioInputStream);

            // Set volume
            FloatControl setVolume = (FloatControl) clipFX.getControl(FloatControl.Type.MASTER_GAIN);
            if (filepath.contains("buttonHover")) {
                setVolume.setValue(-10.0f);
            } else {
                setVolume.setValue(-4.0f);
            }

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
            clipBGM = AudioSystem.getClip();
            clipBGM.open(audioInputStream);

            // Set volume
            FloatControl setVolume = (FloatControl) clipBGM.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume.setValue(0.0f);

            clipBGM.start();
            clipBGM.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error in playing sound/music.");
        }
    }

    public void setVolume(float volume) {
        if (clipBGM != null) {
            FloatControl volumeControl = (FloatControl) clipBGM.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }

    public void fadeVolume(float targetVolume, long durationMillis) {
        if (clipBGM != null) {
            FloatControl volumeControl = (FloatControl) clipBGM.getControl(FloatControl.Type.MASTER_GAIN);
            float currentVolume = volumeControl.getValue();
            float volumeStep = (targetVolume - currentVolume) / (durationMillis / 100); // Adjust volume every 100ms

            new Thread(() -> {
                while (Math.abs(volumeControl.getValue() - targetVolume) > Math.abs(volumeStep)) {
                    volumeControl.setValue(volumeControl.getValue() + volumeStep);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                volumeControl.setValue(targetVolume); // Ensure the target volume is accurately set
            }).start();
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