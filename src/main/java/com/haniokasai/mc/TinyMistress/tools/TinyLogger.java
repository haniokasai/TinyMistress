package com.haniokasai.mc.TinyMistress.tools;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hani on 2017/03/01.
 */
public class TinyLogger {
    private static final String sep = System.getProperty("line.separator");

// --Commented out by Inspection START (2017/03/22 22:14):
//    public void nlog(String con){
//        System.out.printf("["+new Date(System.currentTimeMillis())+"][LowerLobby]:"+con+sep);
//    }
// --Commented out by Inspection STOP (2017/03/22 22:14)

    public void elog(String con){
        System.out.printf("["+new Date(System.currentTimeMillis())+"][LowerLobby]:"+con+sep);
        try{
            File file = new File("ll.log");
            FileWriter filewriter = new FileWriter(file, true);
            filewriter.write("["+new Date(System.currentTimeMillis())+"][LowerLobby]:"+con+sep);
            filewriter.close();
        }catch (IOException e){
            System.out.println("File writing trouble");
            System.out.println(e.getMessage());
        }
    }
}
