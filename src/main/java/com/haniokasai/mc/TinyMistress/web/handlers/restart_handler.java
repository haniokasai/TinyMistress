package com.haniokasai.mc.TinyMistress.web.handlers;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hani on 2017/03/22.
 */
public class restart_handler {
    public static void restart_handler(Request baseRequest, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Restart</h1>");
        Main.servers.get("a").restart();
    }
}
