package com.haniokasai.mc.TinyMistress.web.handlers.info;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hani on 2017/03/22.
 */
public class status_handler {
    public static void status_handler(Request baseRequest, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println(Main.servers.get("a").process.isAlive());
    }
}
