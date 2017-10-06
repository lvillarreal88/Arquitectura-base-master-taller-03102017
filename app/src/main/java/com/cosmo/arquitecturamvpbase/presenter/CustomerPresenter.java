package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.ICustomerView;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leonardo on 04/10/2017.
 */

public class CustomerPresenter extends BasePresenter<ICustomerView>{

    private ICustomerRepository customerRepository;

    public CustomerPresenter(){
        this.customerRepository = new CustomerRepository();
    }

    public CustomerPresenter(ICustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public void getCustomersList() {
        if(!getValidateInternet().isConnected()){
            getView().showAlertDialog(R.string.error_customer, R.string.error_customer);
            return;
        }
       createThreadGetCustomer();
    }

    public void createThreadGetCustomer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                catchGetCustomerList();
            }
        });
        thread.start();
    }

    public void catchGetCustomerList() {
        try{
            ArrayList<Customer> lsyCustomers=customerRepository.getCustomerList();
            getView().showCustomerList(lsyCustomers);
        }catch (RepositoryError ex){
            ex.printStackTrace();
            getView().showAlertDialog(R.string.error_customer,R.string.error_customer);
            //throw RepositoryError(":: error ::");
        }finally {
           // getView().hideProgress();
        }
    }
}
