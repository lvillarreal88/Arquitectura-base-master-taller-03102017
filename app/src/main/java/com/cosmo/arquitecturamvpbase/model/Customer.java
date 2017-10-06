package com.cosmo.arquitecturamvpbase.model;

import java.io.Serializable;

/**
 * Created by leonardo on 04/10/2017.
 */

import java.util.ArrayList;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer implements Serializable {

//    @SerializedName("_id")
//    @Expose
//    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

//    @SerializedName("__v")
//    @Expose
//    private Integer v;

    @SerializedName("phoneList")
    @Expose
    private ArrayList<PhoneList> phoneList = null;

//    public String getId() {return id;}

//    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

//    public Integer getV() {return v;}

//    public void setV(Integer v) {this.v = v;}

    public List<PhoneList> getPhoneList() {return phoneList;}

    public void setPhoneList(ArrayList<PhoneList> phoneList) {this.phoneList = phoneList;}


}