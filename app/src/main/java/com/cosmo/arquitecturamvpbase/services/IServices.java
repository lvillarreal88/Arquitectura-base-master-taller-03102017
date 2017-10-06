package com.cosmo.arquitecturamvpbase.services;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.ResponseCustomerStatus;

import java.util.ArrayList;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface IServices {


    @GET("/product")
    ArrayList<Product> getProductList();

    @GET("/customers")
    ArrayList<Customer> getCustomerList();

    @POST("/customers")
    Customer postCustomer(@Body Customer customer);

}
