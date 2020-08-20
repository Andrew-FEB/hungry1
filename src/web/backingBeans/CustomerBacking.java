package web.backingBeans;

import ejb.CustomerBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.*;

@ManagedBean
@SessionScoped
public class CustomerBacking implements Serializable {

    //User attributes
    private String name;

    @EJB
    private CustomerBean customer;
    private List<String> restaurants;
    private String restaurantName;

    @PostConstruct
    private void initialise()
    {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        name = (String) httpSession.getAttribute("name");
        restaurants = customer.getAvailableRestaurantNames();
        restaurantName = "";
    }

    public String order()
    {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String returnCode = "";
        if (!(restaurantName.equals(""))) {
            httpSession.setAttribute("restaurantName", restaurantName);
            returnCode = "order?faces-redirect=true";
        }
        return returnCode;
    }

    public List<String> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<String> restaurants) {
        this.restaurants = restaurants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

}
