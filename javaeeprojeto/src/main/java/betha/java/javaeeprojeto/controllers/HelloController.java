
package betha.java.javaeeprojeto.controllers;

//Coded by: Artur Dias

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloController {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@QueryParam("usuario") String usuario){
        return "Welcome: " + usuario;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("usuarios/{id}")
    public String getUsuario(@PathParam("id") Integer id){
        return "Retrieving user with ID" + id;
    }
}
