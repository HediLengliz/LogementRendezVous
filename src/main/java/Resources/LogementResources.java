package Resources;

import entities.Logement;
import metiers.LogementBusiness;
import javax.ws.rs.ApplicationPath;

import javax.print.attribute.standard.MediaPrintableArea;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("logements")
public class LogementResources {

    public static LogementBusiness logb = new LogementBusiness();

    @POST
    //@Consumes("application/xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/ajouter")
    public Response ajouterLogement(Logement l) {
        if (logb.addLogement(l))
            return Response.status(Status.CREATED).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/afficher")
    public Response afficherLogements(Logement l) {
        if (logb.getLogements().size() != 0)
            return Response.status(Status.OK).entity(logb.getLogements()).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/test")
    public Response testEndpoint() {
        return Response.ok("Test Successful").build();
    }

    @DELETE
    @Path("/supprimer/{reference}")
    public Response supprimerLogement(@PathParam("reference") int reference) {
        if (logb.deleteLogement(reference)) {
            return Response.status(Response.Status.OK).entity("Logement with reference " + reference + " deleted successfully.").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Logement with reference " + reference + " not found.").build();
    }
    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogementref(@PathParam("reference") int reference, Logement newLogement) {
        Logement updatedLogementref = logb.updateLogementref(reference, newLogement);
        if (updatedLogementref != null) {
            return Response.status(Response.Status.OK).entity(updatedLogementref).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Logement with reference " + reference + " not found.").build();
    }
    @GET
    @Path("/get/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogement(@PathParam("reference") int reference) {
        Logement logement = logb.getLogementByReference(reference);
        if (logement != null) {
            return Response.status(Response.Status.OK).entity(logement).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Logement with reference " + reference + " not found.").build();
    }

    @GET
    @Path("/getLogByref/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsListeByref(@PathParam("reference") int reference) {
        if (logb.getLogementsListeByref(reference).size() != 0)
            return Response.status(Status.OK).entity(logb.getLogementsListeByref(reference)).build();
        return Response.status(Status.NOT_FOUND).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDeleguation(@QueryParam("deleguation") String deleguation) {
        if (logb.getLogementsByDeleguation(deleguation).size() != 0)
            return Response.status(Status.OK).entity(logb.getLogementsByDeleguation(deleguation)).build();
        return Response.status(Status.NOT_FOUND).build();
    }
}
