package com.haniokasai.mc.TinyMistress.web;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.haniokasai.mc.TinyMistress.web.handlers.def_handler.def_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.index_handler.index_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.logger_handler.logger_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.panel.panel_handler.panel_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.post.cmd_handler.cmd_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.post.test_handler.test_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.restart_handler.restart_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.root_handler.root_handler;
import static com.haniokasai.mc.TinyMistress.web.handlers.varCmd_handler.varCmd_handler;

/**
 * Created by hani on 2017/03/15.
 */
class WebHandler  extends AbstractHandler
{
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        //String url = "http://"+Main.conf.hostname+":"+Main.conf.webport;
        //HashMap <String,String> vars = new HashMap<>();
        switch (request.getPathInfo()){
            case"/index":
                index_handler(baseRequest,response);
                break;
            case "":
            case "/":
                root_handler(baseRequest, response);
                break;
            case "/restart":
                restart_handler(baseRequest, response);
                break;
            case "/cmd":
                varCmd_handler(baseRequest,request,response);
                break;
            case "/post/test":
                test_handler(baseRequest, request, response);
                break;
            case "/post/cmd":
                cmd_handler(baseRequest, request, response);
                break;
            case "/info":
            case "/logger":
                logger_handler(baseRequest, response);
                break;
            case "/panel":
                panel_handler(baseRequest, response);
                break;
            default:
                def_handler(baseRequest,request,response);
                break;
        }
    }




}