package com.haniokasai.mc.TinyMistress.web;

import com.haniokasai.mc.TinyMistress.Main;
import com.haniokasai.mc.TinyMistress.playground.main;
import com.haniokasai.mc.TinyMistress.srv.server;
import com.haniokasai.mc.TinyMistress.tools.Parampharser;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Map;

import static com.haniokasai.mc.TinyMistress.Main.servers;

/**
 * Created by hani on 2017/03/15.
 */
public class WebHandler  extends AbstractHandler
{
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String url = "http://"+Main.conf.hostname+":"+Main.conf.webport;
        switch (request.getPathInfo()){
            case "":
            case "/":
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println("<h1>機能一覧（実務で使うことをおすすめしません）</h1>");
                response.getWriter().println("<p><a href=\""+url+"/restart\" target=\"_blank\">PMMPを再起動する</a></p>");
                response.getWriter().println("<p><a href=\""+url+"/cmd?cmd=stop\" target=\"_blank\">PMMPを止める</a></p>");
                response.getWriter().println("<p><a href=\""+url+"/cmd\" target=\"_blank\">コマンドを実行する（URLの引数）</a></p>");
                response.getWriter().println("<p><a href=\""+url+"/post/test\" target=\"_blank\">POSTのテスト</a></p>");
                response.getWriter().println("<p><a href=\""+url+"/post/cmd\" target=\"_blank\">コマンドを実行（POSTをする）</a></p>");
                response.getWriter().println("<p><a href=\""+url+"/logger\" target=\"_blank\">PMMPのログを見る</a></p>");
                break;
            case "/restart":
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println("<h1>Restart</h1>");
                Main.servers.get("a").restart();
                break;
            case "/cmd":
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println("<h1>cmd</h1>"+request.getParameter("cmd"));
                Main.servers.get("a").execcmd(request.getParameter("cmd"));
                break;
            case "/post/test":
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
                break;
            case "/post/cmd":
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
                break;
            case "/logger":
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(Main.servers.get("a").is,"UTF-8"));
                    int i = 0;
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

                break;
        }


       // Main.servers.get("a").restart();
    }



}