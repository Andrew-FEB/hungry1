package entities;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Restaurant")
@DiscriminatorValue(value = "restaurant")
public class Restaurant extends User
{
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_foods",
                joinColumns = @JoinColumn(name = "name"))
    private List<Food> foods;
    public Restaurant(){}
    public Restaurant(String email, String password, String name)
    {
        super(email, password, name);
        foods = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        Restaurant restaurant = (Restaurant) o;
        return (Objects.equals(name, restaurant.name));
    }

    public List<Food> getFoods() { return foods; }

    public void setFoods(List<Food> foods)
    {
        this.foods=foods;
    }


}
