package sound;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoundManager {
    private Sound bgmSound;
    private Sound sfxSound;
    private List<String> bgmTracks; // List of bgm tracks to play
    private List<String> sfxTracks;

    public SoundManager() {
        this.bgmSound = new Sound();
        this.sfxSound = new Sound();
        this.bgmTracks = new ArrayList<>();
        this.sfxTracks = new ArrayList<>();
    }

    public void importBGM(String directoryPath) {
        File folder = new File(directoryPath); // will create a file from the directory path
        File[] listOfFiles = folder.listFiles(); // list of all files in the directory

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    bgmTracks.add(file.getPath());
                }
            }
        }

        Collections.reverse(bgmTracks);
        System.out.println(bgmTracks);
    }

    public void importFX(String directoryPath) {
        File folder = new File(directoryPath); // will create a file from the directory path
        File[] listOfFiles = folder.listFiles(); // list of all files in the directory

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    sfxTracks.add(file.getPath());
                }
            }
        }
        Collections.reverse(sfxTracks);
        System.out.println(sfxTracks);
    }

    public void playBGM() {
        for (String track : bgmTracks) {
            bgmSound.bgMusic(track);
        }
    }

    public void playButtonSound() {
        sfxSound.fx(sfxTracks.getFirst());
    }

    public void playScreenTransitionSound() {
        sfxSound.fx(sfxTracks.get(1));
    }

    public void setBGMVolume(float volume) {
        bgmSound.fadeVolume(volume, 1500);

    }
}
