package helper.helper.httpstacks;

import android.os.Build;

/**
 * Created by weiyuyang on 16-1-24.
 */
public final class HttpStackFactory {
    private static final int GINGERBREAD_SDK_NUM = 9;

    public static HttpStack creatHttpStack(){
        int runtimeSDKapi = Build.VERSION.SDK_INT;
        if(runtimeSDKapi>=GINGERBREAD_SDK_NUM){
            return new HttpUrlConnStack();
        }
        return new HttpClientStack();
    }

}
