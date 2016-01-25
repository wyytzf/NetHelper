package helper.core;

import java.util.concurrent.BlockingDeque;

import helper.Request;
import helper.Response;
import helper.cache.Cache;
import helper.cache.LruMemCache;
import helper.httpstack.HttpStack;

/**
 * Created by weiyuyang on 16-1-24.
 */
public final class NetWorkExecutor extends Thread{
    private BlockingDeque<Request<?>> mRequestQueue;

    private HttpStack mHttpStack;

    /**
     * static
     */
    private static ResponseDelivery mResonseDelivery = new ResponseDelivery();

    /**
     * static
     */
    private static Cache<String, Response> mCache = new LruMemCache();

    private boolean isStop = false;

    public NetWorkExecutor(BlockingDeque<Request<?>> queue,HttpStack httpStack){
        this.mRequestQueue = queue;
        this.mHttpStack = httpStack;
    }

    @Override
    public void run() {
        try {
            while (!isStop){
                final Request<?> request;
                request = mRequestQueue.take();
                if(request.isCanceled()){
                    continue;
                }
                Response response = null;
                if (isUseCache(request)){
                    response = mCache.get(request.getUrl());
                }else{
                    response = mHttpStack.performRequest(request);
                    if(request.ismShouldCache() && isSuccess(response)){
                        mCache.put(request.getUrl(),response);
                    }
                }

                mResonseDelivery.deliverResponse(request,response);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isSuccess(Response response){
        return response!=null&&response.getStatusCode()==200;
    }

    private boolean isUseCache(Request<?> request){
        return request.ismShouldCache() && mCache.get(request.getUrl())!=null;
    }
}
