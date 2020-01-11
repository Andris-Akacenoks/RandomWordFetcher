package helpers;

import fetcher.RandomWordException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

public class FileHelper {

    public String createNew() {
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String name = String.valueOf(ts);
        String fullFileName = "result/" + name + ".txt";
        try {
            File file = new File(fullFileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + fullFileName);
                return fullFileName;
            } else {
                System.out.println("File '" + fullFileName + "'already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write (String path, String content) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
