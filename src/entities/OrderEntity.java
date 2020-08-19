package entities;
import entities.FoodEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.*;



@Entity
@Table(name = "orders", schema = "hungry")
public class OrderEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    private String customerEmail;
    private double cost;
    private List<FoodEntity> items;
    private String restaurantName;

    public OrderEntity(){}

    public OrderEntity(String customerEmail, double cost, List<FoodEntity> items, String restaurantName)
    {
        this.customerEmail = customerEmail;
        this.cost = cost;
        this.items = items;
        this.restaurantName = restaurantName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        OrderEntity order = (OrderEntity) o;
        return (Objects.equals(orderID, order.orderID));

    }

    @Id
    @Column(name = "orderid")
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_to_items",
            joinColumns = @JoinColumn(name = "orderid"))
    public List<FoodEntity> getItems()
    {
        return items;
    }

    public void setItems(List<FoodEntity> items)
    {
        this.items=items;
    }

    public void addItem(FoodEntity item) {
        this.items.add(item);
    }

    @Basic
    @Column(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(String password) {
        this.cost = cost;
    }


    @Column(name = "customerEmail")
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Id
    @Column(name = "restaurantName")
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


}
