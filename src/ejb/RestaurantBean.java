package ejb;

import entities.Food;
import entities.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.TypedQuery;
import java.util.*;

@Singleton(name = "RestaurantEJB")
@Startup
public class RestaurantBean {

    @PersistenceContext(name = "HungryPersistenceUnit")
    private EntityManager entityManager;

    public RestaurantBean() {
    }

    public boolean addFood(String name, double cost, String restaurantName)
    {
        try {
            Food food = new Food(name, cost);
            entityManager.persist(food);
            TypedQuery<Restaurant> q = entityManager.createQuery("SELECT r FROM users r WHERE r.name = :restaurantName", Restaurant.class)
                    .setParameter("restaurantName", restaurantName);
            Restaurant restaurant = (Restaurant)q.getSingleResult();
            List<Food> foods = restaurant.getFoods();
            foods.add(food);
            restaurant.setFoods(foods);
            entityManager.merge(restaurant);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
