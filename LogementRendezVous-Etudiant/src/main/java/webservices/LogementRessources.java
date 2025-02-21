package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/logement")
public class LogementRessources {
    static LogementBusiness help = new LogementBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogements()).
                build();
    }

    @GET
    @Path("/get/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByReference(@PathParam("reference") int reference) {
        Logement logement = help.getLogementsByReference(reference);
        if (logement != null) {
            return Response.status(200).entity(logement).build();
        } else {
            return Response.status(404).entity("Logement non trouvé").build();
        }
    }

    @GET
    @Path("/getByDelegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
        return Response.status(200).entity(help.getLogementsByDeleguation(delegation)).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean isAdded = help.addLogement(logement);
        if (isAdded) {
            return Response.status(201).entity("Logement ajouté avec succès").build();
        } else {
            return Response.status(400).entity("Erreur lors de l'ajout du logement").build();
        }
    }

    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean isDeleted = help.deleteLogement(reference);
        if (isDeleted) {
            return Response.status(200).entity("Logement supprimé avec succès").build();
        } else {
            return Response.status(404).entity("Logement non trouvé").build();
        }
    }

    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean isUpdated = help.updateLogement(reference, logement);
        if (isUpdated) {
            return Response.status(200).entity("Logement mis à jour avec succès").build();
        } else {
            return Response.status(400).entity("Erreur lors de la mise à jour du logement").build();
        }
    }
}