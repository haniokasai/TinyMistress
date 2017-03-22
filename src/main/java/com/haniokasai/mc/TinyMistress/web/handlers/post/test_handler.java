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
public class test_handler {
    public static void test_handler(Request baseRequest, HttpServletRequest request, HttpServletResponse response)throws IOException {
        String url = "http://"+ Main.conf.hostname+":"+Main.conf.webport;
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<form action=\""+url+"\"/test\" method=\"post\">\n" +
                "  <input name=\"say\" value=\"Hi\">\n" +
                "  <input name=\"to\" value=\"Mom\">\n" +
                "  <button>Send my greetings</button>\n" +
                "</form>");
        BufferedReader reader = baseRequest.getReader();
        //response.getWriter().println(reader.readLine());
        final String st =reader.readLine();
        response.getWriter().println(st);
        try {
            response.getWriter().println((new Parampharser()).splitQuery(st).get("say"));
        }catch (NullPointerException ignored){}
        request.getMethod();
    }
}
