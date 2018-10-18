package com.example.TEM_PMSD.homeiot_lights.model;

import java.io.Serializable;

public class Lights implements Serializable {

    public static int ID=0;

    private int id;
    private int resourceIndex; //0 or 1 or 2
    private String name;

    public void setResourceIndex(int resourceIndex) {
        this.resourceIndex = resourceIndex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lights(String name, int resourceIndex)
    {
        this.id = ID++;
        this.name = name;
        this.resourceIndex= resourceIndex;

    }
    public int getId() {
        return id;
    }

    public int getResourceIndex() {
        return resourceIndex;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "id: "+id+" name:"+name+" resourceIndex:"+resourceIndex;
    }
}
