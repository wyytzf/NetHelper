package helper.core;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;


import helper.Request;
import helper.Response;

/**
 * Created by weiyuyang on 16-1-24.
 */
public class ResponseDelivery implements Executor {
    Handler mResponseHandler = new Handler(Looper.getMainLooper());

    public void deliverResponse(final Request<?> request,final Response response){
        Runnable respRunnable = new Runnable() {
            @Override
            public void run() {
                request.deliveryResponse(response);
            }
        };
        execute(respRunnable);
    }


    @Override
    public void execute(Runnable command) {

    }
}
