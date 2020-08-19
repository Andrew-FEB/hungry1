package web.backingBeans;

import ejb.CustomerBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class RestaurantBacking implements Serializable {

    //User attributes
    private String email;

    @PostConstruct
    private void initialise()
    {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        email = (String) httpSession.getAttribute("email");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
}
