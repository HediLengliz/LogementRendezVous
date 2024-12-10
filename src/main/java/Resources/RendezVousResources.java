package Resources;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import entities.RendezVous;
import metiers.RendezVousBusiness;

import java.util.List;

@Path("RendezVous")
public class RendezVousResources {
    public static RendezVousBusiness rdv = new RendezVousBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/ajouter")
    public Response createRendezVous(RendezVous r) {
        if (rdv.addRendezVous(r)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/afficher")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeRendezVous() {
        List<RendezVous> listeRendezVous = rdv.getListeRendezVous();
        if (listeRendezVous.size() != 0) {
            return Response.status(Status.OK).entity(listeRendezVous).build();
        }
        return Response.status(Status.NOT_FOUND).build();

    }
    @GET
    @Path("/test")
    public Response testEndpoint() {
        return Response.ok("Test Successful").build();
    }
    @GET
    @Path("/ID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@QueryParam("id") Integer id) {
        RendezVous rendezVous = rdv.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.status(Response.Status.OK).entity(rendezVous).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("RendezVous with ID " + id + " not found.").build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteRendezVous(@PathParam("id") Integer id) {
        boolean isDeleted = rdv.deleteRendezVous(id);
        if (isDeleted) {
            return Response.status(Response.Status.OK).entity("RendezVous with ID " + id + " deleted successfully.").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("RendezVous with ID " + id + " not found.").build();
    }
    @GET
    @Path("/RDVBYLOGEMENT")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByLogementReference(@QueryParam("refLogement") Integer refLogement) {
        List<RendezVous> rendezVousList = rdv.getListeRendezVousByLogementReference(refLogement);
        if (!rendezVousList.isEmpty()) {
            return Response.status(Response.Status.OK).entity(rendezVousList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("No rendezvous found for logement reference " + refLogement).build();
    }
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") Integer id, RendezVous updatedRendezVous) {
        boolean isUpdated = rdv.updateRendezVous(id, updatedRendezVous);
        if (isUpdated) {
            return Response.status(Response.Status.OK).entity(updatedRendezVous).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("RendezVous with ID " + id + " not found or invalid Logement reference.").build();
    }


}
