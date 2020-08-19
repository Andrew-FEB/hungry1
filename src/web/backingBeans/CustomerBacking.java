package web.backingBeans;

import ejb.CustomerBean;
import ejb.RegistrationBean;
import entities.RestaurantEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.*;

@ManagedBean
@ViewScoped
public class CustomerBacking implements Serializable {

    //User attributes
    private String email;

    @EJB
    private CustomerBean customer;
    private List<String> restaurants;

    @PostConstruct
    private void initialise()
    {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        email = (String) httpSession.getAttribute("email");
        restaurants = customer.getAvailableRestaurantNames();
    }

    public List<String> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<String> restaurants) {
        this.restaurants = restaurants;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

}
