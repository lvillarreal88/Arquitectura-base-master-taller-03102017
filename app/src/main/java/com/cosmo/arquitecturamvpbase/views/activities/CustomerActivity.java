package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.presenter.CustomerPresenter;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.CustomerAdapter;
import com.cosmo.arquitecturamvpbase.views.adapter.ProductAdapter;

import java.util.ArrayList;

/**
 * Created by leonardo on 04/10/2017.
 */

public class CustomerActivity extends BaseActivity<CustomerPresenter> implements ICustomerView{


    private ListView customerList;
    private CustomerAdapter customerAdapter;
    private ContentLoadingProgressBar progress;
    private FloatingActionButton fabAddCustomer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        setPresenter(new CustomerPresenter());
        getPresenter().inject(this, getValidateInternet());
        loadComponentObject();
        progress.show();
        getPresenter().getCustomersList();

        fabAddCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intaux = new Intent(CustomerActivity.this, AddCustomerActivity.class);
                startActivity(intaux);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getCustomersList();
    }

    public void loadComponentObject(){
        customerList = (ListView) findViewById(R.id.customer_listview);
        progress = (ContentLoadingProgressBar) findViewById(R.id.progress);
        fabAddCustomer = (FloatingActionButton) findViewById(R.id.activity_fabtn_customer_add);
    }

    @Override
    public void showCustomerList(final ArrayList<Customer> customerArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.hide();
                callAdapter(customerArrayList);
            }
        });
    }


    private void callAdapter(final ArrayList<Customer> customerArrayList) {
        customerAdapter =  new CustomerAdapter(this, R.id.customer_listview, customerArrayList);
        customerList.setAdapter(customerAdapter);
        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerActivity.this, DetailActivity.class);
                intent.putExtra(Constants.ITEM_PRODUCT,customerArrayList.get(position));
                startActivity(intent);
            }
        });
    }



    @Override
    public void showAlertDialog(int error_customer, int error_customer1) {

    }
}
