package com.haniokasai.mc.TinyMistress.srv;

import com.haniokasai.mc.TinyMistress.tools.TinyLogger;
import com.haniokasai.mc.TinyMistress.tools.config;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import static com.haniokasai.mc.TinyMistress.tools.PlatformCheck.isWindows;
import static com.haniokasai.mc.TinyMistress.tools.pid_list.addpid;
import static com.haniokasai.mc.TinyMistress.tools.pid_list.delpid;
import static cz.chalda.pid.getter.GetPid.getUnixPid;
import static cz.chalda.pid.getter.GetPid.getWindowsPid;


/**
 * Created by hani on 2017/03/01.
 */
public class server{
    String srvid;
    long pid;
    OutputStream stdin;
    String basedir;
    String srcdir;
    public Process process;
    private Boolean alive;
    public InputStream is;
    public ArrayList<String> srv_log = new ArrayList<>();
    OutputLogger ol;


    public server(){

    }

    public void start(){
        config conf = new config();

        String[] cmd=null;
        cmd=new String[]
                {
                        conf.phpbindir, conf.phardir
                };

        ProcessBuilder builder = new ProcessBuilder(cmd);
        builder.redirectErrorStream(true);
        builder.directory(new File(conf.hostdir));
        try
        {
            process = builder.start();
            stdin= process.getOutputStream();
            is = process.getInputStream();

            ol = new OutputLogger(is,process);
            ol.start();
        } catch(Exception e)
        {
            TinyLogger logger = new TinyLogger();
            logger.elog(e.getMessage());
            // return false;
        }
        pid = -1;


        if(isWindows()){
            pid = getWindowsPid(process);
        }else{
            pid = getUnixPid(process);
        }
        addpid("a",pid);
        try {


            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    while(process.isAlive()){
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    delpid("a");
                }
            });
            t1.start();
        } catch (Exception e) {
            pid = -1;
            TinyLogger logger = new TinyLogger();
            logger.elog(e.getMessage());
            // return false;
        }

    }

    public void restart(){
        (new TinyLogger()).elog("["+"a"+"]is restarting now!");
        stopsrv();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        process.destroy();
        alive=false;
        ol.stop();//depricated
        start();
    }

    public void stopsrv(){
        execcmd("stop");
    }

    public void kill(){
        alive=false;
        process.destroy();
        ol.stop();//depricated
    }

    public void execcmd(String cmd){
        BufferedOutputStream writer =new BufferedOutputStream(stdin);
        try {
            System.out.println(cmd);
            writer.write((cmd+ System.getProperty("line.separator")).getBytes("UTF-8"));
            writer.flush();
        } catch (IOException e) {
            if(cmd.equals("exit")){
                System.exit(0);
            }
            //e.printStackTrace();
        }
    }


}
