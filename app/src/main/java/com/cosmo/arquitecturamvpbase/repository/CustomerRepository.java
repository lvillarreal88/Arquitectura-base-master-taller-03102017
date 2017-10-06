package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.services.IServices;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leonardo on 04/10/2017.
 */

public class CustomerRepository implements ICustomerRepository{

    private IServices iServices;

    public CustomerRepository() {
        ServicesFactory servicesFactory = new ServicesFactory();
        iServices = (IServices) servicesFactory.getInstance(IServices.class);
    }

    @Override
    public ArrayList<Customer> getCustomerList() {
        return iServices.getCustomerList();
    }

    @Override
    public Customer addCustomer(Customer customer) throws RepositoryError{
        try {
            return iServices.postCustomer(customer);
        }catch (RetrofitError repositoryError){
            repositoryError.printStackTrace();
            throw repositoryError;
        }
    }
}
