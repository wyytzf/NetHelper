package helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by weiyuyang on 16-1-23.
 */
public class JSONRequest extends Request<JSONObject> {

    public JSONRequest(HttpMethod method,String url,RequestListener<JSONObject> listener){
        super(method,url,listener);
    }



    @Override
    public JSONObject parseResponse(Response response) {
        String JSONString = new String(response.getRawData());
        try {
            return new JSONObject(JSONString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
