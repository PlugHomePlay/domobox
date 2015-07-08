package fr.infini.domobox.webservices;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.infini.domobox.aspect.Loggable;
import fr.infini.domobox.model.entite.Box;
import fr.infini.domobox.model.service.BoxServices;
import fr.infini.domobox.redis.ChannelType;
import fr.infini.domobox.redis.service.RedisService;
import fr.infini.domobox.utils.RedisHandler;

@Path("/box")
public class BoxRestService {

	private final BoxServices services = BoxServices.getInstance();
	
	private final RedisService redisService = RedisService.getInstance();
	
	@POST
	public Response ajouterDomobox(Box box) {
		//Ajout de la box à l'application
		services.ajouterBox(box);
		
		//Ajout d'un nouveau channel de notification
		redisService.addChannel(RedisHandler.INSTANCE.getRedisName(box), ChannelType.BOX);
		return Response.ok(box).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable
	public Response listerBox() {
		List<Box> boxs = services.listerBox();
		GenericEntity<List<Box>> entity = new GenericEntity<List<Box>>(boxs) {};
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("{boxId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchBox(@PathParam("boxId") long id) {
		Box box = services.rechercherBox(id);
		return Response.ok(box).build();
	}
	
	@DELETE
	@Path("{boxId}")
	public Response retirerBox(@PathParam("boxId") long id){
		Box box = services.rechercherBox(id);
		if(box != null){
			services.supprimerBox(box);
			redisService.removeChannel(RedisHandler.INSTANCE.getRedisName(box));
			
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}
	}
}
