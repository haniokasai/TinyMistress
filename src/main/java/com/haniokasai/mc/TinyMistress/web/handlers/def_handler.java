package com.haniokasai.mc.TinyMistress.web.handlers;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Created by htek on 2017/03/20.
 */
public class def_handler {

    // --Commented out by Inspection (2017/03/22 22:12):public static HashMap<String,String> defs;
    public static void def_handler(Request baseRequest, HttpServletRequest request, HttpServletResponse response)throws IOException{
        String path = request.getPathInfo();
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        if(path.endsWith(".jpg")||path.endsWith(".jpeg")){
            File is = new File("src/main/resources/res"+path);
            try {
                BufferedImage bi = ImageIO.read(is);
                OutputStream out = response.getOutputStream();
                ImageIO.write(bi, "jpg", out);
                out.close();
            }catch (javax.imageio.IIOException ignored){}
        }else{
            try {
                InputStream is = ClassLoader.getSystemResourceAsStream("res"+path);
                OutputStream out = response.getOutputStream();
                IOUtils.copy(is,out);
                is.close();
                out.close();
            }catch (java.lang.NullPointerException ignored){}
        }
    }
}
