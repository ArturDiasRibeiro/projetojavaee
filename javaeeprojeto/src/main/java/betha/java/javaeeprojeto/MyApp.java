
package betha.java.javaeeprojeto;

//Coded by: Artur Dias

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class MyApp extends ResourceConfig {

    public MyApp() {
        packages("betha.java.javaeeprojeto.controllers");
    }

}
