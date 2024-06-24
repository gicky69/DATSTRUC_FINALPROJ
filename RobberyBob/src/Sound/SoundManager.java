package Sound;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {
    private Sound sound;
    private List<String> tracks; // List of tracks to play

    public SoundManager() {
        this.sound = new Sound();
        this.tracks = new ArrayList<>();
        importFX("RobberyBob/resources/sounds/footstep.wav");
        importBGM("");
    }

    private void importBGM(String directoryPath) {
        File folder = new File(directoryPath); // will create a file from the directory path
        File[] listOfFiles = folder.listFiles(); // list of all files in the directory

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    tracks.add(file.getPath());
                }
            }
        }
    }

    private void importFX(String directoryPath) {
        File folder = new File(directoryPath); // will create a file from the directory path
        File[] listOfFiles = folder.listFiles(); // list of all files in the directory

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    tracks.add(file.getPath());
                }
            }
        }
    }
}
