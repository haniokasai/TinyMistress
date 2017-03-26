package com.haniokasai.mc.TinyMistress;
import com.haniokasai.mc.TinyMistress.srv.server;
import com.haniokasai.mc.TinyMistress.tools.TinyLogger;
import com.haniokasai.mc.TinyMistress.tools.config;
import com.haniokasai.mc.TinyMistress.web.WebSrv;

import java.util.HashMap;
import java.util.Map;

import static com.haniokasai.mc.TinyMistress.tools.pid_list.mkpid_list;

/**
 * Created by hani on 2017/03/01.
 */
public class Main{
    public static final Map<String,server> servers = new HashMap<>();

    private static TinyLogger logger;
    public static config conf;
    public static void main(String args[]){

        logger = new TinyLogger();
        logger.elog("hello world");
        conf = new config();
       // main.test();
        (new WebSrv()).start();
        mkpid_list();
        servers.put("a",new server());
        servers.get("a").start();





        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            //mysql.shutdown();
            servers.get("a").kill();
            servers.get("a").stopsrv();
            System.out.println("shutdown..");
        }
        ));
        //mysql.load();

    }

}
