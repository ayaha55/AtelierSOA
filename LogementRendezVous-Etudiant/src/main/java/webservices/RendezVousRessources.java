package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
public class RendezVousRessources {
    static RendezVousBusiness service = new RendezVousBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<RendezVous> liste = service.getListeRendezVous();
        return Response.status(200).entity(liste).build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        RendezVous rendezVous = service.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.status(200).entity(rendezVous).build();
        } else {
            return Response.status(404).entity("Rendez-vous non trouvé").build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean isAdded = service.addRendezVous(rendezVous);
        if (isAdded) {
            return Response.status(201).entity("Rendez-vous ajouté avec succès").build();
        } else {
            return Response.status(400).entity("Erreur lors de l'ajout du rendez-vous").build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean isDeleted = service.deleteRendezVous(id);
        if (isDeleted) {
            return Response.status(200).entity("Rendez-vous supprimé avec succès").build();
        } else {
            return Response.status(404).entity("Rendez-vous non trouvé").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        boolean isUpdated = service.updateRendezVous(id, rendezVous);
        if (isUpdated) {
            return Response.status(200).entity("Rendez-vous mis à jour avec succès").build();
        } else {
            return Response.status(400).entity("Erreur lors de la mise à jour du rendez-vous").build();
        }
    }
}