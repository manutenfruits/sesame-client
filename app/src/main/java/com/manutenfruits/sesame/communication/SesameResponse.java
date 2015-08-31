package com.manutenfruits.sesame.communication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by manutenfruits on 31/08/15.
 */
public class SesameResponse {

    private final List<Door> doors;
    private final long nonce;

    public SesameResponse(JSONObject object) throws JSONException {
        this.doors = new ArrayList<Door>();

        final JSONArray doorArr = object.getJSONArray("doors");

        for (int i = 0; i < doorArr.length(); i++) {
            final JSONObject door = doorArr.getJSONObject(i);

            final int id = door.getInt("id");
            final DoorStatus status = DoorStatus.valueOf(door.getString("status"));
            final String name = door.getString("name");

            this.doors.add(new Door(id, status, name));
        }

        this.nonce = object.getLong("nonce");
    }

    public List<Door> getDoors() {
        return doors;
    }

    public long getNonce() {
        return nonce;
    }
}
