package fr.infini.domotique.siteroom.resources;

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
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;

import fr.infini.domotique.siteroom.model.Carte;
import fr.infini.domotique.siteroom.service.CarteService;

@Path("/cards")
public class CartesRessources {

	private CarteService service = CarteService.getInstance();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response findAll(){
		System.out.println("findall");
		List<Carte> cartes =  service.rechercher();
		return Response.status(Status.OK).entity(cartes).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(Carte carte){
		System.out.println("save carte: " + carte);
		carte = service.sauvegarder(carte);
		return Response.status(Status.OK).entity(carte).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})	
	public Response update(Carte carte) {
		System.out.println("update card : " + carte);
		carte = service.modifier(carte);
		return Response.status(Status.OK).entity(carte).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})	
	public Response remove(@PathParam("id") String id) {
		System.out.println("delete card with id : " + id);
		service.supprimer(new ObjectId(id));
		return Response.status(Status.OK).build();
	}
	
}
