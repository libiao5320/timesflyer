package com.lee.system.http;


import com.lee.system.dom.HtmlDomUtil;
import com.lee.system.exception.HttpErrorConstants;
import com.lee.system.exception.MyHttpException;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by libia on 2016/1/30.
 */
public class HttpRequestHandler extends HttpAbstractHttpRequest {

    private URL url;
    private HttpURLConnection urlConnection ;

    private HttpRequestContnet httpRequestContnet;


    public HttpRequestHandler() {


    }

    public HttpRequestHandler(String url) {


    }

    public HttpRequestHandler(HttpRequestContnet httpRequestContnet) {
        this.httpRequestContnet = httpRequestContnet;
    }

    public String request(HttpRequestContnet httpRequestContnet) throws MyHttpException, IOException {


        String pageResult = "";
        this.httpRequestContnet = httpRequestContnet;


        if( null == httpRequestContnet )
        {
            throw new MyHttpException(HttpErrorConstants.URLNULL_CODE);
        }
        else
        {
            if( null == httpRequestContnet.getUrl()  || "".equals(httpRequestContnet.getUrl()))
            throw new MyHttpException(HttpErrorConstants.URLNULL_CODE);
        }




        this.url = new URL(httpRequestContnet.getUrl()+"?t="+Math.random());
        this.urlConnection = (HttpURLConnection) this.url.openConnection();

        if ( null !=  httpRequestContnet.getHttpRequestHead() ) {
            HttpRequestHead httpRequestHead =  httpRequestContnet.getHttpRequestHead();
            if( null !=  httpRequestHead.getAgent() && !"".equals(httpRequestHead.getAgent()))
                this.urlConnection.setRequestProperty("", httpRequestHead.getAgent());
            if( null !=  httpRequestHead.getAccept() && !"".equals(httpRequestHead.getAccept()))
                this.urlConnection.setRequestProperty("", httpRequestHead.getAccept());
            if( null !=  httpRequestHead.getConnetction() && !"".equals(httpRequestHead.getConnetction())) {
                this.urlConnection.setRequestProperty("", "");
            }
            if( null !=  httpRequestHead.getContentType() && !"".equals(httpRequestHead.getContentType()))
                this.urlConnection.setRequestProperty("", httpRequestHead.getContentType());
            else
                this.urlConnection.setRequestProperty("contentType", "utf-8");
        }
        String data = "";
        if( null != this.httpRequestContnet ) {
            if( null !=  this.httpRequestContnet.getParams()) {
                data = this.httpRequestContnet.getParams().toString();
                OutputStream outputStream = this.urlConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                outputStreamWriter.write(new String(data.getBytes()));
                outputStream.flush();
                outputStream.close();
            }
        }

        int responseCode = urlConnection.getResponseCode();
        if( responseCode  == 200) {
            String encoding = "iso-8859-1";
            String contentType = urlConnection.getContentType();
            int encodingStart = contentType.indexOf("charset=");
            if( encodingStart != -1)
            {
                encoding = contentType.substring(encodingStart +8);
            }
            InputStream inputStream =  urlConnection.getInputStream();
            InputStreamReader inputStreamReader  = new InputStreamReader(inputStream,encoding);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer buffer  = new StringBuffer();

        //  字节数组
//            byte[] bs = new byte[inputStream.available()];
//            int c = -1;
//            int count = 0;
//            while ((c = inputStream.read(bs)) != -1) {
//
//            }


            while ( null != bufferedReader.readLine()) {
                buffer.append(bufferedReader.readLine());
            }
            pageResult = buffer.toString();

            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();

            inputStream = null;
            inputStreamReader = null;
            bufferedReader = null;

//            pageResult  =  new String (bs);

        }
        else
        {
            if( responseCode == 500)
                throw  new MyHttpException(this.httpRequestContnet.getUrl() , HttpErrorConstants.SERVER_CODE);
            if( responseCode == 404)
                throw  new MyHttpException(this.httpRequestContnet.getUrl() , HttpErrorConstants.NOTFOUND_CODE);
            if( responseCode == 403)
                throw  new MyHttpException(this.httpRequestContnet.getUrl() , HttpErrorConstants.URLFOBIDEN_CODE);
        }


        Document document = HtmlDomUtil.parseDoc(pageResult);
        Element body  = document.body();



        Elements links = document.select("a[href]");
        Elements media = document.select("[src]");
        Elements imports = document.select("link[href]");


        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }






        return pageResult;
    }




    public String request(String url) {



        return "";
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

}
