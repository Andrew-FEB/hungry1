package entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.*;
import java.lang.*;


@Entity
@Table(name = "orders", schema = "hungry")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    private String customerEmail;
    private double cost;
    private List<Food> items;
    private String restaurantName;

    public Order(){}

    public Order(String customerEmail, double cost, List<Food> items, String restaurantName)
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
        Order order = (Order) o;
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
    public List<Food> getItems()
    {
        return items;
    }

    public void setItems(List<Food> items)
    {
        this.items=items;
    }

    public void addItem(Food item) {
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

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Order costs "+cost+" and contains:\n");
        for (Food item : items)
        {

        }
        return str.toString();
    }
}
