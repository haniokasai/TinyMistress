package com.haniokasai.mc.TinyMistress.web.handlers;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hani on 2017/03/22.
 */
public class varCmd_handler {
    public static void varCmd_handler(Request baseRequest, HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>cmd</h1>"+request.getParameter("cmd"));
        Main.servers.get("a").execcmd(request.getParameter("cmd"));
    }
}
