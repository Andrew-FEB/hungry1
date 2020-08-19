package web.backingBeans;

import javax.ejb.*;
import ejb.RegistrationBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.*;

@ManagedBean
@ViewScoped
public class RegisterBacking implements Serializable {

    //User attributes
    private String email;
    private String password;
    private String permissions;

    //Restaurant attributes
    private String name;

    @EJB
    private RegistrationBean registrationBean;

    @PostConstruct
    private void initialise()
    {
        email = password = permissions = "";
    }

    public String register()
    {
        String returnCode = "";
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (registrationBean.searchForUserWithEmail(email, password))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration failed", "User already exists!"));
            return returnCode;
        }

        switch(permissions)
        {
            case("Customer"):
                if (registrationBean.createCustomer(email, password))
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration Successful", "Account created"));
                    returnCode = "customer?faces-redirect=true";
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration Unsuccessful", "Account creation failed"));
                    returnCode = "";
                }
                break;

            case("Restaurant"):
                if (registrationBean.createRestaurant(email, password, name))
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration Successful", "Account created"));
                    returnCode = "restaurant?faces-redirect=true";
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration Unsuccessful", "Account creation failed"));
                    returnCode = "";
                }
                break;
        }

        httpSession.setAttribute("email", email);
        return returnCode;
    }

    public void setPassword(String password) {this.password = password;}
    public String getPassword(){return this.password;}

    public void setEmail(String email) {this.email = email;}
    public String getEmail(){return this.email;}

    public void setPermissions(String permissions) {this.permissions = permissions;}
    public String getPermissions(){return this.permissions;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString()
    {
        return "RegisterBean has email = "+getEmail()+" and password = "+getEmail()+" and permissions = "+getPermissions();
    }
}
