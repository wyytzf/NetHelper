package helper.httpstack;

import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.SSLSocketFactory;

import helper.Request;
import helper.Response;

/**
 * Created by weiyuyang on 16-1-24.
 */
public class HttpUrlConnStack implements HttpStack{
    @Override
    public Response performRequest(Request<?> request) {
        return null;
    }


    private HttpURLConnection createUrlConnection(String url) throws IOException {
        URL newurl = new URL(url);
        URLConnection urlConnection = newurl.openConnection();
        urlConnection.setConnectTimeout();
        urlConnection.setReadTimeout();
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        return (HttpURLConnection) urlConnection;
    }

    private void configHttps(Request<?> request){
        if(){
            SSLSocketFactory sslFactory;
        }
    }


    private void setRequestHeader(HttpURLConnection connection,Request<?> request){

    }
    private void setRequestParams(HttpURLConnection connection,Request<?> request){

    }

    private Response fetchResponse(HttpURLConnection connection){

    }

    private HttpEntity entityFromURLConnection(HttpURLConnection connection){

    }

    private void addHeadersToResponse(BasicHttpResponse response,HttpURLConnection connection){

    }
}


