package com.example.bookplace.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NetworkRequestHandler {
    private RequestQueue mRequestQueue;
    private Context mContext;

    public NetworkRequestHandler(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public void makeStringRequest(String url, final NetworkRequestListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.getMessage());
                    }
                });

        mRequestQueue.add(stringRequest);
    }

    public interface NetworkRequestListener<T> {
        void onSuccess(T response);
        void onError(String errorMessage);
    }
}
