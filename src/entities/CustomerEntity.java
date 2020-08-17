package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers", schema = "hungry")
@DiscriminatorValue(value = "customer")
public class CustomerEntity extends UserEntity
{

    public CustomerEntity() {super();}
    public CustomerEntity(String email, String password)
    {
        super(email, password);
        super.permissions = "Customer";
    }

}
