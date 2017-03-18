package com.haniokasai.mc.TinyMistress.web;

/**
 * Created by hani on 2017/03/14.
 */
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletHandler;

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

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
