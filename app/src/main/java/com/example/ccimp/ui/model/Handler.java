package com.example.ccimp.ui.model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Handler {
    public static Handler mInstance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private Handler(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }

        return requestQueue;
    }

    public static synchronized Handler getInstance (Context context) {
        if(mInstance == null) {
            mInstance = new Handler(context);
        }

        return mInstance;
    }

    public  <T> void addToRequestQue(com.android.volley.Request<T> request){
        requestQueue.add(request);
    }
}
