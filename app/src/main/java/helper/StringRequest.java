package helper;

/**
 * Created by weiyuyang on 16-1-25.
 */
public class StringRequest extends Request<String>{


    public StringRequest(HttpMethod method,String url,RequestListener<String> listener){
        super(method,url,listener);
    }


    @Override
    public String parseResponse(Response response) {
        String str = new String(response.getRawData());
        return str;
    }
}
