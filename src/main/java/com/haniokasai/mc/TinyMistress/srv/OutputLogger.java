package com.haniokasai.mc.TinyMistress.srv;

import com.haniokasai.mc.TinyMistress.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hani on 2017/03/02.
 */
public class OutputLogger extends Thread {
    private final InputStream is;
    private final Process pr;

    public OutputLogger(InputStream is,Process pr) {
        this.is = is;
        this.pr = pr;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            try {
                for(;;) {
                    String line = br.readLine();
                    if (line == null) break;
                    System.out.println(line);
                    try {
                        Main.servers.get("a").srv_log.add(line);
                        int len = Main.servers.get("a").srv_log.size();
                        while (len > 150) {
                            Main.servers.get("a").srv_log.remove(0);
                            --len;
                        }
                    }catch (NullPointerException ignored){
                        System.out.println("a");
                    }
                    if(line.matches(".*"+"Stopping other threads"+".*")){
                        pr.destroy();
                        break;
                    }
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
