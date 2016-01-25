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
import helper.config.HttpsUrlConnConfig;

/**
 * Created by weiyuyang on 16-1-24.
 */
public class HttpUrlConnStack implements HttpStack{

    HttpsUrlConnConfig mConfig = HttpsUrlConnConfig.getConfigInstance();

    @Override
    public Response performRequest(Request<?> request) {
        return null;
    }


    private HttpURLConnection createUrlConnection(String url) throws IOException {
        URL newurl = new URL(url);
        URLConnection urlConnection = newurl.openConnection();
        urlConnection.setConnectTimeout(mConfig.connTimeOut);
        urlConnection.setReadTimeout(mConfig.soTimeOut);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        return (HttpURLConnection) urlConnection;
    }

    private void configHttps(Request<?> request){

    }


    private void setRequestHeader(HttpURLConnection connection,Request<?> request){

    }
    private void setRequestParams(HttpURLConnection connection,Request<?> request){

    }

    private Response fetchResponse(HttpURLConnection connection){
        return null;
    }

    private HttpEntity entityFromURLConnection(HttpURLConnection connection){
        return null;
    }

    private void addHeadersToResponse(BasicHttpResponse response,HttpURLConnection connection){

    }
}


