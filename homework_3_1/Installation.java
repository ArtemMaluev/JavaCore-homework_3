package maluevArtem.homework_3.homework_3_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  Задача №1 Установка
 */

public class Installation {

    static StringBuilder strLogInstall = new StringBuilder();
    static String wayDirGames = "D://JavaCourse/HomeWork_JavaCore/src/maluevArtem/homework_3/homework_3_1/Games";

    public static void main(String[] args) {

        // Создаем корневую директорию Games
        File dirGames = new File(wayDirGames);
        if (dirGames.mkdir()) {
            strLogInstall.append("Директория Games создана\n" + "Путь к директории: ").append(dirGames).append("\n\n");
        }

        // Создаем в директории Games каталоги: src, res, savegames, temp
        File dirSrc = newDir(dirGames, "src");
        File dirRes = newDir(dirGames, "res");
        File dirSavegames = newDir(dirGames, "savegames");
        File dirTemp = newDir(dirGames, "temp");

        // Создаем в каталоге src подкаталоги: main, test
        File dirMain = newDir(dirSrc, "main");
        File dirTest = newDir(dirSrc, "test");

        // Создаем в каталоге res подкаталоги: drawables, vectors, icons
        File dirDrawables = newDir(dirRes,"drawables");
        File dirVectors = newDir(dirRes,"vectors");
        File dirIcons = newDir(dirRes,"icons");

        // Создаем в подкаталоге main файлы: Main.java, Utils.java
        File fileMain = newFile(dirMain,"Main.java");
        File fileUtils = newFile(dirMain,"Utils.java");

        //Создаем в подкаталоге temp файл: temp.txt
        File fileTemp = newFile(dirTemp,"temp.txt");

        // Запишем сообщения о создании файлов и каталогов в файл temp.txt
        logInstallation(fileTemp);
    }

    public static File newDir(File dir, String name) {
        File newDir = new File(dir, name);
        if (newDir.mkdir()) {
            strLogInstall.append("Каталог ").append(name).append(" создан\n")
                    .append("Путь к каталогу: ").append(newDir).append("\n\n");
        }
        return newDir;
    }

    public static File newFile(File file, String name) {
        File newFile = new File(file, name);
        try {
            if (newFile.createNewFile()) {
                strLogInstall.append("Файл ").append(name).append(" был создан\n")
                        .append("Путь к файлу: ").append(newFile).append("\n\n");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return newFile;
    }

    public static void logInstallation(File fileTemp) {
        try (FileWriter writer = new FileWriter(fileTemp, false)) {
            writer.write(strLogInstall.toString());
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getWayDirGames() {
        return wayDirGames;
    }
}
