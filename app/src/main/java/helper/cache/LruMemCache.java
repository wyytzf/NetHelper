package helper.cache;

import android.support.v4.util.LruCache;

import helper.Response;

/**
 * Created by weiyuyang on 16-1-25.
 */
public class LruMemCache implements Cache<String, Response>{

    private LruCache<String,Response> mLruMemCache;


    public LruMemCache(){
        int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        int cacheSize = maxMemory/8;
        mLruMemCache = new LruCache<String,Response>(cacheSize){
            @Override
            protected int sizeOf(String key, Response value) {
                return value.getRawData().length/1024;
            }
        };

    }


    @Override
    public Response get(String key) {
        return mLruMemCache.get(key);
    }

    @Override
    public void put(String key, Response value) {
        mLruMemCache.put(key,value);
    }

    @Override
    public void remove(String key) {
        mLruMemCache.remove(key);
    }
}
