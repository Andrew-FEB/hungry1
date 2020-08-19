package entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customers", schema = "hungry")
@DiscriminatorValue(value = "customer")
public class CustomerEntity extends UserEntity
{

    private List<OrderEntity> pastOrders;

    public CustomerEntity() {super();}
    public CustomerEntity(String email, String password)
    {
        super(email, password);
        super.permissions = "Customer";
    }

    @Basic
    @Column(name = "permissions")
    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @OneToMany
    @JoinTable(name = "customer_to_orders",
            joinColumns = @JoinColumn(name = "email"),
            inverseJoinColumns = @JoinColumn(name = "orderID"))
    public List<OrderEntity> getPastOrders(){return pastOrders;}

    public void setPastOrders(List<OrderEntity> pastOrders){this.pastOrders = pastOrders;}

    public void addPastOrder(OrderEntity order){this.pastOrders.add(order);}

}
