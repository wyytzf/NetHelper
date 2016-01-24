package helper;

import org.apache.http.ProtocolVersion;

import java.net.URLEncoder;
import java.security.Policy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiyuyang on 16-1-23.
 */
public abstract class Request<T> implements Comparable<Request<T>>{
    public static enum HttpMethod{
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        private String mHttpMethod = "";
        private HttpMethod(String method){
            mHttpMethod = method;
        }

        @Override
        public String toString() {
            return mHttpMethod;
        }
    }


    public static enum Priority{
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }


    @Override
    public int compareTo(Request<T> another) {
        Priority thisPriority = this.getPriority();
        Priority anotherPriority = another.getPriority();
        // ?
        return thisPriority.equals(anotherPriority)?this.getSerialNum()-another.getSerialNum():
                thisPriority.ordinal()-anotherPriority.ordinal();
    }

    private static final String DEFAULT_ENCODING = "utf-8";
    protected int mSerialNum = 0;
    protected Priority mPriority = Priority.NORMAL;
    protected boolean isCancel = false;
    protected boolean isHttps = false;
    private boolean mShouldCache = true;
    private String url="";
    HttpMethod mHttpMethod = HttpMethod.GET;
    private Map<String ,String> mHeaders = new HashMap<String,String>();
    private Map<String,String> mBodyParams = new HashMap<String,String>();
    private RequestListener<T> mRequestListener;



    public Request(HttpMethod method,String url,RequestListener<T> listener){
        this.mHttpMethod = method;
        this.url = url;
        this.mRequestListener = listener;
    }





    public String getUrl(){
        return url;
    }

    public int getSerialNum(){
        return mSerialNum;
    }

    public Priority getPriority(){
        return mPriority;
    }
    public void setPriority(Priority priority){
        this.mPriority = priority;
    }


    public void setmSerialNum(int mSerialNum){
        this.mSerialNum = mSerialNum;
    }


    public Map<String,String> getHeaders(){
        return mHeaders;
    }
    public Map<String,String> getParams(){
        return mBodyParams;
    }

    public String getParamsEncoding(){
        return DEFAULT_ENCODING;
    }

    public String getBodyContentType(){
        return getParamsEncoding();
    }

    public HttpMethod getHttpMethod(){
        return mHttpMethod;
    }

    public void cancel(){
        isCancel= true;
    }

    public boolean isCanceled(){
        return isCancel;
    }

    public boolean ismShouldCache() {
        return mShouldCache;
    }



    public abstract T parseResponse(Response response);


    public final void deliveryResponse(Response response){
        T result = parseResponse(response);
        if(mRequestListener!=null){
            int reCode = response != null?response.getStatusCode() : -1;
            String msg = response != null?response.getMessage():"unknow error";
            mRequestListener.onComplete(reCode,result,msg);
        }
    }

    public byte[] getBody(){
        Map<String,String> params = getParams();
        if(params!= null && params.size()>0){
            return encodeParamters(params,getParamsEncoding());
        }
        return null;
    }

    private byte[] encodeParamters(Map<String,String> params,String paramsEncoding){
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                sb.append("=");
                sb.append(URLEncoder.encode(entry.getValue(),paramsEncoding));
                sb.append("&");
            }
            return sb.toString().getBytes(paramsEncoding);
        }catch (Exception e){
            throw new RuntimeException("can not encode paramters");
        }
    }


    public interface RequestListener<T>{
        void onComplete(int reCode, T result, String errorMsg);
    }


}
