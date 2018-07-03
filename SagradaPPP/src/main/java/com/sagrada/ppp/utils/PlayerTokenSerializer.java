package com.sagrada.ppp.utils;

import com.sagrada.ppp.model.JoinGameResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Saves an instance of JoinGameResult into a file.
 * Used to reconnect to a game after a sudden disconnection(eg. network disconnection)
 */
public class PlayerTokenSerializer {

    private PlayerTokenSerializer(){
        super();
    }
    public static boolean isTokenPresent() {
        File f = new File(StaticValues.TOKEN_URL);
        return f.exists() && !f.isDirectory();
    }

    public static void serialize(JoinGameResult joinGameResult) {
        if(isTokenPresent()) deleteToken();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // write object to file
            fos = new FileOutputStream(StaticValues.TOKEN_URL);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(joinGameResult);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null)
                    oos.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void deleteToken() {
        try {
            Files.deleteIfExists(Paths.get(StaticValues.TOKEN_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JoinGameResult deserialize() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        JoinGameResult joinGameResult = null;
        try {
            fis = new FileInputStream(StaticValues.TOKEN_URL);
            ois = new ObjectInputStream(fis);
            joinGameResult = (JoinGameResult) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return joinGameResult;
    }

}
