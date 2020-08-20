package ejb;

import entities.Restaurant;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless(name = "CustomerEJB")
public class CustomerBean {

    @PersistenceContext(name = "HungryPersistenceUnit")
    private EntityManager entityManager;

    public CustomerBean() { }

    public List<String> getAvailableRestaurantNames()
    {
        try
        {
            Query q = entityManager.createNativeQuery("SELECT * FROM users WHERE permissions = 'Restaurant'", Restaurant.class);
            List<Restaurant> restaurants = q.getResultList();
            List<String> output = new ArrayList<>();
            for (Restaurant rest : restaurants)
            {
                output.add(rest.getName());
            }
            return output;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
