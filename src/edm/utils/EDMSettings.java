package edm.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EDMSettings {

    private EDMSettings() {}

    public static void saveSettings(Map<String, String> settings) {
        try {
            FileOutputStream fout = new FileOutputStream("settings.edm");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(settings);
            out.close();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> loadSettings() {
        Object result;
        try {
            FileInputStream fin = new FileInputStream("settings.edm");
            ObjectInputStream in = new ObjectInputStream(fin);
            result = in.readObject();
            in.close();
            fin.close();
            return (HashMap<String, String>) result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
