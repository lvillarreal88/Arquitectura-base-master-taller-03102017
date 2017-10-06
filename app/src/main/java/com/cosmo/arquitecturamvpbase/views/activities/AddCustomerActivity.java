package com.cosmo.arquitecturamvpbase.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Location;
import com.cosmo.arquitecturamvpbase.model.PhoneList;
import com.cosmo.arquitecturamvpbase.model.ResponseCustomerStatus;
import com.cosmo.arquitecturamvpbase.presenter.AddCustomerPresenter;
import com.cosmo.arquitecturamvpbase.presenter.CustomerPresenter;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;

import java.util.ArrayList;

/**
 * Created by leonardo on 05/10/2017.
 */

public class AddCustomerActivity extends BaseActivity<AddCustomerPresenter> implements IAddCustomerView {

    private EditText etxtvName;
    private EditText etxtvSurName;
    private EditText etxtvCoordinate;
    private EditText etxtPhoone;
    private Button btnCrear;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add);
        setPresenter(new AddCustomerPresenter());
        getPresenter().inject(this, getValidateInternet());
        loadComponentObject();

        btnCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Customer customer = new Customer();
                PhoneList phone = new PhoneList();
                Location location = new Location();

                ArrayList<PhoneList> phoneList = new ArrayList<>();
                Double coord[] = {Double.valueOf(etxtvCoordinate.getText().toString()), Double.valueOf(etxtvCoordinate.getText().toString())};

                customer.setName(etxtvName.getText().toString());
                customer.setSurname(etxtvSurName.getText().toString());

                //coord.add(new Double(etxtvCoordinate.getText().toString()));
                //coord.add(new Double(etxtvCoordinate.getText().toString()));

                location.setCoordinates(coord);
                location.setType("Point");

                phone.setLocation(location);
                phone.setNumber(etxtPhoone.getText().toString());

                phoneList.add(phone);

                customer.setPhoneList(phoneList);

                addCustomer(customer);
            }
        });

    }


    public void loadComponentObject(){
        etxtvName = (EditText) findViewById(R.id.add_etext_name);
        etxtvSurName =(EditText) findViewById(R.id.add_etext_surname);
        etxtvCoordinate =(EditText)  findViewById(R.id.add_etext_coordinate);
        etxtPhoone=(EditText) findViewById(R.id.add_etext_phone);
        btnCrear=(Button) findViewById(R.id.add_btn_crear);
        btnCrear.setEnabled(true);
    }

    @Override
    public void addCustomer(Customer customer) {
        try{
            getPresenter().postCustomerValidate(customer);
            showAlertDialog(R.string.add_customer_ok,R.string.add_customer_ok);
        }catch (Exception e){
            Toast.makeText(this, Constants.ERROR_REPOSITORY_ADDCUSTOMER, Toast.LENGTH_LONG).show();
        }finally {
            finish();
        }
    }

    @Override
    public void showAlertDialog(int error_customer, int error_customer1) {

    }

    @Override
    public void showToast(final int add_customer_ok) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AddCustomerActivity.this, getString(add_customer_ok), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
