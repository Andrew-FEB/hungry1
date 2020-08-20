package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@JMSDestinationDefinition(
        name = "java:app/jms/HungryQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "HungryQueue"
)

 @JMSConnectionFactoryDefinition(
         name="java:app/jms/HungryConnectionFactory"
 )

@MessageDriven(name = "ReceiverEJB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "jms/HungryQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class LoggerBean implements MessageListener {
    public LoggerBean() {
    }

    @Override
    public void onMessage(Message message) {
        Logger logger = Logger.getLogger("ReceiverBean");
        try {
            if (message instanceof TextMessage) {
                logger.log(Level.INFO,"Message received: {0}", ((TextMessage)message).getBody(String.class));
            } else {
                logger.log(Level.WARNING,"Wrong type of message!");
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE,"JMSException: {0}",e.toString());
            e.printStackTrace();
        }
    }
}
