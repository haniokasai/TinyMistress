package com.haniokasai.mc.TinyMistress.web.handlers;

import com.haniokasai.mc.TinyMistress.tools.HtmlReplacer;
import org.eclipse.jetty.server.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hani on 2017/03/22.
 */
public class index_handler {
    public static void index_handler(Request baseRequest,HttpServletResponse response)
            throws IOException, ServletException{
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        HashMap<String,String> vars = new HashMap<>();
        vars.put("foo","bar");
        response.getWriter().println(HtmlReplacer.HtmlReplace("index",vars));
    }
}
