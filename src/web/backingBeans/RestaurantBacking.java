package web.backingBeans;

import ejb.CustomerBean;
import ejb.RestaurantBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class RestaurantBacking implements Serializable {

    //User attributes
    private String name;

    //Food attributes
    private String foodName;
    private double foodCost;

    @EJB
    private RestaurantBean restaurant;

    @PostConstruct
    private void initialise()
    {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        name = (String) httpSession.getAttribute("name");
    }

    public void addFood()
    {
        if (restaurant.addFood(foodName, foodCost, name))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Food added", "Food added to menu"));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Food not added", "Failed to add food to menu"));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(double foodCost) {
        this.foodCost = foodCost;
    }


}
