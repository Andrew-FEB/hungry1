package web;

import ejb.DatabaseGatewayBean;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {

    @EJB
    private DatabaseGatewayBean userSearch;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean userFound = userSearch.searchForUserWithEmail(email, password);
        if (!userFound)
        {
            request.setAttribute("msg", "invalid");
        }
        else
        {
            request.setAttribute("permissions", userSearch.getPermissions(email));
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
