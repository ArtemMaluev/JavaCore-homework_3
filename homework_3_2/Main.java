package maluevArtem.homework_3.homework_3_2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.*;

/**
 *  Задача №2 Сохранение
 */

public class Main {

    static StringBuilder strLog = new StringBuilder();

    public static void main(String[] args) throws FileNotFoundException {

        GameProgress save1 = new GameProgress(100, 2, 5, 15.2);
        GameProgress save2 = new GameProgress(75, 5, 12, 55.4);
        GameProgress save3 = new GameProgress(120, 10, 30, 98.8);

        String[] listFile = new String[3];
        listFile[0] = "D://JavaCourse/HomeWork_JavaCore/src/maluevArtem/homework_3/homework_3_1/Games/savegames/save1.dat";
        listFile[1] = "D://JavaCourse/HomeWork_JavaCore/src/maluevArtem/homework_3/homework_3_1/Games/savegames/save2.dat";
        listFile[2] = "D://JavaCourse/HomeWork_JavaCore/src/maluevArtem/homework_3/homework_3_1/Games/savegames/save3.dat";

        String[] listNameFile = new String[3];
        listNameFile[0] = "save1.dat";
        listNameFile[1] = "save2.dat";
        listNameFile[2] = "save3.dat";

        FileOutputStream fos1 = new FileOutputStream(listFile[0]);
        FileOutputStream fos2 = new FileOutputStream(listFile[1]);
        FileOutputStream fos3 = new FileOutputStream(listFile[2]);

        String zip = "D://JavaCourse/HomeWork_JavaCore/src/maluevArtem/homework_3/homework_3_1/Games/savegames/saveZip.zip";

        saveGame(fos1, save1);
        saveGame(fos2, save2);
        saveGame(fos3, save3);

        zipFiles(zip, listFile, listNameFile);
        log();
    }

    public static void saveGame(FileOutputStream fos, GameProgress save) {
        try (fos; ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
            strLog.append("Файл ").append(save).append(" был создан\n\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zip, String[] listFile, String[] listNameFile) {
        try {
            FileOutputStream fosZip = new FileOutputStream(zip);
            strLog.append("Архив saveZip.zip создан\n\n");
            ZipOutputStream zout = new ZipOutputStream(fosZip);
            for (int i = 0; i < listFile.length; i++) {
                FileInputStream fis = new FileInputStream(listFile[i]);
                ZipEntry entry = new ZipEntry(listNameFile[i]);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
                strLog.append("Файл ").append(listNameFile[i]).append(" помещен в архив\n\n");
                try {
                    Files.delete(Paths.get(listFile[i]));
                    strLog.append("Исходный файл ").append(listNameFile[i]).append(" удален\n\n");
                }  catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            zout.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void log() {
        File fileTemp = new File("D://JavaCourse/HomeWork_JavaCore/src/maluevArtem/homework_3/homework_3_2",
                "temp.txt");
        try {
            if (fileTemp.createNewFile()) {
                strLog.append("Файл temp.txt был создан\n\n");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter(fileTemp, false)) {
            writer.write(strLog.toString());
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
