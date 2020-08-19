package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "foods", schema = "hungry")
public class FoodEntity implements Serializable
{
    @Id
    String name;
    String restaurantName;
    double cost;
    
    public FoodEntity(){}

    public FoodEntity(String name, String restaurantName, double cost)
    {
        this.name = name;
        this.restaurantName = restaurantName;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        return Objects.equals(name, ((FoodEntity)o).name);
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(String password) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "restaurantName")
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


}
