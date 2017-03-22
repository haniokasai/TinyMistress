package com.haniokasai.mc.TinyMistress.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * Created by htek on 2017/03/20.
 */
public class HtmlReplacer {

    private static HashMap<String,String> htmls;

    public static String HtmlReplace(String html_name ,HashMap<String,String> map){
        if(!htmls.containsKey(html_name))return null;
        String res_html = htmls.get(html_name);
        //System.out.println(res_html);
        for(final String variable: map.keySet()){
            res_html = res_html.replace("=v="+variable+"=v=",map.get(variable));
        }
        return res_html;
    }

    public static String Def_replacer(String name){//絶対に最初置き換えるものを、initで実行します
        return null;
    }

    public static void html_init(){
        InputStream is = ClassLoader.getSystemResourceAsStream("html/conf.properties");
        Properties properties = new Properties();
        TinyLogger log = new TinyLogger();
        htmls = new HashMap<>();
        try {
            properties.load(is);
        } catch (Exception e) {
            log.elog(e.getMessage());
            log.elog("Html設定ファイル異常です");
            System.exit(1);
        }
        try {
            for (final String name: properties.stringPropertyNames()) {
                String res_html;
                BufferedReader br = null;
                InputStream is2 = null;
                try {
                    is2 = ClassLoader.getSystemResourceAsStream("html/"+properties.getProperty(name));
                    br = new BufferedReader(new InputStreamReader(is2, StandardCharsets.UTF_8));
                    String str;
                    res_html = br.readLine();
                    while ((str = br.readLine()) != null) {

                        res_html += str;
                    }
                    htmls.put(name,res_html);
                } catch (Exception e) {
                    log.elog(e.getMessage());
                } finally {
                    try {
                        assert br != null;
                        br.close();
                        assert is2 != null;
                        is2.close();
                    } catch (IOException e) {
                        log.elog(e.getMessage());
                    }
                }

            }
        }catch (MissingResourceException e){
            log.elog(e.getMessage());
            log.elog("Html設定に問題があります");
            System.exit(1);
        }
        try {
            is.close();
        } catch (IOException e) {
            log.elog(e.getMessage());
        }
    }

}
