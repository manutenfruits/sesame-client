package com.manutenfruits.sesame.communication;

import org.json.JSONObject;

/**
 * Created by manutenfruits on 31/08/15.
 */
public class Door {

    public final int id;
    public final DoorStatus status;
    public final String name;

    public Door(int id, DoorStatus status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public DoorStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
