package helper.httpstack;

import helper.Request;
import helper.Response;

/**
 * Created by weiyuyang on 16-1-24.
 */
public interface HttpStack {
    Response performRequest(Request<?> request);
}
