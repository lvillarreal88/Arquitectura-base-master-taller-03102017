package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.ResponseCustomerStatus;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by leonardo on 05/10/2017.
 */

public interface IAddCustomerView extends IBaseView {

    void addCustomer(Customer customer);

    void showAlertDialog(int error_customer, int error_customer1);

    void showToast(int add_customer_ok);
}
