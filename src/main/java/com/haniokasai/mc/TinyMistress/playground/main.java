package com.haniokasai.mc.TinyMistress.playground;

import com.haniokasai.mc.TinyMistress.srv.OutputLogger;
import com.haniokasai.mc.TinyMistress.tools.TinyLogger;
import com.haniokasai.mc.TinyMistress.tools.config;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Scanner;



/**
 * Created by hani on 2017/03/16.
 */
public class main {


    private static config conf;
    private Process process;
    private OutputStream stdin;
    static main m;


    public static void test() {
       m = new main();
        m.start();
    }

    void start(){
        conf = new config();

        String[] cmd=null;
        cmd=new String[]
                {
                        conf.phpbindir,conf.phardir
                };

        ProcessBuilder builder = new ProcessBuilder(cmd);
        builder.redirectErrorStream(true);
        builder.directory(new File(conf.hostdir));

        try
        {
            process = builder.start();
            stdin= process.getOutputStream();
            InputStream is = process.getInputStream();

            OutputLogger ol = new OutputLogger(is,process);
            ol.start();
        } catch(Exception e)
        {
            TinyLogger logger = new TinyLogger();
            logger.elog(e.getMessage());
            // return false;
        }
        long pid = -1;

        try {
            Field f = process.getClass().getDeclaredField("pid");
            f.setAccessible(true);
            pid = f.getLong(process);
            f.setAccessible(false);

        } catch (Exception e) {
            pid = -1;
            // return false;
        }
    }


    public void execcmd(String cmd){
        BufferedOutputStream writer =new BufferedOutputStream(stdin);
        try {
            writer.write((cmd+ System.getProperty("line.separator")).getBytes());
            writer.flush();
        } catch (IOException e) {
            if(cmd.equals("exit")){
                System.exit(0);
            }
            e.printStackTrace();
        }
    }

    public void stopsrv(){
        execcmd("stop");
    }

    public static void restart(){
        (new TinyLogger()).elog("["+"a"+"]is restarting now!");
        m.stopsrv();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m.process.destroy();
        m.start();
    }
}
