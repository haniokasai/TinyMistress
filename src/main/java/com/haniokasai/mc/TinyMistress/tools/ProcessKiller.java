package com.haniokasai.mc.TinyMistress.tools;

import java.io.IOException;

import static com.haniokasai.mc.TinyMistress.tools.PlatformCheck.isLinux;
import static com.haniokasai.mc.TinyMistress.tools.PlatformCheck.isMac;
import static com.haniokasai.mc.TinyMistress.tools.PlatformCheck.isWindows;

/**
 * Created by hani on 2017/03/25.
 */
public class ProcessKiller {
    String os;
    ProcessKiller(){
        TinyLogger log = new TinyLogger();
        if(isWindows()){
            os ="win";
            log.elog("windowsです");
        }else if(isLinux()){
            os ="li";
            log.elog("linuxです");
        }else if(isMac()){
            os="mac";
            log.elog("macです");
        }else{
            log.elog("わからないosです");
            System.exit(1);
        }
    }

    void killProcess(long pid){
        Runtime r = Runtime.getRuntime();
        TinyLogger log = new TinyLogger();
        try {
            switch (os) {
                case "win": {
                    r.exec(new String[]{"taskkill", "/pid", String.valueOf(pid),"/F"});
                    break;
                }
                case "li":{

                }
                case "mac":{
                    r.exec(new String[]{"kill", "-9", String.valueOf(pid)});
                    break;
                }
            }
        }catch (IOException e){
            log.elog(e.getMessage());
            log.elog("os:"+os+"にて、pid:"+pid+"を正常に終了できませんでした");
        }
    }

}
