package maluevArtem.homework_3.homework_3_3;

import maluevArtem.homework_3.homework_3_2.GameProgress;
import maluevArtem.homework_3.homework_3_2.Saving;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

/**
 * Задача №3 Загрузка
 */

public class Loading {

    public static void main(String[] args) {

        GameProgress gameProgress = null;
        List<String> listUnpackFile;

        listUnpackFile = wtiteZip(Saving.getZip(), Saving.getWayDirSavegames());
        gameProgress = writeSaveFile(gameProgress, listUnpackFile.get(0));
        System.out.println(gameProgress);
    }

    public static List<String> wtiteZip(String zip, String wayUnpack) {
        List<String> listUnpackFile = new ArrayList<>();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(wayUnpack + name);
                listUnpackFile.add(wayUnpack + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listUnpackFile;
    }

    public static GameProgress writeSaveFile(GameProgress gameProgress, String file) {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
