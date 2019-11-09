package com.example.banregiotest.ws;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ConnectionQueue {
    private static  ConnectionQueue mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private ConnectionQueue(Context context)
    {
        mContext=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized ConnectionQueue getInstance (Context context)
    {
        if(mInstance==null)
        {
            mInstance= new ConnectionQueue(context);

        }
        return mInstance;
    }

    public <T>void addToRequestQue(Request<T> request)
    {
        requestQueue.add(request);
    }

}
