package ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.*;

public class NewUserLogInterceptor {
    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        Logger log = Logger.getLogger(NewUserLogInterceptor.class.getName());
        Object[] contextData = context.getParameters();
        String name = (String) contextData[2];
        log.log(Level.INFO, "New user created with name "+name);
        return context.proceed();
    }
}
