package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity  @Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "permissions", discriminatorType = DiscriminatorType.STRING)
public abstract class User
{
    @Id
    protected String name;

    protected String email;

    protected String password;

    public User(){}

    public User(String email, String password, String name)
    {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Basic
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

    @Id
    @Column(name = "name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
