package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RenameAllureJsonFiles {

    private static final File folder = new File("target/allure-results");
    private static final Set<String> existingFileNames = new HashSet<>();

    // Testten önce çağrılacak: var olan dosyaları hafızaya al
    public static void rememberExistingFiles() {
        if (folder.exists()) {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    existingFileNames.add(file.getName());
                }
            }
        }
    }

    public static void renameOnlyNewFiles(String prefix) {
        if (!folder.exists()) return;

        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) return;

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        int counter = 1;

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".json") && !existingFileNames.contains(file.getName())) {
                String newName;
                if (file.getName().contains("-result")) {
                    newName = String.format("%s_%s-%d-result.json", prefix, timestamp, counter++);
                } else if (file.getName().contains("-container")) {
                    newName = String.format("%s_%s-%d-container.json", prefix, timestamp, counter++);
                } else {
                    newName = String.format("%s_%s-%d.json", prefix, timestamp, counter++);
                }

                File newFile = new File(folder, newName);
                try {
                    Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
