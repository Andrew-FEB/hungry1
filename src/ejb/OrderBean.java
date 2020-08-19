package ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateful(name = "CustomerEJB")
public class OrderBean {

    @PersistenceContext(name = "HungryPersistenceUnit")
    private EntityManager entityManager;

    public OrderBean() {
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
            TypedQuery<String> q = entityManager.createQuery("SELECT f.name from restaurant_to_foods where name  = :name", String.class)
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
