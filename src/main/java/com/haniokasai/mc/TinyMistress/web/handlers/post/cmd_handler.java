package com.haniokasai.mc.TinyMistress.web.handlers.post;

import com.haniokasai.mc.TinyMistress.Main;
import com.haniokasai.mc.TinyMistress.tools.Parampharser;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by hani on 2017/03/22.
 */
public class cmd_handler {
    public static void cmd_handler(Request baseRequest, HttpServletRequest request, HttpServletResponse response)throws IOException {
        String url = "http://"+ Main.conf.hostname+":"+Main.conf.webport;
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<form action=\""+url+"/post/cmd\" method=\"post\">\n" +
                "  <input name=\"cmd\" value=\"コマンドを入れてください\">\n" +
                "  <button>実行！</button>\n" +
                "</form>");
        BufferedReader reader2 = baseRequest.getReader();
        final String st2 =reader2.readLine();
        response.getWriter().println(st2);
        try {
            String cmd = (new Parampharser()).splitQuery(st2).get("cmd");
            response.getWriter().println(cmd);
            Main.servers.get("a").execcmd(cmd);
        }catch (NullPointerException ignored){}
        request.getMethod();
    }
}
