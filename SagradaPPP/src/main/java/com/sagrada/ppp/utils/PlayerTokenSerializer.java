package com.sagrada.ppp.utils;
import com.sagrada.ppp.model.JoinGameResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PlayerTokenSerializer {


    public static boolean isTokenPresent() {
        File f = new File(StaticValues.TOKEN_URL);
        return f.exists() && !f.isDirectory();
    }

    public static void serialize(JoinGameResult joinGameResult) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // write object to file
            fos = new FileOutputStream(StaticValues.TOKEN_URL);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(joinGameResult);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.close();
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
}
