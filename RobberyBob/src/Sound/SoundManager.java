package Sound;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {
    private Sound sound;
    private List<String> bgmTracks; // List of bgm tracks to play
    private List<String> sfxTracks;

    public SoundManager() {
        this.sound = new Sound();
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
    }

    public void playBGM() {
        for (String track : bgmTracks) {
            sound.bgMusic(track);
        }
    }
}
