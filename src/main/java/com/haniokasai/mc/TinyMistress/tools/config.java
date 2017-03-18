package com.haniokasai.mc.TinyMistress.tools;

import java.io.*;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * Created by hani on 2017/03/03.
 */
public class config {
    public String hostdir;
    public String webport;
    public String phardir;
    public String phpbindir;
    public String hostname;

    public config(){
        TinyLogger log = new TinyLogger();
        String conf_name ="TinyMistress.properties";
        if(!new File(conf_name).exists()) {
            Properties properties = new Properties();
            try {
                properties.setProperty("hostdir", "server-location");
                properties.setProperty("web-port", "9000");
                properties.setProperty("phardir", "phar-location");
                properties.setProperty("phpbindir", "bin-location");
                properties.setProperty("hostname", "localhost");
                properties.store(new FileOutputStream(conf_name), "cre8");
            } catch (Exception e) {
                log.elog(e.getMessage());
            }
            log.elog("設定を編集してください");
            System.exit(0);
        }else{
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(conf_name));
            } catch (IOException e) {
                log.elog(e.getMessage());
                log.elog("設定ファイル異常です");
                System.exit(1);
            }
            try {
                hostdir=properties.getProperty("hostdir");
                webport=properties.getProperty("web-port");
                phardir=properties.getProperty("phardir");
                phpbindir=properties.getProperty("phpbindir");
                hostname=properties.getProperty("hostname");
            }catch (MissingResourceException e){
                log.elog(e.getMessage());
                log.elog("設定ファイルを作り直してください");
                System.exit(1);
            }
        }
    }
}
