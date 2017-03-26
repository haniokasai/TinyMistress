package com.haniokasai.mc.TinyMistress.web.handlers.info;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hani on 2017/03/22.
 */
public class log_handler {
    public static int log_max;//設定で変えられるように
    public static void log_handler(Request baseRequest, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        int size = Main.servers.get("a").srv_log.size();
        for (int i = 0; i < size; i++) {
            response.getWriter().println("<p>"+Main.servers.get("a").srv_log.get(i)+"</p>");
        }
        Main.servers.get("a").srv_log.clear();
    }
}
