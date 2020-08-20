package entities;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Customer")
@DiscriminatorValue(value = "customer")
public class Customer extends User
{

    private List<Order> pastOrders;

    public Customer() {super();}
    public Customer(String email, String password, String name)
    {
        super(email, password, name);
    }

    @OneToMany
    @JoinTable(name = "customer_to_orders",
            joinColumns = @JoinColumn(name = "email"),
            inverseJoinColumns = @JoinColumn(name = "orderID"))
    public List<Order> getPastOrders(){return pastOrders;}

    public void setPastOrders(List<Order> pastOrders){this.pastOrders = pastOrders;}

    public void addPastOrder(Order order){this.pastOrders.add(order);}

    @Override
    public String toString()
    {
        return "Customer has email = "+email+" and name = "+name;
    }
}
