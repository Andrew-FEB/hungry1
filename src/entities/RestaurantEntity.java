package entities;
import entities.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurants", schema = "hungry")
@DiscriminatorValue(value = "restaurant")
public class RestaurantEntity extends UserEntity
{
    private List<FoodEntity> foods;

    public RestaurantEntity(){}

    public RestaurantEntity(String email, String password, String name)
    {
        super(email, password, name);
        super.permissions = "Restaurant";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        RestaurantEntity restaurant = (RestaurantEntity) o;
        return (Objects.equals(name, restaurant.name));
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
