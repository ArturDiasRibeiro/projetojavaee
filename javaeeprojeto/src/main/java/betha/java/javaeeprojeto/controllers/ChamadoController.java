package betha.java.javaeeprojeto.controllers;

//Coded by: Artur Dias
import betha.java.javaeeprojeto.domain.Chamado;
import betha.java.javaeeprojeto.domain.ChamadoDAO;
import betha.java.javaeeprojeto.domain.enums.Status;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("chamados")
public class ChamadoController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Chamado> listChamado() {
        try {
            ChamadoDAO chamadoDAO = new ChamadoDAO();
            return chamadoDAO.listar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
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

        System.out.println(chamado);
        return chamado;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response postChamado(Chamado chamado) {
        try {
            chamado.setStatus(Status.NOVO);
            ChamadoDAO chamadoDAO = new ChamadoDAO();
            chamadoDAO.inserir(chamado);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response putChamado(Chamado chamado) {
        try {
            chamado.setStatus(Status.PENDENTE);

            ChamadoDAO chamadoDAO = new ChamadoDAO();
            chamadoDAO.alterar(chamado);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}/")
    public Response deleteChamado(@PathParam("id") Integer id) {
        try{
            ChamadoDAO chamadoDAO = new ChamadoDAO();
            chamadoDAO.excluir(id);
            return Response.status(Response.Status.OK).build();
        } catch(SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Path("{id}/")
    public Response concludeChamado(@PathParam("id") Integer id) {
        try{
            ChamadoDAO chamadoDAO = new ChamadoDAO();
            Chamado c = chamadoDAO.selecionar(id);
            c.setStatus(Status.FECHADO);
            chamadoDAO.alterar(c);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
