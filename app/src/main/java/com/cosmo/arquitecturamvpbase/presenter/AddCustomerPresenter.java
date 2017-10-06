package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.ResponseCustomerStatus;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IAddCustomerView;

/**
 * Created by leonardo on 05/10/2017.
 */

public class AddCustomerPresenter extends BasePresenter<IAddCustomerView>{

    private ICustomerRepository iCustomerRepository;

    public AddCustomerPresenter() {
        this.iCustomerRepository = new CustomerRepository();
    }

    public AddCustomerPresenter(ICustomerRepository customerRepository) {
        this.iCustomerRepository = customerRepository;
    }

    public void postCustomerValidate(Customer cus) {
        if(!getValidateInternet().isConnected()){
            getView().showAlertDialog(R.string.error_customer,R.string.error_customer);
            return;
        }
        createThreadPostCustomer(cus);
    }

    public void createThreadPostCustomer(final Customer cus) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                catchPostCustomer(cus);
            }
        });
        thread.start();
    }

    public void catchPostCustomer(Customer cus){
        try{
            iCustomerRepository.addCustomer(cus);
            getView().showToast(R.string.add_customer_ok);
        }catch (RepositoryError rep){
            rep.printStackTrace();
            getView().showAlertDialog(R.string.error_customer,R.string.error_customer);
        }
    }
}
