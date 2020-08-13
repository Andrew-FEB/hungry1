package ejb;

import java.util.List;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless(name = "UserSearchEJB")
public class UserSearchBean
{
    @PersistenceContext(name = "HungryPersist")
    private EntityManager entityManager;

    public UserSearchBean(){}

    public boolean searchForUserWithEmail(String email, String password)
    {
        try
        {
            TypedQuery<User> q = entityManager.createQuery("SELECT u from Users where u.email = :userEmail AND u.password = :userPassword", User.class)
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
