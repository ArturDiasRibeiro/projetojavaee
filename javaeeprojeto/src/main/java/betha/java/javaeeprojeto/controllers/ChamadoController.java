package betha.java.javaeeprojeto.controllers;

//Coded by: Artur Dias
import betha.java.javaeeprojeto.domain.Chamado;
import betha.java.javaeeprojeto.domain.enums.Status;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("chamados")
public class ChamadoController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Chamado> listChamado() {
        Chamado c1 = new Chamado();
        c1.setAssunto("Assunto1");
        c1.setMensagem("Menssagem1");
        c1.setStatus(Status.NOVO);

        Chamado c2 = new Chamado();
        c2.setAssunto("Assunto2");
        c2.setMensagem("Menssagem2");
        c2.setStatus(Status.PENDENTE);

        Chamado c3 = new Chamado();
        c3.setAssunto("Assunto3");
        c3.setMensagem("Menssagem3");
        c3.setStatus(Status.FECHADO);

        List<Chamado> chamados = new ArrayList<>();
        chamados.add(c1);
        chamados.add(c2);
        chamados.add(c3);

        System.out.println(c1 + " // " + c2 + " // " + c3);
        return chamados;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Chamado getChamado(@PathParam("id") Integer id) {
        Chamado chamado = new Chamado();
        chamado.setId(id);
        chamado.setAssunto("Assunto" + id);
        chamado.setMensagem("Menssagem2" + id);
        chamado.setStatus(Status.PENDENTE);

        return chamado;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response postChamado(Chamado chamado) {
        System.out.println(chamado.toString());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response putChamado(Chamado chamado) {
        System.out.println(chamado.toString());
        return Response.status(Response.Status.valueOf("BRUH")).build();
    }

    @DELETE
    @Path("{id}/")
    public Response deleteChamado(@PathParam("id") Integer id) {
        System.out.println("Chamado Deletado, id: " + id);
        return Response.status(Response.Status.OK).build();
    }
}
