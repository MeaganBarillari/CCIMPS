package com.example.ccimp;

import com.example.ccimp.ui.business.BusinessProfileActivity;
import com.example.ccimp.ui.business.BusinessRequestCartActivity;
import com.example.ccimp.ui.interfaces.business.BusinessCartInterface;
import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCartPresenter;
import com.example.ccimp.ui.presenter.business.BusinessProfilePresenter;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BusinessUnitTest {

    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");


    @Test
    public void testCart(){
        BusinessCartInterface.BusinessCartView businessCartView = new BusinessRequestCartActivity();
        BusinessCartPresenter businessCartPresenter = new BusinessCartPresenter(businessCartView, user.getUserID());

        businessCartPresenter.onViewCreate();
        assertNotNull(businessCartPresenter);
    }

    @Test
    public void testProfile(){
        BusinessProfileInterface.BusinessProfileView businessProfileView = new BusinessProfileActivity();
        BusinessProfilePresenter businessProfilePresenter = new BusinessProfilePresenter(businessProfileView, user.getUserID());
        businessProfilePresenter.onViewCreate();
        assertNotNull(businessProfilePresenter);
    }

}
