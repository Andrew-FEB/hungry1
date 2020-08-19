package entities;
import entities.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "orders", schema = "hungry")
public class RestaurantEntity extends UserEntity
{
    private String name;
    private List<FoodEntity> foods;

    public RestaurantEntity(){}

    public RestaurantEntity(String email, String password, String name)
    {
        super(email, password);
        super.permissions = "Restaurant";
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        RestaurantEntity restaurant = (RestaurantEntity) o;
        return (Objects.equals(name, restaurant.name));
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_to_foods",
            joinColumns = @JoinColumn(name = "name"))
    public List<FoodEntity> getFoods()
    {
        return foods;
    }

    public void setFoods(List<FoodEntity> foods)
    {
        this.foods=foods;
    }

    public void addFood(FoodEntity foods) {
        this.foods.add(foods);
    }

}
