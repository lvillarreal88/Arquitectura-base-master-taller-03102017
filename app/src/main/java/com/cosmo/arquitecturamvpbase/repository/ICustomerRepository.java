package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.ResponseCustomerStatus;

import java.util.ArrayList;

/**
 * Created by leonardo on 04/10/2017.
 */

public interface ICustomerRepository {

    ArrayList<Customer> getCustomerList() throws RepositoryError;

    Customer addCustomer(Customer customer) throws RepositoryError;

}
