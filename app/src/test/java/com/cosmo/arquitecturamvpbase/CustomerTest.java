package com.cosmo.arquitecturamvpbase;

import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.helper.IValidateInternet;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Location;
import com.cosmo.arquitecturamvpbase.model.PhoneList;
import com.cosmo.arquitecturamvpbase.presenter.AddCustomerPresenter;
import com.cosmo.arquitecturamvpbase.presenter.CustomerPresenter;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IAddCustomerView;
import com.cosmo.arquitecturamvpbase.views.activities.ICustomerView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.BaseStubbing;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;

import retrofit.RetrofitError;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by leonardo on 04/10/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

    @Mock
    IValidateInternet validateInternet;

    @Mock
    ICustomerRepository customerRepository;

    @Mock
    ICustomerView customerView;

    @Mock
    IAddCustomerView addCustomerView;

    CustomerPresenter customerPresenter;

    AddCustomerPresenter addCustomerPresenter;


    public Customer loadCustomer(){

        Customer customer = new Customer();
        PhoneList phone = new PhoneList();
        Location location = new Location();

        ArrayList<PhoneList> phoneList = new ArrayList<>();
        Double coord[] = {38.0, -40.0};

       // customer.setId("56789");
        customer.setName("rtyuiop");
        customer.setSurname("tyuio");
        //customer.setV(6789);

        location.setCoordinates(coord);
        location.setType("fghjk");

//        phone.setId("6789");
        phone.setLocation(location);
        phone.setNumber("rtyui");

        phoneList.add(phone);

        customer.setPhoneList(phoneList);

        return customer;
    }

    @Before
    public void setUp(){

        customerPresenter = Mockito.spy(new CustomerPresenter(customerRepository));
        customerPresenter.inject(customerView, validateInternet);

        addCustomerPresenter = Mockito.spy(new AddCustomerPresenter(customerRepository));
        addCustomerPresenter.inject(addCustomerView,validateInternet);

    }


    // :: GET CUSTOMERS :::

    @Test
    public void methodGetCustomerWithConnectionAndFailCallMethodCreateThreadGetCustomersShowAlertDialog(){

        when(validateInternet.isConnected()).thenReturn(false);
        customerPresenter.getCustomersList();
        verify(customerPresenter).getCustomersList();
        verify(customerView).showAlertDialog(R.string.error_customer,R.string.error_customer);
        verify(customerPresenter, never()).createThreadGetCustomer();
    }

    @Test
    public void methodGetCustomerWithConnectionCallMethodCreateGetCustomers(){

        when(validateInternet.isConnected()).thenReturn(true);
        customerPresenter.getCustomersList();
        verify(customerPresenter).createThreadGetCustomer();

    }

    @Test
    public void methodGetCustomerWithConnectionCallMethodCreateGetCustomersCallCustomersRepository() throws RepositoryError {

        when(validateInternet.isConnected()).thenReturn(true);
        customerPresenter.catchGetCustomerList();
        verify(customerPresenter).catchGetCustomerList();
        //verify(customerPresenter).createThreadGetCustomer();
        //verify(customerPresenter).catchGetCustomerList();
        verify(customerView, never()).showAlertDialog(R.string.error_customer,R.string.error_customer);

    }

    @Test
    public void methodGetCustomerWithConnectionCallMethodCreateGetCustomersFailCallCustomersRepository() throws RepositoryError {

        when(validateInternet.isConnected()).thenReturn(true);
        //doThrow(new Exception()).when(customerRepository.getCustomerList());
        when(customerRepository.getCustomerList()).thenThrow(new RepositoryError(Constants.ERROR_REPOSITORY_GETCUSTOMER));
        customerPresenter.catchGetCustomerList();
        verify(customerPresenter).catchGetCustomerList();
        //verify(customerPresenter).createThreadGetCustomer();
        //Assert.assertEquals(customerRepository.getCustomerList().isEmpty(),true);
        verify(customerView).showAlertDialog(R.string.error_customer,R.string.error_customer);

    }


    //::: POST CUSTOMERS ::::
    @Test
    public void methodPostCustomerWithConnectionFail(){
        Customer customer=loadCustomer();
        when(validateInternet.isConnected()).thenReturn(false);
        addCustomerPresenter.postCustomerValidate(customer);
        verify(addCustomerView).showAlertDialog(R.string.error_customer,R.string.error_customer);
        verify(addCustomerPresenter, never()).createThreadPostCustomer(customer);

    }

    @Test
    public void methodPostCustomerWithConnectionCreateThreadPostCustomer(){
        Customer customer=loadCustomer();
        when(validateInternet.isConnected()).thenReturn(true);
        addCustomerPresenter.postCustomerValidate(customer);
        verify(addCustomerPresenter).createThreadPostCustomer(customer);
        verify(addCustomerView, never()).showAlertDialog(R.string.error_customer,R.string.error_customer);

    }

    @Test
    public void methodPostCustomerWithConnectionCreateThreadPostCustomerAndFailCallAddCustomer() throws RepositoryError{
        Customer customer=loadCustomer();
        when(validateInternet.isConnected()).thenReturn(true);
        when(customerRepository.addCustomer(customer)).thenThrow(new RepositoryError(Constants.ERROR_REPOSITORY_ADDCUSTOMER));
        addCustomerPresenter.catchPostCustomer(customer);
        verify(addCustomerView).showAlertDialog(R.string.error_customer,R.string.error_customer);
    }






}
