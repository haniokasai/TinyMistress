package com.haniokasai.mc.TinyMistress.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by hani on 2017/03/25.
 */
public class pid_list {
    private static String conf_name;
    public HashMap<String,Long> pidli;
    static Properties properties;

    public static void mkpid_list(){

        TinyLogger log = new TinyLogger();
        conf_name ="pid_list.properties";


        if(new File(conf_name).exists()) {
            InputStream is = null;
            try {
                is = new FileInputStream(conf_name);
            } catch (FileNotFoundException e) {
                log.elog(e.getMessage());
                log.elog("pid設定ファイルがありません");
                System.exit(1);
            }

            properties = new Properties();
            try {
                properties.load(is);
            } catch (Exception e) {
                log.elog(e.getMessage());
                log.elog("pid設定ファイル異常です");
                System.exit(1);
            }

            for (final String name: properties.stringPropertyNames()) {
                new ProcessKiller().killProcess(Long.parseLong(properties.getProperty(name)));
            }

            if(!new File(conf_name).delete()){
                log.elog("pid設定ファイルを消せません");
            }
        }else{
            Properties properties = new Properties();
            properties.setProperty("foo", "0");
            try {
                properties.store(new FileOutputStream(conf_name), "cre8");
            } catch (IOException e) {
                log.elog(e.getMessage());
                log.elog("pid設定ファイルがありません");
                System.exit(1);
            }
        }

    }

    public static void delpid(String name){
        properties.remove(name);
        try {
            properties.store(new FileOutputStream(conf_name), "cre8");
        } catch (IOException e) {
            TinyLogger log = new TinyLogger();
            log.elog(e.getMessage());
            log.elog("pid設定ファイルがありません");
        }
    }

    public static void addpid(String name,long pid){
        properties.setProperty(name, String.valueOf(pid));
        try {
            properties.store(new FileOutputStream(conf_name), "cre8");
        } catch (IOException e) {
            TinyLogger log = new TinyLogger();
            log.elog(e.getMessage());
            log.elog("pid設定ファイルがありません");
        }
    }
}
