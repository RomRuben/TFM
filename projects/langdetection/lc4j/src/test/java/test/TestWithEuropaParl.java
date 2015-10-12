package test;

import languagedetection.LanguageDetection;
import utils.FileHandler;
import utils.PropertiesManager;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by ruben on 02/09/15.
 */
public class TestWithEuropaParl {

    static LanguageDetection languageDetection = new LanguageDetection();


    public static void main(String[] args) {

        Instant before = Instant.now();
        languageDetection.init(PropertiesManager.getInstance().getProfileDir());
        File filesdir = new File("/Users/ruben/Desktop/lang");

        int total = getFilesCount(filesdir);
        int welldetected = getFilesWellDetected(filesdir.listFiles());

        System.out.println("total: " + total + " well detected: " + welldetected + " in " + Duration.between(before, Instant.now()));
    }

    private static int getFilesWellDetected(File[] allLangsDir) {
        int detected = 0;

        for (File langdir : allLangsDir){
            detected += analyzeLangDir(langdir);
        }

        return detected;
    }

    private static int analyzeLangDir(File langdir) {
        String langName = langdir.getName();
        int docWellDetected = 0;

        for(File file : langdir.listFiles()){
            if (languageDetection.detect(FileHandler.readFileContent(file.getAbsolutePath())).equals(langName))
                docWellDetected++;
        }

        return docWellDetected;
    }


    public static int getFilesCount(File file) {
        File[] files = file.listFiles();
        int count = 0;
        for (File f : files)
            if (f.isDirectory())
                count += getFilesCount(f);
            else
                count++;

        return count;
    }

}
