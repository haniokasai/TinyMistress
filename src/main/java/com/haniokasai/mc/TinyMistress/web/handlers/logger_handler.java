package com.haniokasai.mc.TinyMistress.web.handlers;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hani on 2017/03/22.
 */
public class logger_handler {
    public static void logger_handler(Request baseRequest, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Main.servers.get("a").is,"UTF-8"));
            for (; ; ) {
                String line = br.readLine();
                if (line == null) break;
                response.getWriter().println("<p>" + line + "</p>");
                response.getWriter().println(" ");
                response.flushBuffer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
