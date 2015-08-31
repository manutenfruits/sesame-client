package com.manutenfruits.sesame.communication;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manutenfruits on 31/08/15.
 */
public class SesameService {

    private final String host;
    private final String password;
    private final Context context;
    private final RequestQueue queue;
    private List<Door> doors;
    private long nonce = 0;

    public SesameService(String host, String password, final Context context) {
        this.host = host;
        this.password = password;
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
        this.doors = new ArrayList<Door>();

        this.refreshStatus();
    }

    public void refreshStatus() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, this.host,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        final SesameResponse sesameResponse = new SesameResponse(response);

                        setDoors(sesameResponse.getDoors());
                        setNonce(sesameResponse.getNonce());

                    } catch (JSONException e) {
                        Toast toast = Toast.makeText(context, "Something went wrong parsing the response.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(context, "Something went wrong accessing the server.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        );

        this.queue.add(jsonRequest);
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }
}
