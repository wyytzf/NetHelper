package helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by weiyuyang on 16-1-23.
 */
public class JSONRequest extends Request<JSONObject> {
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


    @Override
    public int compareTo(Request<JSONObject> another) {
        return 0;
    }
}
