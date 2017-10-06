package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by leonardo on 04/10/2017.
 */

public interface ICustomerView extends IBaseView{

    void showCustomerList(ArrayList<Customer> customerArrayList);

    void showAlertDialog(int error_customer, int error_customer1);
}
