package com.vitalsource.lib;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexis Espinoza on 7/19/16.
 * Reads the elements of a file into an ArrayList
 */
public class FileOperation {
    private static  BufferedReader br = null;
    public static ArrayList<String> readFileIntoArrayList (String fileName){
        ArrayList<String> arrayListFile=new ArrayList<String>();
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {
                arrayListFile.add(i,sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return arrayListFile;
    }

    public static HashMap<String, Object> readFileIntoHashMap (String fileName,int option){
        HashMap<String, Object> hashMap=new HashMap<String, Object>();
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {
                switch (option) {
                    case 0: hashMap.put(sCurrentLine, sCurrentLine);
                            break;
                    case 1: hashMap.put(sCurrentLine, i);
                        break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return hashMap;
    }
    public static void outPutFile(String content, String fileName) {
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Files/"+fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            System.out.println("New file created at " +System.getProperty("user.dir") + "/src/Files/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}