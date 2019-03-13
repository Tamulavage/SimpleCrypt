
import java.io.*;
import java.util.ArrayList;

import static java.lang.Character.*;

public class ROT13 {
    private Integer offset;
    private String alphaOriginal = "abcdefghijklmnopqrstuvwxyz";
    private String alphaRotated;
    ArrayList<String> fileStrings = new ArrayList<>();
    ArrayList<String> fileStringsEncrypt = new ArrayList<>();
    ArrayList<String> fileStringsDecrypt = new ArrayList<>();

    ROT13(Character cs, Character cf) {
        this.offset = cf - cs;
        this.alphaRotated = rotate(alphaOriginal, offset);
    }

    ROT13() {
        this.offset = 0;
        this.alphaRotated = rotate(alphaOriginal, offset);
    }


    public String crypt(String text) throws UnsupportedOperationException {
        String retVal = "";
        char temp;
        for (int i = 0; i < text.length(); i++) {

            if (isAlphabetic(text.charAt(i))) {
                temp = text.toLowerCase().charAt(i);
                temp = (char) ((temp + offset - (int) 'a') % 26 + (int) 'a');
            } else {
                temp = text.charAt(i);
            }
            retVal = retVal + Character.toString(temp);

        }

        retVal = retVal.toUpperCase().substring(0, 1) + retVal.substring(1);

        return retVal;

    }

    public String encrypt(String text) {
        return crypt(text);
        /*
        String retVal = "";
        char temp;
        for (int i = 0; i < text.length(); i++) {

            if (isAlphabetic(text.charAt(i))) {
                temp = text.toLowerCase().charAt(i);
                temp = (char) ((temp + offset - (int) 'a') % 26 + (int) 'a');
            } else {
                temp = text.charAt(i);
            }
            retVal = retVal + Character.toString(temp);

        }

        retVal = retVal.toUpperCase().substring(0, 1) + retVal.substring(1);

        return retVal;
        */

    }

    public String decrypt(String text) {
        String retVal = "";
        char temp;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isAlphabetic(text.charAt(i))) {
                temp = text.toLowerCase().charAt(i);
                temp = (char) ((temp - offset - (int) 'a' + 26) % 26 + (int) 'a');
            } else {
                temp = text.charAt(i);
            }
            retVal = retVal + Character.toString(temp);

        }

        retVal = retVal.toUpperCase().substring(0, 1) + retVal.substring(1);

        return retVal;
    }


    public static String rotate(String s, Character c) {
        int i = (c - 65) % s.length();
        return s.substring(i) + s.substring(0, i);
    }

    public static String rotate(String s, Integer i) {
        return s.substring(i) + s.substring(0, i);
    }

    public void loadDataFromFile(String filePath) throws IOException {
        BufferedReader fileInput = new BufferedReader(new FileReader(filePath));
        String currentLine;
        try {

            while ((currentLine = fileInput.readLine()) != null) {
                fileStrings.add(currentLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void printDataFromFile(String filePath) throws IOException {
        BufferedWriter fileOutput = new BufferedWriter(new FileWriter(filePath));
        String currentLine;
        if (fileStringsEncrypt.size() > 0) {
            try {

                for (String s : fileStringsEncrypt) {
                    fileOutput.append(s);
                }
                fileOutput.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public void encryptLoadedFile() {
        for(String s: fileStrings){
            fileStringsEncrypt.add(encrypt(s));
        }
    }

    public void decryptLoadedFile() {
        for(String s: fileStringsEncrypt){
            fileStringsDecrypt.add(encrypt(s));
        }
    }
}
