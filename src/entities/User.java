package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users", schema = "hungry")
@DiscriminatorColumn(name = "permissions", discriminatorType = DiscriminatorType.STRING)
public class User implements Serializable
{

    protected String email;

    protected String password;

    private String permissions;

    public User(){}

    public User(String email, String password, String permissions)
    {
        this.email = email;
        this.password = email;
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Id
    @Column(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User has email = "+email+" and password = "+password;
    }
}
