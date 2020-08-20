package ejb;
import entities.Food;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless(name = "OrderEJB")
public class OrderBean {

    List<String> foods;

    @PersistenceContext(name = "HungryPersistenceUnit")
    private EntityManager entityManager;

    public OrderBean() {
    }

    public List<String> getFoodsForRestaurant(String name)
    {
        try
        {
            Query q = entityManager.createNativeQuery("SELECT r.foods FROM foods WHERE r.name = :name", Food.class)
                    .setParameter(":name", name);
            List<Food> foodItems = q.getResultList();
            List<String> names = new ArrayList<>();
            for (Food food : foodItems)
            {
                names.add(food.getName());
            }
            return names;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
