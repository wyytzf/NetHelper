package helper;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiyuyang on 16-1-23.
 */
public abstract class Request<T> implements Comparable<Request<T>>{
    @Override
    public int compareTo(Request<T> another) {
        return 0;
    }

    private static final String DEFAULT_ENCODING = "utf-8";
    protected int mSerialNum = 0;
    private String url="";
    private Map<String ,String> mHeaders = new HashMap<String,String>();
    private Map<String,String> mBodyParams = new HashMap<String,String>();
    private RequestListener<T> mRequestListener;



    public Request(){

    }

    public String getUrl(){
        return url;
    }

    public int getSerialNum(){
        return mSerialNum;
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


    public abstract T parseResponse(Response response);
    public final void deliveryResponse(Response response){
        T result = parseResponse(response);
        if(mRequestListener!=null){

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
