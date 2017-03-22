package com.haniokasai.mc.TinyMistress.web.handlers.panel;

import com.haniokasai.mc.TinyMistress.Main;
import com.haniokasai.mc.TinyMistress.tools.HtmlReplacer;
import com.haniokasai.mc.TinyMistress.tools.Parampharser;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hani on 2017/03/22.
 */
public class panel_handler {
    public static void panel_handler(Request baseRequest, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        HashMap<String,String> vars = new HashMap<>();
        vars.put("srv_status", Main.servers.get("a").process.isAlive()?"動作中":"停止中");
        response.getWriter().println(HtmlReplacer.HtmlReplace("panel",vars));
        BufferedReader reader3 = baseRequest.getReader();
        final String st3 =reader3.readLine();
        response.getWriter().println(st3);
        try {
            String action = (new Parampharser()).splitQuery(st3).get("action");
            switch (action){
                case "start":
                    if(!Main.servers.get("a").process.isAlive()){
                        Main.servers.get("a").start();
                    }
                    break;
                case "restart":
                    Main.servers.get("a").restart();
                    break;
                case "stop":
                    Main.servers.get("a").execcmd("stop");
                    break;
                case "kill":
                    Main.servers.get("a").kill();
                    break;
                default:
                    break;
            }
        }catch (NullPointerException ignored){}
        try {
            String cmd = (new Parampharser()).splitQuery(st3).get("cmd");
            if(cmd == null)return;
            response.getWriter().println(cmd);
            Main.servers.get("a").execcmd(cmd);
        }catch (NullPointerException ignored){}
    }
}
