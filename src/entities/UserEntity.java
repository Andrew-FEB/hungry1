package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users", schema = "hungry")
@DiscriminatorColumn(name = "permissions", discriminatorType = DiscriminatorType.STRING)
public class UserEntity implements Serializable
{
    @Id
    protected String email;

    protected String name;

    protected String password;

    protected String permissions;

    public UserEntity(){}

    public UserEntity(String email, String password, String name)
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
        UserEntity user = (UserEntity) o;
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

    @Basic
    @Column(name = "permissions")
    public String getPermissions() {
        return permissions;
    }
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Id
    @Column(name = "name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString()
    {
        return "User has email = "+email+" and name = "+name+" and password = "+password;
    }
}
