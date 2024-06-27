package sound;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SoundManager {
    private Sound bgmSound;
    private Sound sfxSound;
    private List<String> bgmTracks; // List of bgm tracks to play
    private List<String> sfxTracks;
    private String buttonClickedSound, buttonHoveredSound;

    public SoundManager() {
        this.bgmSound = new Sound();
        this.sfxSound = new Sound();
        this.bgmTracks = new ArrayList<>();
        this.sfxTracks = new ArrayList<>();
        this.buttonClickedSound = "";
        this.buttonHoveredSound = "";

    }

    public void importBGM(String directoryPath) {
        File folder = new File(directoryPath); // will create a file from the directory path
        File[] listOfFiles = folder.listFiles(); // list of all files in the directory

        if (listOfFiles != null) {
            Arrays.sort(listOfFiles);
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    bgmTracks.add(file.getPath());
                }
            }
        }

        System.out.println(bgmTracks);
    }

    public void importFX(String directoryPath) {
        File folder = new File(directoryPath); // will create a file from the directory path
        File[] listOfFiles = folder.listFiles(); // list of all files in the directory

        if (listOfFiles != null) {
            Arrays.sort(listOfFiles);
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    sfxTracks.add(file.getPath());
                    if (file.getPath().contains("buttonPressed")) {
                        buttonClickedSound = file.getPath();
                    } else if (file.getPath().contains("buttonHover")) {
                        buttonHoveredSound = file.getPath();
                    }
                }
            }
        }
        System.out.println(sfxTracks);
    }

    public void playBGM() {
        // will play random track
        if (!bgmTracks.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(bgmTracks.size()); //calculate size
            String randomTrack = bgmTracks.get(index);
            bgmSound.bgMusic(randomTrack);
        } else {
            System.out.println("No BGM tracks available to play.");
        }
    }

    public void stopBGM() {
        if (!bgmTracks.isEmpty()) {
            bgmSound.stopMusic();
        }

    }

    public void playPressed() {
        if (!buttonClickedSound.isEmpty()) {
            sfxSound.fx(buttonClickedSound);
        }
    }

    public void playScreenTransitionSound() {
        sfxSound.fx(sfxTracks.getFirst());
    }

    public void playHover() {
        if (!buttonHoveredSound.isEmpty()) {
            sfxSound.fx(buttonHoveredSound);
        }
    }

    public void playRoundWin() {
        sfxSound.fx(sfxTracks.get(3));
    }

    public void playRoundLose() {
        sfxSound.fx(sfxTracks.get(2));
    }


    public void setBGMVolume(float volume) {
        bgmSound.fadeVolume(volume, 1500);

    }
}
