package com.cosmo.arquitecturamvpbase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by leonardo on 04/10/2017.
 */

public class PhoneList implements Serializable{

    @SerializedName("number")
    @Expose
    private String number;
//    @SerializedName("_id")
//    @Expose
//    private String id;
    @SerializedName("location")
    @Expose
    private Location location;


    public String getNumber() {return number;}


    public void setNumber(String number) {this.number = number;}


  //  public String getId() {return id;}


    //public void setId(String id) {this.id = id;}


    public Location getLocation() {return location;}


    public void setLocation(Location location) {this.location = location;}


}
