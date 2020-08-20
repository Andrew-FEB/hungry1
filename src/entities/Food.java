package entities;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "foods", schema = "hungry")
public class Food {
    @Id
    String name;
    double cost;

    public Food() {
    }

    public Food(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(name, ((Food) o).name);
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
}

