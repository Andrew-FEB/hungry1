package web.backingBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class OrderBacking implements Serializable {


    @PostConstruct
    private void initialise()
    {
    }

}
