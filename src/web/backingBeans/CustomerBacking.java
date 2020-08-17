package web.backingBeans;

import ejb.CustomerBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class CustomerBacking implements Serializable {

    //User attributes
    private String email;

    @EJB
    private CustomerBean customer;

    @PostConstruct
    private void initialise()
    {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        email = (String) httpSession.getAttribute("email");
    }

    public void setEmail(String email) {this.email = email;}
    public String getEmail(){return this.email;}

}
