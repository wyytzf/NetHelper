package helper.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by weiyuyang on 16-1-25.
 */
public class HttpsUrlConnConfig extends HttpConfig{

    /**
     * httpClient的SSLSocketFactory所在包为org.apache.http.conn.ssl.SSLSocketFactory
     * HttpURLConnection的SSLSocketFactory所在的包为javax.net.ssl.SocketFactory
     */

    private static HttpsUrlConnConfig mHttpUrlConnConfig = null;

    private SSLSocketFactory mSslSocketFactory = null;
    private HostnameVerifier mHostnameVerifier = null;


    private HttpsUrlConnConfig(){

    }


    public static HttpsUrlConnConfig getConfigInstance(){
        if(mHttpUrlConnConfig == null){
            synchronized (HttpsUrlConnConfig.class){
                if (mHttpUrlConnConfig == null){
                    mHttpUrlConnConfig = new HttpsUrlConnConfig();
                }
            }
        }
        return mHttpUrlConnConfig;
    }


    public void setHttpsConfig(SSLSocketFactory sslSocketFactory,HostnameVerifier hostnameVerifier){
        this.mSslSocketFactory = sslSocketFactory;
        this.mHostnameVerifier = hostnameVerifier;
    }

    public SSLSocketFactory getSSLSocketFactory(){
        return mSslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier(){
        return mHostnameVerifier;
    }


}
