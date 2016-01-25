package helper.core;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import helper.Request;
import helper.httpstack.HttpStack;

/**
 * Created by weiyuyang on 16-1-24.
 */

/**
 * final ?
 */
public final class RequestQueue {
    /**
     *  queue?
     */
    private BlockingQueue<Request<?>> mRequestQueue = new PriorityBlockingQueue<Request<?>>();
    /**
     * AtomicInteger?
     */
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);

    /**
     * ?
     */
    public static int DEFAULT_CORE_NUMS = Runtime.getRuntime().availableProcessors()+1;


    private int mDispatcherNums = DEFAULT_CORE_NUMS;

    private NetworkExecutor[] mDispathces = null;

    private HttpStack mHttpStack;

    /**
     * protected ?
     * @param coreNums
     * @param httpStack
     */
    protected RequestQueue(int coreNums,HttpStack httpStack){
        this.mDispatcherNums = coreNums;
        this.mHttpStack = httpStack;
    }

    private final void startNetWorkExecutors(){
        mDispathces = new NetworkExecutor[mDispatcherNums];
    }
    public void start(){
        stop();
        startNetWorkExecutors();
    }
    public void stop(){

    }

    public void addRequest(Request<?> request){
        if(!mRequestQueue.contains(request)){
            request.setmSerialNum(generateSerialNumber());
            mRequestQueue.add(request);
        }else{

        }
    }


    public void clear(){
        mRequestQueue.clear();
    }

    public BlockingQueue<Request<?>> getAllRequest(){
        return mRequestQueue;
    }

    /**
     *
     * @return
     */
    private int generateSerialNumber(){
        return mSerialNumGenerator.incrementAndGet();
    }

}
