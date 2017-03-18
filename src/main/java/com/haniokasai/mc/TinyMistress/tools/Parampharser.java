package com.haniokasai.mc.TinyMistress.tools;

import org.omg.CORBA.NameValuePair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import org.apache.http.client.utils.URLEncodedUtils;

/**
 * Created by hani on 2017/03/17.
 */
public class Parampharser {
    //http://stackoverflow.com/questions/13592236/parse-a-uri-string-into-name-value-collection

    public HashMap<String,String> splitQuery(String str) {
        if(str==null||str.isEmpty())return null;
        List<org.apache.http.NameValuePair> paramsList = null;
        HashMap<String,String> map = new HashMap<>();
        try {
            paramsList = URLEncodedUtils.parse(new URI("http://foo.bar/example?"+str),"utf-8");
        } catch (URISyntaxException e) {
            return null;
        }
        for (org.apache.http.NameValuePair parameter : paramsList)
            map.put(parameter.getName(),parameter.getValue());
        return map;
    }


}
