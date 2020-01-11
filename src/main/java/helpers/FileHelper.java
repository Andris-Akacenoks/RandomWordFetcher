package helpers;

import fetcher.RandomWordException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Scanner;

public class FileHelper {

    public String createNew() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String name = String.valueOf(ts);
        String fullFileName = "result/" + name + ".txt";
        try {
            File file = new File(fullFileName);
            if (file.createNewFile()) {
                return fullFileName;
            } else {
                System.out.println("File '" + fullFileName + "'already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String path, String content) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(String path) {
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                content.append(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public boolean delete(String path) {
        File file = new File(path);
        return file.delete();
    }
}
