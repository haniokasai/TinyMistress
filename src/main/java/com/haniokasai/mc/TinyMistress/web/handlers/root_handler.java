package com.haniokasai.mc.TinyMistress.web.handlers;

import com.haniokasai.mc.TinyMistress.Main;
import org.eclipse.jetty.server.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hani on 2017/03/22.
 */
public class root_handler {
    public static void root_handler(Request baseRequest, HttpServletResponse response)
            throws IOException, ServletException {
        String url = "http://"+ Main.conf.hostname+":"+Main.conf.webport;
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
        response.getWriter().println("<p><a href=\""+url+"/panel\" target=\"_blank\">PMMPのコントロールパネルを見る</a></p>");
    }
}
