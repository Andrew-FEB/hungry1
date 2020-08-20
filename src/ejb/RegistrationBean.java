package ejb;

import entities.User;
import entities.Customer;
import entities.Restaurant;
import javax.interceptor.Interceptors;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import javax.naming.*;
import javax.jms.*;

@Stateless(name = "DatabaseGatewayBean")
public class RegistrationBean
{
    @PersistenceContext(name = "HungryPersistenceUnit")
    private EntityManager entityManager;

    public RegistrationBean(){}

    @Interceptors(NewUserLogInterceptor.class)
    public boolean createCustomer(String email, String password, String name) {
        try {
            Customer customer = new Customer(email, password, name);
            entityManager.persist(customer);

            // Gets the JNDI context
            Context jndiContext = new InitialContext();
            // Looks up the administered objects
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/HungryConnectionFactory");
            Destination queue = (Destination) jndiContext.lookup("jms/HungryQueue");
            JMSContext jmsContext = connectionFactory.createContext();
            // Sends an object message to the topic
            jmsContext.createProducer().send(queue, customer.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Interceptors(NewUserLogInterceptor.class)
    public boolean createRestaurant(String email, String password, String name) {
        try {
            Restaurant restaurant = new Restaurant(email, password, name);
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
            TypedQuery<User> q = entityManager.createQuery("SELECT * from User where u.email = :userEmail AND u.password = :userPassword", User.class)
            .setParameter("userEmail", email)
            .setParameter("userPassword", password);
            User user = q.getSingleResult();
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
            TypedQuery<String> q = entityManager.createQuery("SELECT u.permissions from users where u.email = :userEmail", String.class)
                    .setParameter("userEmail", email);
            return q.getSingleResult();
        }
        catch(Exception e)
        {
            return "NULL";
        }
    }
}
