package ejb;

import entities.UserEntity;
import entities.RestaurantEntity;
import entities.FoodEntity;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.sql.Timestamp;

@Stateless(name = "CustomerEJB")
public class CustomerBean {

    @PersistenceContext(name = "HungryPersistenceUnit")
    private EntityManager entityManager;

    public CustomerBean() {
    }

    public List<String> getAvailableRestaurantNames()
    {
        try
        {
            TypedQuery<String> q = entityManager.createQuery("SELECT r.name from restaurants", String.class);
            List<String> restaurants = q.getResultList();
            return restaurants;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public List<String> getFoodsForRestaurant(String name)
    {
        try
        {
            TypedQuery<String> q = entityManager.createQuery("SELECT f.name from foods where restaurantName = :name", String.class)
                    .setParameter(":name", name);
            List<String> foodItems = q.getResultList();
            return foodItems;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
