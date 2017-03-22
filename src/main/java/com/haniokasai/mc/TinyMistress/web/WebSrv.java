package com.haniokasai.mc.TinyMistress.web;

/**
 * Created by hani on 2017/03/14.
 */

import com.haniokasai.mc.TinyMistress.Main;
import com.haniokasai.mc.TinyMistress.tools.HtmlReplacer;
import com.haniokasai.mc.TinyMistress.web.handlers.def_handler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import java.util.HashMap;

public class WebSrv extends Thread {

    @Override
    public void run() {
        /*
        Server server = new Server(1234);
        try {
            server.setHandler(new WebHandler());
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        Server server = new Server();
        Connector connector = new SocketConnector();
        connector.setPort(Integer.parseInt(Main.conf.webport));
        server.setConnectors(new Connector[] { connector });

        ServletHandler handler = new ServletHandler();
        server.setHandler(new WebHandler());

        handler.addServletWithMapping("foo","/");
        HtmlReplacer.html_init();
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
