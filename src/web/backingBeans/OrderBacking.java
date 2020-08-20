package web.backingBeans;

import ejb.OrderBean;
import javax.ejb.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class OrderBacking implements Serializable {

    @EJB
    OrderBean order;

    String foodName;

    List<String> foodNames;

    @PostConstruct
    private void initialise()
    {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String restaurantName = (String) httpSession.getAttribute("restaurantName");
        foodNames = order.getFoodsForRestaurant(restaurantName);
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public List<String> getFoodNames() {
        return foodNames;
    }

    public void setFoodNames(List<String> foodNames) {
        this.foodNames = foodNames;
    }

}
