package ejb;

import entities.UserEntity;
import entities.CustomerEntity;
import entities.RestaurantEntity;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless(name = "DatabaseGatewayBean")
public class RegistrationBean
{
    @PersistenceContext(name = "HungryPersistenceUnit")
    private EntityManager entityManager;

    public RegistrationBean(){}

    public boolean createCustomer(String email, String password) {
        try {
            CustomerEntity customer = new CustomerEntity(email, password);
            entityManager.persist(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createRestaurant(String email, String password, String name) {
        try {
            RestaurantEntity restaurant = new RestaurantEntity(email, password, name);
            entityManager.persist(restaurant);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean searchForUserWithEmail(String email, String password)
    {
        try
        {
            TypedQuery<UserEntity> q = entityManager.createQuery("SELECT u from Users where u.email = :userEmail AND u.password = :userPassword", UserEntity.class)
            .setParameter("userEmail", email)
            .setParameter("userPassword", password);
            UserEntity user = q.getSingleResult();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public String getPermissions(String email)
    {
        try
        {
            TypedQuery<String> q = entityManager.createQuery("SELECT u.permissions from Users where u.email = :userEmail", String.class)
                    .setParameter("userEmail", email);
            return q.getSingleResult();
        }
        catch(Exception e)
        {
            return "NULL";
        }
    }
}
