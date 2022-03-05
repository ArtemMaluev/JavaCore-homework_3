package maluevArtem.homework_3.homework_3_2;

import maluevArtem.homework_3.homework_3_1.Installation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.*;

/**
 *  Задача №2 Сохранение
 */

public class Saving {

    static StringBuilder strLogSav = new StringBuilder();
    static String wayDirSavegames = Installation.getWayDirGames() + "/savegames";
    static String zip = wayDirSavegames + "/saveZip.zip";

    public static void main(String[] args) {

        GameProgress save1 = new GameProgress(100, 2, 5, 15.2);
        GameProgress save2 = new GameProgress(75, 5, 12, 55.4);
        GameProgress save3 = new GameProgress(120, 10, 30, 98.8);

        String[] listNameSaveFile = new String[3];
        listNameSaveFile[0] = "/save1.dat";
        listNameSaveFile[1] = "/save2.dat";
        listNameSaveFile[2] = "/save3.dat";

        saveGame(wayDirSavegames + listNameSaveFile[0], save1);
        saveGame(wayDirSavegames + listNameSaveFile[1], save2);
        saveGame(wayDirSavegames + listNameSaveFile[2], save3);

        zipFiles(zip, wayDirSavegames, listNameSaveFile);
        logSaving();
    }

    public static void saveGame(String waySaveFile, GameProgress save) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(waySaveFile))) {
            oos.writeObject(save);
            strLogSav.append("Файл ").append(save).append(" был создан\n\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zip, String wayDirSavegames, String[] listNameSaveFile) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip))) {
            strLogSav.append("Архив saveZip.zip создан\n\n");
            for (int i = 0; i < listNameSaveFile.length; i++) {
                FileInputStream fis = new FileInputStream(wayDirSavegames + listNameSaveFile[i]);
                ZipEntry entry = new ZipEntry(listNameSaveFile[i]);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
                strLogSav.append("Файл ").append(listNameSaveFile[i]).append(" помещен в архив\n\n");
                try {
                    Files.delete(Paths.get(wayDirSavegames + listNameSaveFile[i]));
                    strLogSav.append("Исходный файл ").append(listNameSaveFile[i]).append(" удален\n\n");
                }  catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void logSaving() {
        File fileTemp = new File("D://JavaCourse/HomeWork_JavaCore/src/maluevArtem/homework_3/homework_3_2",
                "temp.txt");
        try {
            if (fileTemp.createNewFile()) {
                strLogSav.append("Файл temp.txt был создан\n\n");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter(fileTemp, false)) {
            writer.write(strLogSav.toString());
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getZip() {
        return zip;
    }

    public static String getWayDirSavegames() {
        return wayDirSavegames;
    }
}
