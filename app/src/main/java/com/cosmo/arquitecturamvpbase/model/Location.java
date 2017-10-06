package com.cosmo.arquitecturamvpbase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leonardo on 04/10/2017.
 */

public class Location implements Serializable{

    @SerializedName("coordinates")
    @Expose
    private Double[] coordinates = null;

    @SerializedName("type")
    @Expose
    private String type;

    public Double[] getCoordinates() {return coordinates;}

    public void setCoordinates(Double[] coordinates) {this.coordinates = coordinates;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

}
