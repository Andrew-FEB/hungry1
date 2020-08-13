package web;

import javax.ejb.Stateless;
import ejb.UserSearchBean;

@Named
@ViewScoped
public class RegisterBean implements Serializable {


    //User attributes
    private String email;
    private String password;
    private String permissions;

    @EJB
    private UserSearchBean userSearch;

    @PostConstruct
    private void initialise()
    {
        email = password = permissions = "";
    }

    public String register()
    {
        String returnCode = "";
        FacesMessage message;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession();
        if (userSearch.searchForUserWithEmail(email, password))
        {
            message = new FacesMessage("Registration failed", "User already exists!")
        }
        else
        {
            message = new FacesMessage("Registration Successful", "Account created");
            returnCode = "customer?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        httpSession.setAttribute("email", email);
        return returnCode;
    }

    @Size(min = 8, message = "Password should contain at least 8 characters")
    public void setPassword(String password) {this.password = password;}
    public String getPassword(){return this.password;}

    public void setEmail(String email) {this.email = email;}
    public String getEmail(){return this.email;}

    public void setPassword(String password) {this.password = password;}
    public String getPassword(){return this.password;}
}
