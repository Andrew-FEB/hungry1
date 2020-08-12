package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "customers", schema = "hungry")
@DiscriminatorValue(value = "customer")
public class Customer extends User
{
    //private List<Purchase> purchases;

    public Customer() {super();}
    public Customer (String email, String password, String permissions)
    {
        super(email, password, permissions);
    }

}
